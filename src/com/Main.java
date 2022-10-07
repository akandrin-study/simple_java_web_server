package com;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Thread serverThread = new Thread(() -> {
            HttpServer server = null;
            try {
                server = new HttpServer(80);
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

        try {
            serverThread.start();
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
