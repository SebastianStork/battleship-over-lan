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

    public void announceHit(boolean isSunk) {
        System.out.println("It's a hit!");
        if (isSunk) {
            System.out.println("The ship has sunk!");
        }
        System.out.println();
    }

    public void announceMiss() {
        System.out.println("It's a miss!");
        System.out.println();
    }

    public void announceRepeatShot() {
        System.out.println("This position has already been shot at! Please choose a different position.");
    }

    public void announceWinner(boolean isEnemy) {
        if (isEnemy) {
            System.out.println("Your enemy is the winner!");
        } else {
            System.out.println("You are the winner!");
        }
    }

    public void announceEnemyShot(int col, int row) {
        System.out.println("Your enemy shot at " + convertCoordinatesToCode(col, row) + ".");
    }

    private String convertCoordinatesToCode(int col, int row) {
        return letters.charAt(col) + Integer.toString(row + 1);
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

    private int[] convertCodeToCoordinates(String code) {
        if (code.length() < 2) {
            return new int[]{-1, -1};
        }

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

    public void printBoards(Board enemyBoard, Board homeBoard) {
        String[] printableEnemyBoard = getPrintableBoard(enemyBoard, false);
        String[] printableHomeBoard = getPrintableBoard(homeBoard, true);
        for (int i = 0; i < printableEnemyBoard.length; i++) {
            System.out.println(printableEnemyBoard[i] + "          " + printableHomeBoard[i]);
        }
        System.out.println();
    }

    private String[] getPrintableBoard(Board board, boolean isHome) {
        String[] printedBoard = new String[13];

        if (isHome) {
            printedBoard[0] = "          Your own board         ";
        } else {
            printedBoard[0] = "        Your enemy's board       ";
        }
        printedBoard[1] = "";

        printedBoard[2] = "   ";
        for (int i = 0; i < 10; i++) {
            printedBoard[2] += " " + convertCoordinatesToCode(i, 0).charAt(0) + " ";
        }

        for (int i = 0; i < 10; i++) {
            printedBoard[i + 3] = " " + (i + 1) + " ";
            for (int j = 0; j < 10; j++) {
                printedBoard[i + 3] += getPositionSymbol(board.isHitAt(j, i), board.isOccupiedAt(j, i), isHome);
            }
        }
        printedBoard[12] = printedBoard[12].substring(1);

        return printedBoard;
    }

    private String getPositionSymbol(boolean isHit, boolean isOccupied, boolean isHome) {
        if (isHit) {
            if (isOccupied) {
                return " X ";
            }
            return " ~ ";
        }
        if (isOccupied && isHome) {
            return " □ ";
        }
        return "   ";
    }
}
