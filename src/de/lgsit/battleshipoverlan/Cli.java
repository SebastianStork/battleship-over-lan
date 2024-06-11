package de.lgsit.battleshipoverlan;

import java.util.Scanner;

public class Cli {
    private final Scanner stdin;
    private final String letters = "ABCDEFGHIJ";

    public Cli() {
        stdin = new Scanner(System.in);
    }

    public String getServerAddress() {
        System.out.println("What's the Address of the game server?");
        String line = stdin.nextLine();
        System.out.println();
        return line;
    }

    public void announceOwnTurn() {
        System.out.println("It's your turn! Where do you want to shoot at?");
    }

    public void announceEnemyTurn() {
        System.out.println("It's your enemy's turn! Waiting...");
    }

    public void announceHit() {
        System.out.println("It's a hit!");
        System.out.println();
    }

    public void announceMiss() {
        System.out.println("It's a miss!");
        System.out.println();
    }

    private String convertCoordinatesToCode(int col, int row) {
        return letters.charAt(col) + Integer.toString(row + 1);
    }

    public void announceEnemyShot(int col, int row) {
        System.out.println("Your enemy shot at " + convertCoordinatesToCode(col, row) + ".");
    }

    private int[] convertCodeToCoordinates(String code) {
        String letter = String.valueOf(code.charAt(0));
        if (!letters.contains(letter)) {
            return new int[]{-1, -1};
        }

        String numbers = "12345678910";
        String number = code.substring(1);
        if (!numbers.contains(number)) {
            return new int[]{-1, -1};
        }

        return new int[]{letters.indexOf(letter), Integer.parseInt(number) - 1};
    }

    private boolean isLegalCoordinates(int col, int row) {
        return (col >= 0 && col < 10 && row >= 0 && row < 10);
    }

    public int[] getCoordinates() {
        System.out.print("Choice [A-J][1-10]: ");
        String code = stdin.nextLine();
        int[] coordinates = convertCodeToCoordinates(code);

        while (!isLegalCoordinates(coordinates[0], coordinates[1])) {
            System.out.println("'" + code + "' is invalid!");
            System.out.print("Choice [A-J][1-10]: ");
            code = stdin.nextLine();
            coordinates = convertCodeToCoordinates(code);
        }

        return coordinates;
    }

    public void announceRepeatShot() {
        System.out.println("This position has already been shot at! Please choose a different position.");
    }

    public void announceSunkShip() {
        System.out.println("The ship has sunk!");
        System.out.println();
    }
}
