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
    public void placeShipsRandomly(){

    }
    public void setShotAt(int col, int row) {
        Position pos = positions[col][row];
        pos.setToHit();
        if (pos.isOccupied()) {
            for (Ship ship : ships) {
                if (ship.getOrientation() == 0) {
                    for (int i = 0; i < ship.getLenght(); i++) {
                        if (ship.getCentralPosition() == positions[col][row + i]) {
                            ship.hit();
                        }
                    }
                } else {
                    for (int i = 0; i < ship.getLenght(); i++) {
                        if (ship.getCentralPosition() == positions[col + i][row]) {
                            ship.hit();
                        }
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
