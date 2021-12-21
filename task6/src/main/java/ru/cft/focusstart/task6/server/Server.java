package ru.cft.focusstart.task6.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.cft.focusstart.task6.common.Connection;
import ru.cft.focusstart.task6.common.Message;
import ru.cft.focusstart.task6.common.MessageType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

record Server(int port) {

    private static final Logger log = LoggerFactory.getLogger(Server.class.getName());

    private static final Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    void run() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("Сервер успешно запущен");

            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
            }
        }
    }

    private void sendBroadcastMessage(Message message) {
        try {
            for (Connection connection : connectionMap.values()) {
                connection.send(message);
            }
        } catch (IOException e) {
            log.error("Не удалось отправить сообщение всем пользователям", e);
        }
    }

    private class ClientHandler extends Thread {
        private final Socket socket;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            log.info("Установлено новое соединение с удаленным адресом: {}", socket.getRemoteSocketAddress().toString());

            String userName = null;

            try (Connection connection = new Connection(socket)) {
                userName = handShake(connection);
                sendBroadcastMessage(new Message(MessageType.TEXT, "Пользователь " + userName + " зашел в чат"));
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);

                log.info("Пользователь {} зашел в чат", userName);

                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                log.error("Ошибка при обмене данными с удаленным адресом ", e);
            }

            if (userName != null) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.TEXT, "Пользователь " + userName + " покинул чат"));
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));

                log.info("Пользователь {} покинул чат", userName);
            }

            log.info("Соединение закрыто");
        }

        private String handShake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message name = connection.receive();

                if ((name.getType().equals(MessageType.USER_NAME)) &&
                        (!name.getData().isEmpty()) &&
                        (!connectionMap.containsKey(name.getData()))) {

                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    connectionMap.put(name.getData(), connection);
                    return name.getData();
                } else if (connectionMap.containsKey(name.getData())) {
                    connection.send(new Message(MessageType.NAME_USED));
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> connectionEntry : connectionMap.entrySet()) {
                String name = connectionEntry.getKey();
                Message message = new Message(MessageType.USER_ADDED, name);

                if (!name.equals(userName)) {
                    connection.send(message);
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();

                if (message.getType().equals(MessageType.TEXT)) {
                    String currentTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Calendar.getInstance().getTime());

                    String stringWithMessage = currentTime + " [" + userName + "]: " + message.getData();
                    Message formattedMessage = new Message(message.getType(), stringWithMessage);
                    sendBroadcastMessage(formattedMessage);
                } else {
                    log.info("Непредвиденный тип сообщения");
                }
            }
        }
    }
}
