package de.lgsit.battleshipoverlan;

public class Ship {
    private int length;
    private int orientation;
    private Position centralPosition;
    private int hits;
    //0 vertikal central = oben
    //1 horinzontila central = links

    public Ship(int length, int orientation, Position centralPosition){
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

    public int getOrientation() {
        return orientation;
    }
}
