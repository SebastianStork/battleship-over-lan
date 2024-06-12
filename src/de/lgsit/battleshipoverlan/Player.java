package de.lgsit.battleshipoverlan;

public class Player {
    private final Board homeBoard;
    private final Board enemyBoard;
    private final Cli cli;

    public Player() {
        homeBoard = new Board();
        enemyBoard = new Board();
        cli = new Cli();
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
        cli.printBoards(enemyBoard, homeBoard);
        cli.announceOwnTurn();

        int[] coordinates = cli.getCoordinates();
        int col = coordinates[0];
        int row = coordinates[1];

        while (enemyBoard.isHitAt(col, row)) {
            cli.announceRepeatShot();

            coordinates = cli.getCoordinates();
            col = coordinates[0];
            row = coordinates[1];
        }

        enemyBoard.setShotAt(col, row);

        if (enemyBoard.isOccupiedAt(col, row)) {
            cli.announceHit(enemyBoard.getShipAt(col, row).isSunk());
        } else {
            cli.announceMiss();
        }

        return new int[]{col, row};
    }

    public void setEnemyShot(int col, int row) {
        homeBoard.setShotAt(col, row);

        cli.announceEnemyShot(col, row);
        if (homeBoard.isOccupiedAt(col, row)) {
            cli.announceHit(homeBoard.getShipAt(col, row).isSunk());
        } else {
            cli.announceMiss();
        }
    }

    public void checkForWin() {
        boolean win = true;
        for (Ship ship : enemyBoard.getShips()) {
            if (!ship.isSunk()) {
                win = false;
                break;
            }
        }
        if (win) {
            cli.announceWinner(true);
            System.exit(0);
        }

        win = true;
        for (Ship ship : homeBoard.getShips()) {
            if (!ship.isSunk()) {
                win = false;
                break;
            }
        }
        if (win) {
            cli.announceWinner(false);
            System.exit(0);
        }
    }
}
