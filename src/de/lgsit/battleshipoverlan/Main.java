package de.lgsit.battleshipoverlan;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Battleship-Over-LAN!");
        System.out.println();

        System.out.println("Would you like to");
        System.out.println("(h) host a new game?");
        System.out.println("(j) join an existing game?");
        System.out.println("(q) quit the game?");
        System.out.println();

        System.out.print("Choice [hjq]: ");
        Scanner stdin = new Scanner(System.in);
        String line = stdin.nextLine();

        while (!Arrays.asList("h", "j", "q").contains(line)) {
            System.out.printf("'%s' is invalid!\r\n", line);
            System.out.print("Choice [hjq]: ");
            line = stdin.nextLine();
        }

        switch (line) {
            case "h":
                new Server();
                break;
            case "j":
                new Client();
                break;
            case "q":
                System.exit(0);
        }
    }
}