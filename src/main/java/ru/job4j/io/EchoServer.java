package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String line = in.readLine();
                    if (line.contains("?msg=Exit")) {
                        server.close();
                    } else if (line.contains("?msg=Hello")) {
                        out.write("Hello".getBytes());
                    } else if (line.contains("?msg=")) {
                        out.write("What".getBytes());
                    }
                    out.flush();
                }
            }
        }  catch (IOException e) {
            LOG.error("Socket error ", e);
        }
    }
}