public class Position {
    private boolean occupied;
    private boolean hit;

    public Position() {
    hit = false;
    occupied = false;
    }
    public boolean isHit(){
        return hit;
    }
    public void setToHit(){
        hit = true;
    }

    public boolean isOccupied() {
        return occupied;
    }
}
