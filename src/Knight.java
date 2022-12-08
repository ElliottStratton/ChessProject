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
        Board board = currBoard;
        System.out.println("getX() = " + getX());
        if (white){
            if ((x == getX()+2) || (x == getX()-2)){
                if (((y == getY()-1) || (y == getY()+1)) && (board.getPiece(x,y) == null || !board.getPiece(x,y).white)){
                    System.out.println("something is wrong here");
                    return true;
                }
            }
            else if ((y == getY()+2) || (y == getY()-2)){
                if (((x == getX()-1) || (x == getX()+1)) && (board.getPiece(x,y) == null || !board.getPiece(x,y).white)){
                    System.out.println("this might also be the issue");
                    return true;
                }
            }
            System.out.println("move not possible");
            System.out.println("x = " + x);
            System.out.println("y = " + y);
            System.out.println("getX()+2 = " + getX()+2);
            return false;
        }
        else{
            if ((x == getX()+2) || (x == getX()-2)){
                if (((y == getY()-1) || (y == getY()+1)) && (board.getPiece(x,y) == null || board.getPiece(x,y).white)){
                    return true;
                }
            }
            else if ((y == getY()+2) || (y == getY()-2)){
                if (((x == getX()-1) || (x == getX()+1)) && (board.getPiece(x,y) == null || board.getPiece(x,y).white)){
                    return true;
                }
            }
        }
        return false;
    }
}
