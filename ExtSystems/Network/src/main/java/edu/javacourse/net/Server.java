package edu.javacourse.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket socket = new ServerSocket(25225, 2000)) {
            System.out.println("Server is started");
            while (true) {
                Socket client = socket.accept();
                new SimpleServer(client).start();
            }
        }
    }
}

class SimpleServer extends Thread {

    private Socket client;

    public SimpleServer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        handleRequest();
    }

    private void handleRequest() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
            String request = reader.readLine();
            String[] lines = request.split("\\s+");
            String command = lines[0];
            String userName = lines[1];
            System.out.println("Server got string 1:" + command);
            System.out.println("Server got string 2:" + userName);
//            Thread.sleep(2000);
            String response = buildResponse(command, userName);
            writer.write(response);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildResponse(String command, String userName) {
        switch (command) {
            case "HELLO":
                return "Hello, " + userName;
            case "MORNING":
                return "Good morning, " + userName;
            case "DAY":
                return "Good day, " + userName;
            case "EVENING":
                return "Good evening, " + userName;
            default:
                return "Hi, " + userName;
        }
    }
}
