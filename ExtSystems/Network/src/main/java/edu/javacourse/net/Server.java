package edu.javacourse.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(25225);
        try {
            while (true) {
                Socket client = socket.accept();
                handleRequest(client);
            }
        } finally {
            socket.close();
        }
    }

    private static void handleRequest(Socket client) {

    }
}
