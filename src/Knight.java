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

//    /**
//     * This returns a list of all possible moves for this specific piece
//     * @return ArrayList<String> is the arraylist of possible moves
//     * */
//    public ArrayList<String> possibleMoves(){
//        ArrayList<String> moves = new ArrayList<>();
//        for (int i = 0; i < 4; i++) { //checks each of the four directions in which it can move
//            if (i == 0){
//                if (isPossible(x+3, y+1)){
//                    moves.add(translateNum(new ArrayList<>(List.of(x+3,y+1))));
//                }
//                if (isPossible(x+3, y-1)){
//                    moves.add(translateNum(new ArrayList<>(List.of(x+3,y-1))));
//                }
//            }
//            if (i == 1){
//                if (isPossible(x-3, y+1)){
//                    moves.add(translateNum(new ArrayList<>(List.of(x-3,y+1))));
//                }
//                if (isPossible(x-3, y-1)){
//                    moves.add(translateNum(new ArrayList<>(List.of(x-3,y-1))));
//                }
//            }
//            if (i == 2){
//                if (isPossible(x+1, y+3)){
//                    moves.add(translateNum(new ArrayList<>(List.of(x+1,y+3))));
//                }
//                if (isPossible(x-1, y+3)){
//                    moves.add(translateNum(new ArrayList<>(List.of(x-1,y+3))));
//                }
//            }
//            if (i == 3){
//                if (isPossible(x+1, y-3)){
//                    moves.add(translateNum(new ArrayList<>(List.of(x+1,y-3))));
//                }
//                if (isPossible(x-1, y-3)){
//                    moves.add(translateNum(new ArrayList<>(List.of(x-1,y-3))));
//                }
//            }
//        }
//        return moves;
//    }

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
