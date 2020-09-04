package edu.javacourse.net;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 8; i++) {
            SimpleClient client = new SimpleClient(i);
            client.start();
        }
    }
}

class SimpleClient extends Thread {

    private static final String[] COMMAND = {
            "HELLO", "MORNING", "DAY", "EVENING"
    };
    private int cmdNumber;

    public SimpleClient(int cmdNumber) {
        this.cmdNumber = cmdNumber;
    }

    @Override
    public void run() {
//        System.out.println("Started:" + LocalDateTime.now());
        try (Socket client = new Socket("127.0.0.1", 25225);
             BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
            String command = COMMAND[cmdNumber % COMMAND.length];
            String builder = command + " " + "Slava";
            writer.write(builder);
            writer.newLine();
            writer.flush();

            String answer = reader.readLine();
            System.out.println("Client got string:" + answer);
//            System.out.println("Finished:" + LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}