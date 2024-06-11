package de.lgsit.battleshipoverlan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Host {
    BufferedReader in;
    PrintWriter out;
    Player player;

    public Host() {
        player = new Player();
    }

    String getFleetSignal() {
        Board board = player.getHomeBoard();
        Ship[] ships = board.getShips();

        StringBuilder signal = new StringBuilder("FLEET:");
        String seperator = "";

        for (Ship ship : ships) {
            signal.append(seperator);
            seperator = "|";

            int[] coordinates = board.getCoordinatesOf(ship.getCentralPosition());
            signal.append(coordinates[0]).append(",").append(coordinates[1]).append(",");
            signal.append(ship.getOrientation().name()).append(",");
            signal.append(ship.getLength());
        }

        return signal.toString();
    }

    void setEnemyFleet(String fleetSignal) {
        if (!fleetSignal.startsWith("FLEET:")) {
            throw new RuntimeException("Error: invalid signal!");
        }
        fleetSignal = fleetSignal.replaceFirst("FLEET:", "");

        Board board = player.getEnemyBoard();
        Ship[] ships = new Ship[10];
        int index = 0;

        String[] shipSignals = fleetSignal.split("\\|");
        for (String shipSignal : shipSignals) {
            String[] shipAttributes = shipSignal.split(",");

            Position centralPosition = board.getPositionAt(Integer.parseInt(shipAttributes[0]), Integer.parseInt(shipAttributes[1]));
            Orientation orientation = Orientation.valueOf(shipAttributes[2]);
            int length = Integer.parseInt(shipAttributes[3]);

            ships[index] = new Ship(centralPosition, orientation, length);
            index++;
        }

        player.getEnemyBoard().placeShips(ships);
    }

    void exchangeFleets(boolean begin) throws IOException {
        System.out.println("Generating fleet...");
        player.getHomeBoard().placeShipsRandomly();
        System.out.println("Exchanging fleet...");
        if (begin) {
            out.println(getFleetSignal());
            setEnemyFleet(in.readLine());
        }
        else {
            setEnemyFleet(in.readLine());
            out.println(getFleetSignal());
        }
    }

    String getShotSignal() {
        int[] coordinates = player.getShot();
        return "SHOT:" + coordinates[0] + "," + coordinates[1];
    }

    void setEnemyShot(String shotSignal) {
        if (!shotSignal.startsWith("SHOT:")) {
            throw new RuntimeException("Error: invalid signal!");
        }
        shotSignal = shotSignal.replaceFirst("SHOT:", "");

        String[] coordinates = shotSignal.split(",");
        int col = Integer.parseInt(coordinates[0]);
        int row = Integer.parseInt(coordinates[1]);

        player.setEnemyShot(col, row);
    }

    void exchangeShots(boolean begin) throws IOException {
        System.out.println("Start!");
        System.out.println();

        if (begin) {
            out.println(getShotSignal());
        }

        player.getCli().announceEnemyTurn();
        String inputSignal;
        while ((inputSignal = in.readLine()) != null) {
            setEnemyShot(inputSignal);
            out.println(getShotSignal());

            player.getCli().announceEnemyTurn();
        }
    }
}
