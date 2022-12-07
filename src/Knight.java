import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{
    /**
     * default constructor
     * @param color player
     * @param x initial x position
     * @param y initial y position
     * @param b board that the pawn will be on
     */
    public Knight(boolean color, int x, int y, int value, Board b) {
        super(color, x, y, value, b);
        b.occupy(this,x,y);
    }

    /**
     * Takes a possible move and checks if it is valid
     * @param x is the x coordinate of a possible move
     * @param y is the y coordinate of a possible move
     * @return true if a specific move is possible
     * */
    public boolean isPossible(int x, int y) {
        if (x == this.x && y == this.y) {
            return false;
        }
        if(sameColorMove(x,y)) {
            return false;
        }
        if ((x == getX() + 2) || (x == getX() - 2)) {
            if ((y == getY() - 1) || (y == getY() + 1)) {
                return true;
            }
        }
        if ((y == getY() + 2) || (y == getY() - 2)) {
            if ((x == getX() - 1) || (x == getX() + 1)) {
                return true;
            }
        }
        return false;
    }
}
