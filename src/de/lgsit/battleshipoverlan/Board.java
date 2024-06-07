package de.lgsit.battleshipoverlan;

import java.util.Random;

public class Board {
    private Position[][] positions;
    private Ship[] ships;

    public Board(){

    }

    public Ship[] getShips() {
        return ships;
    }
    public void placeShips(Ship[] ships){
        this.ships = ships;
    }

    public void placeShipsRandomly() {
        Random random = new Random();
        int[] shipSizes = {5, 4, 4, 3, 3, 3, 2, 2, 2, 2};
        ships = new Ship[10];
        int index = 0;

        for (int size : shipSizes) {
            boolean placed = false;

            while (!placed) {
                boolean isLegal = true;
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                boolean horizontal = random.nextBoolean();

                if (horizontal) {
                    if (col + size > 10 || row + size >10) {
                        isLegal = false;
                    } else {
                        for (int i = 0; i < size; i++) {
                            if (positions[row][col + i].isOccupied()) {
                                isLegal = false;
                                break;
                            }
                        }
                        for (int i = 0; i < size && isLegal; i++) {
                            for (int dr = -1; dr <= 1; dr++) {
                                for (int dc = -1; dc <= 1; dc++) {
                                    int newRow = row + dr;
                                    int newCol = col + i + dc;
                                    if (newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 10) {
                                        if (positions[newRow][newCol].isOccupied()) {
                                            isLegal = false;
                                            break;
                                        }
                                    }
                                }
                                if (!isLegal) break;
                            }
                        }
                    }
                } else {
                    if (row + size > 10) {
                        isLegal = false;
                    } else {

                        for (int i = 0; i < size && isLegal; i++) {
                            for (int dr = -1; dr <= 1; dr++) {
                                for (int dc = -1; dc <= 1; dc++) {
                                    int newRow = row + i + dr;
                                    int newCol = col + dc;
                                    if (newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 10) {
                                        if (positions[newRow][newCol].isOccupied()) {
                                            isLegal = false;
                                            break;
                                        }
                                    }
                                }
                                if (!isLegal) break;
                            }
                        }
                    }
                }

                if (isLegal) {
                    ships[index] = new Ship(size, horizontal ? 0 : 1, new Position());

                    placed = true;
                    index++;
                }
            }
        }
    }
    public void setShotAt(int col, int row) {
        Position pos = positions[col][row];
        pos.setToHit();

        if (!pos.isOccupied()) {return;}

        for (Ship ship : ships) {
            for (int i = 0; i <= ship.getLenght(); i++) {
                if (ship.getOrientation() == 0) {
                    if (ship.getCentralPosition() == positions[col][row + i]) {
                        ship.hit();
                    }
                } else {
                    if (ship.getCentralPosition() == positions[col + i][row]) {
                        ship.hit();
                    }
                }
            }
        }
    }
    public boolean isHitAt(int col, int row){
        Position pos = positions[col][row];

        return pos.isHit();
    }
    public boolean isOccupied(int col, int row){
        Position pos = positions[col][row];
        return pos.isOccupied();
    }
}
