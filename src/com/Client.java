package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    String ip;
    int port;

    public Client(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
    }

    public void SendMessage(String msg) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out.println(msg);
    }

    public String RecvMessage() throws IOException
    {
        String resp = in.readLine();
        stopConnection();
        return resp;
    }

    private void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
