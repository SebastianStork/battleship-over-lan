package de.lgsit.battleshipoverlan;

public class Position {
    private boolean occupied;
    private boolean hit;

    public Position() {
        hit = false;
        occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setToOccupied(){
        occupied = true;
    }

    public boolean isHit(){
        return hit;
    }

    public void setToHit(){
        hit = true;
    }
}
