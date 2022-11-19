import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private boolean firstMove;
    /**
     * default constructor
     *
     * @param color player
     * @param x     initial x position
     * @param y     initial y position
     * @param b     board that the pawn will be on
     */
    public Pawn(boolean color, int x, int y, int value, Board b) {
        super(color, x, y, value, b);
        b.occupy(this, x, y);
        firstMove = true;
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        firstMove = false;
    }

    /**
     * This returns a list of all possible moves for this specific piece
     * It is assumed that white is always on the bottom
     * @return ArrayList<String> is the arraylist of possible moves
     * */
    public ArrayList<String> possibleMoves() {
        ArrayList<String> moves = new ArrayList<>();
        if (white){
            if (firstMove){
                if (isPossible(x,y+1)){
                    moves.add(translateNum(new ArrayList<>(List.of(x, y+1))));
                }
                if (isPossible(x-1,y+1)){
                    moves.add(translateNum(new ArrayList<>(List.of(x-1, y+1))));
                }
                if (isPossible(x+1,y+1)){
                    moves.add(translateNum(new ArrayList<>(List.of(x+1, y+1))));
                }
            }
            else{
                if (isPossible(x, y+2)){
                    moves.add(translateNum(new ArrayList<>(List.of(x, y+2))));
                }
            }
        }
        if (!white){
            if (firstMove){
                if (isPossible(x,y-1)){
                    moves.add(translateNum(new ArrayList<>(List.of(x, y-1))));
                }
                if (isPossible(x-1,y-1)){
                    moves.add(translateNum(new ArrayList<>(List.of(x-1, y-1))));
                }
                if (isPossible(x+1,y-1)){
                    moves.add(translateNum(new ArrayList<>(List.of(x+1, y-1))));
                }
            }
            else{
                if (isPossible(x, y-2)){
                    moves.add(translateNum(new ArrayList<>(List.of(x, y-2))));
                }
            }
        }
        return moves;
    }


    /**
     * Takes a possible move and checks if it is valid
     * @param x is the x coordinate of a possible move
     * @param y is the y coordinate of a possible move
     * @return true if a specific move is possible
     * */
    public boolean isPossible(int x, int y) {
        boolean possible = false;
        if (sameSpot(x,y) || sameColorMove(x,y) || moveOutsideBoard(x,y)){
            return false;
        }
        else if(currBoard.getPiece(x,y) == null || currBoard.getPiece(x,y).white == this.white){
            return true;
        }
        return possible;
    }
}