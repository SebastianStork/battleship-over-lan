package de.lgsit.battleshipoverlan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server extends Host {
    public Server() {
        super();

        try (
                ServerSocket serverSocket = new ServerSocket(4444);
                Socket clientSocket = serverSocket.accept();
        ) {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            out.println(getFleetSignal());
            setEnemyFleet(in.readLine());

            out.println(getShotSignal());

            String inputSignal;
            while ((inputSignal = in.readLine()) != null) {
                setEnemyShot(inputSignal);
                out.println(getShotSignal());
            }
        } catch (IOException e) {
            System.out.println("Exception caught when listening for a connection!");
            System.out.println(e.getMessage());
        }
    }
}
