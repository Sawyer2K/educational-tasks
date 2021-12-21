package ru.cft.focusstart.task6.client.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task6.client.view.ClientView;
import ru.cft.focusstart.task6.common.Connection;
import ru.cft.focusstart.task6.common.Message;
import ru.cft.focusstart.task6.common.MessageType;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Client {

    private static final Logger log = LoggerFactory.getLogger(Client.class.getName());

    private Set<String> allUserNames = new HashSet<>();
    private String name;
    private String serverAddress;
    private int port;
    private Connection connection;
    private final ViewNotifier viewNotifier = new ViewNotifier();

    public void setName(String name) {
        this.name = name;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void attachView(ClientView clientView) {
        viewNotifier.attachView(clientView);
    }

    public void run() {
        new SocketThread().start();
    }

    public void sendTextMessage(String text) {
        try {
            if (!text.isEmpty()) {
                Message message = new Message(MessageType.TEXT, text);
                connection.send(message);
            }
        } catch (IOException e) {
            log.info("Невозможно отправить сообщение");
        }
    }

    private void requestServerAddress() {
        viewNotifier.notifyViewsAboutServerAddressRequest();
    }

    private void requestPort() {
        viewNotifier.notifyViewsAboutPortRequest();
    }

    private void requestName() {
        viewNotifier.notifyViewsAboutNameRequest();
    }

    private class SocketThread extends Thread {
        @Override
        public void run() {
            Socket socket;
            requestServerAddress();
            requestPort();

            try {
                socket = new Socket(serverAddress, port);
                Client.this.connection = new Connection(socket);
                handShake();
                clientMessageProcessing();
            } catch (IOException | ClassNotFoundException e) {
                log.error(e.getMessage(), e);
            }
        }

        private void handShake() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();

                if (message.getType() == MessageType.NAME_REQUEST) {
                    requestName();
                    connection.send(new Message(MessageType.USER_NAME, name));
                } else if (message.getType() == MessageType.NAME_USED) {
                    JOptionPane.showMessageDialog(null, "Имя занято", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    return;
                } else {
                    throw new IOException("Непредвиденный тип сообщения");
                }
            }
        }

        private void clientMessageProcessing() throws IOException, ClassNotFoundException {
            boolean isYourName = true;

            while (true) {
                Message message = connection.receive();

                if (message.getType() == MessageType.TEXT) {
                    viewNotifier.notifyViewsAboutNewMessage(message.getData());
                } else if (message.getType() == MessageType.USER_ADDED) {
                    if (isYourName) {
                        allUserNames.add(message.getData() + " (вы)");
                        isYourName = false;
                    } else {
                        allUserNames.add(message.getData());
                    }
                    viewNotifier.notifyViewsAboutUsersUpdate(allUserNames);
                } else if (message.getType() == MessageType.USER_REMOVED) {
                    allUserNames.remove(message.getData());
                    viewNotifier.notifyViewsAboutUsersUpdate(allUserNames);
                } else {
                    throw new IOException("Непредвиденный тип сообщения");
                }
            }
        }
    }
}