package ru.cft.focusstart.task6.client;

import ru.cft.focusstart.task6.client.controller.ClientController;
import ru.cft.focusstart.task6.client.model.Client;
import ru.cft.focusstart.task6.client.view.ClientView;

public class ClientMain {
    public static void main(String[] args) {
        new ClientMain().run();
    }

    private void run() {
        Client client = new Client();
        ClientController clientController = new ClientController(client);
        ClientView clientView = new ClientView(clientController);

        client.attachView(clientView);
        client.run();
    }
}