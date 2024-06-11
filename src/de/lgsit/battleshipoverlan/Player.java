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
}
