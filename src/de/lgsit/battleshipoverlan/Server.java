package de.lgsit.battleshipoverlan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Host {
    public Server() {
        super();

        try (
                ServerSocket serverSocket = new ServerSocket(4444);
                Socket clientSocket = serverSocket.accept()
        ) {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            exchangeFleets(true);
            exchangeShots(true);
        } catch (IOException e) {
            System.out.println("Exception caught when listening for a connection!");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
