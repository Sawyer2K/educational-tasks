package ru.cft.focusstart.task6.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerMain {

    private static final Logger log = LoggerFactory.getLogger(ServerMain.class.getName());

    public static void main(String[] args) {
        try {
            ServerConfigurator serverConfigurator = new ServerConfigurator();
            Server server = new Server(serverConfigurator.getPort());
            server.run();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
