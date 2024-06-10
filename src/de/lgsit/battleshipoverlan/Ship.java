package de.lgsit.battleshipoverlan;

public class Ship {
    private Position centralPosition;
    private Orientation orientation;
    private int length;
    private int hits;

    public Ship(Position centralPosition, Orientation orientation, int length) {
        this.centralPosition = centralPosition;
        this.orientation = orientation;
        this.length = length;
    }

    public void hit() {
        hits++;
    }

    public boolean isSunk() {
        return hits >= length;
    }

    public Position getCentralPosition() {
        return centralPosition;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getLength() {
        return length;
    }
}
