package de.lgsit.battleshipoverlan;

public class Player {
    private final Board homeBoard;
    private final Board enemyBoard;
    private final Cli cli;

    public Player() {
        homeBoard = new Board();
        enemyBoard = new Board();
        cli = new Cli();

        homeBoard.placeShipsRandomly();
    }

    public Board getHomeBoard() {
        return homeBoard;
    }

    public Board getEnemyBoard() {
        return enemyBoard;
    }

    public Cli getCli() {
        return cli;
    }

    public int[] getShot() {
        cli.announceOwnTurn();

        int[] coordinates = cli.getCoordinates();
        int col = coordinates[0];
        int row = coordinates[1];
        Position shotPosition = enemyBoard.getPositionAt(col, row);

        while (shotPosition.isHit()) {
            cli.announceRepeatShot();

            coordinates = cli.getCoordinates();
            col = coordinates[0];
            row = coordinates[1];
            shotPosition = enemyBoard.getPositionAt(col, row);
        }

        enemyBoard.setShotAt(col, row);

        if (shotPosition.isOccupied()) {
            cli.announceHit();
            if (enemyBoard.getShipAt(col, row).isSunk()) {
                cli.announceSunkShip();
            }
        } else {
            cli.announceMiss();
        }

        cli.announceEnemyTurn();

        return new int[]{col, row};
    }

    public void setEnemyShot(int col, int row) {
        homeBoard.setShotAt(col, row);

        cli.announceEnemyShot(col, row);
        if (homeBoard.getPositionAt(col, row).isOccupied()) {
            cli.announceHit();
            if (homeBoard.getShipAt(col, row).isSunk()) {
                cli.announceSunkShip();
            }
        } else {
            cli.announceMiss();
        }
    }
}
