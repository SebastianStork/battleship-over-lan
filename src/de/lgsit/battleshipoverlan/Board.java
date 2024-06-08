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

    public Position getPositionAt(int col, int row) {
        return positions[col][row];
    }

    private int[] getCoordinatesOf(Position position) {
        for (int colIndex = 0; colIndex < 10; colIndex++) {
            for (int rowIndex = 0; rowIndex < 10; rowIndex++) {
                if (positions[colIndex][rowIndex] == position) {
                    return new int[]{colIndex, rowIndex};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public Ship[] getShips() {
        return ships;
    }

    public void placeShips(Ship[] ships) {
        this.ships = ships;

        for (Ship ship: ships) {
            int[] centralCoordinates = getCoordinatesOf(ship.getCentralPosition());
            for (int i = 0; i < ship.getLength(); i++) {
                if (ship.getOrientation() == Orientation.HORIZONTAL) {
                    positions[centralCoordinates[0] + i][centralCoordinates[1]].setToOccupied();
                }
                if (ship.getOrientation() == Orientation.VERTICAL) {
                    positions[centralCoordinates[0]][centralCoordinates[1] + i].setToOccupied();
                }
            }
        }
    }

    public void placeShipsRandomly() {
        Random random = new Random();
        int[] shipSizes = {5, 4, 4, 3, 3, 3, 2, 2, 2, 2};
        Ship[] generatedShips = new Ship[10];
        int index = 0;

        for (int size : shipSizes) {
            boolean placed = false;
            while (!placed) {
                int col = random.nextInt(10);
                int row = random.nextInt(10);
                boolean horizontal = random.nextBoolean();

                if (isLegalShipPlacement(col, row, horizontal, size)) {
                    generatedShips[index] = new Ship(getPositionAt(col, row), horizontal ? Orientation.HORIZONTAL : Orientation.VERTICAL, size);
                    placed = true;
                    index++;
                }
            }
        }

        placeShips(generatedShips);
    }

    private boolean isLegalShipPlacement(int col, int row, boolean horizontal, int size) {
        if (!isInboundShipPlacement(col, row, horizontal, size)) {
            return false;
        }
        return isFreeShipPlacement(col, row, horizontal, size);
    }

    private boolean isInboundShipPlacement(int col, int row, boolean horizontal, int size) {
        if (horizontal) {
            return col + size <= 10;
        } else {
            return row + size <= 10;
        }
    }

    private boolean isFreeShipPlacement(int col, int row, boolean horizontal, int size) {
        for (int i = 0; i < size; i++) {
            for (int dc = -1; dc <= 1; dc++) {
                for (int dr = -1; dr <= 1; dr++) {
                    int newCol = col + (horizontal ? i : 0) + dc;
                    int newRow = row + (horizontal ? 0 : i) + dr;
                    if (newCol >= 0 && newCol < 10 && newRow >= 0 && newRow < 10) {
                        if (positions[newCol][newRow].isOccupied()) {
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

        if (!pos.isOccupied()) {
            return;
        }

        for (Ship ship : ships) {
            for (int i = 0; i <= ship.getLength(); i++) {
                if (ship.getOrientation() == Orientation.HORIZONTAL) {
                    if (ship.getCentralPosition() == positions[col + i][row]) {
                        ship.hit();
                    }
                }
                if (ship.getOrientation() == Orientation.VERTICAL) {
                    if (ship.getCentralPosition() == positions[col][row + i]) {
                        ship.hit();
                    }
                }
            }
        }
    }

    public boolean isHitAt(int col, int row) {;
        return positions[col][row].isHit();
    }

    public boolean isOccupied(int col, int row) {
        return positions[col][row].isOccupied();
    }
}
