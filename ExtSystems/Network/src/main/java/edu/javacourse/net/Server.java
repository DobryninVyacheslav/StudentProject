package edu.javacourse.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
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
            StringBuilder builder = new StringBuilder("Hello, ");
            String userName = reader.readLine();
            System.out.println("Server got string:" + userName);
            Thread.sleep(2000);
            builder.append(userName);
            writer.write(builder.toString());
            writer.newLine();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
