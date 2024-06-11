package de.lgsit.battleshipoverlan;

import java.util.Scanner;

public class Cli {
    private final Scanner stdin;

    public Cli() {
        stdin = new Scanner(System.in);
    }

    public String getServerAddress() {
        System.out.println("What's the Address of the game server?");
        String line = stdin.nextLine();
        System.out.println();
        return line;
    }
}
