package de.lgsit.battleshipoverlan;

public class Ship {
    private int length;
    private Orientation orientation;
    private Position centralPosition;
    private int hits;

    public Ship(int length, Orientation orientation, Position centralPosition){
        this.length = length;
        this.orientation = orientation;
        this.centralPosition = centralPosition;
    }

    public void hit(){
        hits++;
    }

    public boolean isSunk(){
       return hits>= length;
    }

    public Position getCentralPosition(){
        return centralPosition;
    }

    public int getLength() {
        return length;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
