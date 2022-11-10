public abstract class Piece {

    boolean white; //white is true
    int x;
    int y;
    Board currBoard;

    public Piece() {
    }

    public Piece(boolean color, int x, int y, Board b) {
        this.white = color;
        this.x = x;
        this.y = y;
        currBoard = b;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract boolean isPossible(int x, int y) throws IllegalArgumentException;

    public void move(int x, int y) {
        if(!sameSpot(x,y)) {
            if(!sameColorMove(x,y)) {
                if(isPossible(x, y)) {
                    currBoard.unOccupy(getX(), getY());
                    setPosition(x,y);
                    currBoard.occupy(this,x,y);
                }
            }
        }


    }

    public abstract int getNum();

    public boolean sameSpot(int x,int y) {
        if (x == this.x && y == this.y) {
            return true;
        } else { return false; }
    }

    public boolean sameColorMove(int x, int y) {
        if (currBoard.getPiece(x,y) != null) {
            if (white) {
                if (currBoard.getPiece(x, y).white) {
                    return true;
                }
            } else {
                if (!currBoard.getPiece(x, y).white) {
                    return true;
                }
            }
        }
        return false;
    }

}
