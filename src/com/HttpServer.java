package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public HttpServer(int port) throws IOException {
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
            String str = "<!DOCTYPE html>\r\n" +
                    "<html>\r\n" +
                    "<head>\r\n" +
                    "<title>My page</title>\r\n" +
                    "<head>\r\n" +
                    "<body>\r\n" +
                    "<h1 style=\"color: #FFAAAA;\">My page</h1>\r\n" +
                    "<p>Hello</p>\r\n" +
                    "<p>Bye</p>\r\n" +
                    "</body>\r\n" +
                    "</html>\r\n";
            out.println("HTTP/1.1 200 Ok\r\n" +
                        "Server: MyFuckingServer\r\n" +
                        "Date: Wed, 05 Oct 2022 20:00:00 GMT\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Connection: close\r\n" +
                        "Content-Length: " + str.length() + "\r\n\r\n" + str);

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
