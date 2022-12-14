package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public boolean GetMessage() throws IOException
    {

        clientSocket = serverSocket.accept();

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String message = in.readLine();
        boolean status;
        if (message.equals("end"))
        {
            out.println("bye");
            status = false;
        }
        else {
            out.println("I get: " + message);
            status = true;
        }

        in.close();
        out.close();
        clientSocket.close();

        return status;
    }

    public void Stop() throws IOException {
        serverSocket.close();
    }

}
