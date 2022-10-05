package com;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Thread serverThread = new Thread(() -> {
            Server server = null;
            try {
                server = new Server(7777);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }


            boolean needToContinue = true;

            while (needToContinue) {
                try {
                    needToContinue = server.GetMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                server.Stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread clientThread = new Thread(() ->
        {
            try {
                Client client = new Client("127.0.0.1", 7777);

                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();
                    client.SendMessage(line);
                    String answer = client.RecvMessage();
                    System.out.println(answer);
                    if (answer.equals("bye")) {
                        return;
                    }
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        });

        try {
            serverThread.start();
            clientThread.start();
            serverThread.join();
            clientThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
