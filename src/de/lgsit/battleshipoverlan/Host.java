package de.lgsit.battleshipoverlan;

public class Host {
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
}
