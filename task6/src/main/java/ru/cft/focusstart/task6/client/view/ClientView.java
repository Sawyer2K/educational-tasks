package ru.cft.focusstart.task6.client.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task6.client.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class ClientView extends JFrame {

    private static final Logger log = LoggerFactory.getLogger(ClientView.class.getName());

    private final ClientController clientController;
    private final WelcomeMessagePane welcomeMessagePane = new WelcomeMessagePane();

    private JTextField textField;
    private JTextArea messages;
    private JTextArea users;

    public ClientView(ClientController clientController) {
        super("Чат");
        this.clientController = clientController;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setResizable(false);
        setLayout(new GridBagLayout());

        invalidateView();
        setLocationRelativeTo(null);
    }

    private void invalidateView() {
        textField = new JTextField(50);
        messages = new JTextArea(10, 50);
        users = new JTextArea(10, 9);

        JButton sendButton = new JButton("Отправить");
        sendButton.addActionListener(e -> {
            clientController.onSendButtonClicked(textField.getText());
            textField.setText("");
        });

        textField.setEditable(true);
        messages.setEditable(false);
        users.setEditable(false);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(new JScrollPane(messages), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        this.add(new JScrollPane(users), gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.add(textField, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        this.add(sendButton, gridBagConstraints);

        this.pack();
        this.setVisible(true);
    }

    public void renderServerAddressRequest() {
        log.info("Запрашивается адрес сервера");

        String serverAddress = welcomeMessagePane.serverIpAsking();
        clientController.handleServerAddress(serverAddress);
    }

    public void renderPortRequest() {
        log.info("Запрашивается порт");

        int port = welcomeMessagePane.serverPortAsking();
        clientController.handlePort(port);
    }


    public void renderNameRequest() {
        log.info("Запрашивается имя пользователя");

        String name = welcomeMessagePane.userNameAsking();
        clientController.handleName(name);
    }

    public void renderRefreshUsers(Set<String> allUserNames) {
        log.info("Обновляется список пользователей");

        StringBuilder allUserNamesStringBuilder = new StringBuilder();

        for (String name : allUserNames) {
            allUserNamesStringBuilder.append(name).append(System.lineSeparator());
        }

        users.setText(allUserNamesStringBuilder.toString());
    }

    public void renderRefreshMessages(String message) {
        log.info("Обновляются сообщения");

        messages.append(message);
        messages.append(System.lineSeparator());
    }
}