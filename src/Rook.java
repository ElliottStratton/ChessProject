import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
    /**
     * default constructor
     *
     * @param color player
     * @param x     initial x position
     * @param y     initial y position
     * @param b     board that the pawn will be on
     */
    public Rook(boolean color, int x, int y, Board b) {
        super(color, x, y, b);
        b.occupy(this,x,y);
    }

    /**
     * @param x     x position of desired move
     * @param y     y position of desired move
     * */
    public void move(int x,int y) {
            super.move(x,y);
    }

    /**
     * Returns a list of all possible moves for this piece
     * */
    public ArrayList<String> possibleMoves(){
        ArrayList<String> moves = new ArrayList<>();
        ArrayList<Integer> move = new ArrayList<>();
        for (int i = 0; i < 4; i++) { //loop to control how many in directions to search
            inner: for (int j = 0; j < 8; j++) { //Loop to control how many spaces forward to check
                String numLetLocation = "";
                if(i == 0){
                    if (this.isPossible(x+i, y)){
                        move = new ArrayList<>(List.of(x+i, y));
                    }
                    else{
                        break inner;
                    }
                }
                else if (i == 1){
                    if (this.isPossible(x-i, y)){
                        move = new ArrayList<>(List.of(x-i, y));
                    }
                    else{
                        break inner;
                    }
                }
                else if (i == 2){
                    if (this.isPossible(x, y+i)){
                        move = new ArrayList<>(List.of(x, y+i));
                    }
                    else{
                        break inner;
                    }
                }
                else if (i == 3){
                    if (this.isPossible(x, y-i)){
                        move = new ArrayList<>(List.of(x, y-i));
                    }
                    else{
                        break inner;
                    }
                }
                if (!move.isEmpty()){
                    moves.add(translateNum(move)); //Add this current location in Chess notation
                }
            }
        }
        return moves;
    }

    /**
     * Returns true if a move is possible
     * @param x     x position of desired move
     * @param y     y position of desired move
     */
    public boolean isPossible(int x, int y) {
        boolean possible = false;
        if (sameSpot(x,y) || sameColorMove(x,y) || moveOutsideBoard(x,y)){
            return false;
        }
        else if(currBoard.getPiece(x,y) == null || currBoard.getPiece(x,y).white != this.white){
            return true;
        }
       return possible;
    }
}
