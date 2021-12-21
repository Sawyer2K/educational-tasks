package ru.cft.focusstart.task6.client.controller;

import ru.cft.focusstart.task6.client.model.Client;

public record ClientController(Client client) {

    public void onSendButtonClicked(String message) {
        client.sendTextMessage(message);
    }

    public void handleName(String name) {
        client.setName(name);
    }

    public void handleServerAddress(String serverAddress) {
        client.setServerAddress(serverAddress);
    }

    public void handlePort(int port) {
        client.setPort(port);
    }
}