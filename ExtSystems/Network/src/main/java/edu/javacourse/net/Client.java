package edu.javacourse.net;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            sendRequest();
        }
    }

    private static void sendRequest() throws IOException {
        try (Socket client = new Socket("127.0.0.1", 25225);
             BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
            String builder = "Slava";
            writer.write(builder);
            writer.newLine();
            writer.flush();

            String answer = reader.readLine();
            System.out.println("Client got string:" + answer);
        }
    }
}

