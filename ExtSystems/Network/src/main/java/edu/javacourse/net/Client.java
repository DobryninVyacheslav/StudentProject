package edu.javacourse.net;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 1000; i++) {
            SimpleClient client = new SimpleClient();
            client.start();
        }
    }
}

class SimpleClient extends Thread {
    @Override
    public void run() {
        System.out.println("Started:" + LocalDateTime.now());
        try (Socket client = new Socket("127.0.0.1", 25225);
             BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
            String builder = "Slava";
            writer.write(builder);
            writer.newLine();
            writer.flush();

            String answer = reader.readLine();
            System.out.println("Client got string:" + answer);
            System.out.println("Finished:" + LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}