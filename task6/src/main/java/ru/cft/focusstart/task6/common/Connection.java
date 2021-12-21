package ru.cft.focusstart.task6.common;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Closeable {
    private final Socket socket;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Message message) throws IOException {
        synchronized (outputStream) {
            outputStream.writeObject(message);
            outputStream.flush();
        }
    }

    public Message receive() throws IOException, ClassNotFoundException {
        synchronized (inputStream) {
            return (Message) inputStream.readObject();
        }
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
