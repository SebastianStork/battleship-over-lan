package de.lgsit.battleshipoverlan;

public class Ship {
    private int lenght;
    private int orientation;
    private Position centralPosition;
    private int hits;
    //0 vertikal central = oben
    //1 horinzontila central = links

    public Ship(int lenght, int orientation, Position centralPosition){
        this.lenght = lenght;
        this.orientation = orientation;
        this.centralPosition = centralPosition;
    }
    public void hit(){
        hits++;
    }
    public boolean isSunk(){
       return hits>= lenght;
    }
    public Position getCentralPosition(){
        return centralPosition;
    }

    public int getLenght() {
        return lenght;
    }

    public int getOrientation() {
        return orientation;
    }
}
