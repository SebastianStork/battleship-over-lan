package de.lgsit.battleshipoverlan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Host {
    public Client() {
        super();

        String hostName = player.getCli().getServerAddress();

        try (
                Socket clientSocket = new Socket(hostName, 4444)
        ) {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            setEnemyFleet(in.readLine());
            out.println(getFleetSignal());

            String inputSignal;
            while ((inputSignal = in.readLine()) != null) {
                setEnemyShot(inputSignal);
                out.println(getShotSignal());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
