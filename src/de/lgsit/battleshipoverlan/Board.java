package de.lgsit.battleshipoverlan;

import java.util.Random;

public class Board {
    private Position[][] positions;
    private Ship[] ships;

    public Board(){
        for(int i = 0; i<=10; i++){
            for(int j = 0; j <=10; j++){
                Position[][] positions = new Position[i][j];
            }
        }

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
        Ship[] generatedShips = new Ship[10];
        int index = 0;

        for (int size : shipSizes) {
            boolean placed = false;
            while (!placed) {
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                boolean horizontal = random.nextBoolean();

                if (canPlaceShip(size, row, col, horizontal)) {
                    generatedShips[index] = new Ship(size, horizontal ? 0 : 1, new Position());
                    placed = true;
                    index++;
                }
            }
        }

        placeShips(generatedShips);
    }

    private boolean canPlaceShip(int size, int row, int col, boolean horizontal) {
        if (horizontal) {
            if (col + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (positions[row][col + i].isOccupied()) return false;
            }
            return checkSurroundings(row, col, size, horizontal);
        } else {
            if (row + size > 10) return false;
            for (int i = 0; i < size; i++) {
                if (positions[row + i][col].isOccupied()) return false;
            }
            return checkSurroundings(row, col, size, horizontal);
        }
    }

    private boolean checkSurroundings(int row, int col, int size, boolean horizontal) {
        for (int i = 0; i < size; i++) {
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    int newRow = row + (horizontal ? 0 : i) + dr;
                    int newCol = col + (horizontal ? i : 0) + dc;
                    if (newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 10) {
                        if (positions[newRow][newCol].isOccupied()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public void setShotAt(int col, int row) {
        Position pos = positions[col][row];
        pos.setToHit();

        if (!pos.isOccupied()) {return;}

        for (Ship ship : ships) {
            for (int i = 0; i <= ship.getLength(); i++) {
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
