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
    public Rook(boolean color, int x, int y, int value, Board b) {
        super(color, x, y, value, b);
        b.occupy(this,x,y);
    }



    /**
     * Takes a possible move and checks if it is valid
     * @param newX is the x coordinate of a possible move
     * @param newY is the y coordinate of a possible move
     * @return true if a specific move is possible
     * */
    public boolean isPossible(int newX, int newY) {
        int dx = newX - x;
        int dy = newY - y;
        Board b = currBoard;

        if((dx == 0) ^ (dy == 0)) {
            if (dx == 0) {
                if (dy > 0) {
                    boolean isPossible = true;
                    for (int d = 1; d <= dy; d++) {
                        Piece p = b.getPiece(newX, y + d);
                        if (p == null) {
                            continue;
                        }

                        if (white) {
                            if (p.white) {
                                isPossible = false;
                            } else if (!(p.white) && d != dy) {
                                isPossible = false;
                            }
                        } else {
                            if (!(p.white)) {
                                isPossible = false;
                            } else if (p.white && d != dy) {
                                isPossible = false;
                            }
                        }


                    }
                    return isPossible;
                } else {
                    boolean isPossible = true;
                    for (int d = -1; d >= dy; d--) {
                        Piece p = b.getPiece(newX, y + d);
                        if (p == null) {
                            continue;
                        }

                        if (white) {
                            if (p.white) {
                                isPossible = false;
                            } else if (!(p.white) && d != dy) {
                                isPossible = false;
                            }
                        } else {
                            if (!(p.white)) {
                                isPossible = false;
                            } else if (p.white && d != dy) {
                                isPossible = false;
                            }
                        }
                    }
                    return isPossible;
                }

            }
            else   // dy == 0
            {
                if(dx > 0)
                {
                    boolean isPossible = true;
                    for(int d = 1; d <= dx; d++)
                    {
                        Piece p = b.getPiece(x + d, newY);
                        if (p == null) {
                            continue;
                        }

                        if (white) {
                            if (p.white) {
                                isPossible = false;
                            } else if (!(p.white) && d != dx) {
                                isPossible = false;
                            }
                        } else {
                            if (!(p.white)) {
                                isPossible = false;
                            } else if (p.white && d != dx) {
                                isPossible = false;
                            }
                        }
                    }
                    return isPossible;
                }
                else
                {
                    boolean isPossible = true;
                    for(int d = -1; d >= dx; d--)
                    {
                        Piece p = b.getPiece(x + d, newY);
                        if (p == null) {
                            continue;
                        }

                        if (white) {
                            if (p.white) {
                                isPossible = false;
                            } else if (!(p.white) && d != dx) {
                                isPossible = false;
                            }
                        } else {
                            if (!(p.white)) {
                                isPossible = false;
                            } else if (p.white && d != dx) {
                                isPossible = false;
                            }
                        }
                    }
                    return isPossible;
                }
            }
        }
        return false;
    }
}
//    public boolean isPossible(int x, int y) {
//        boolean possible = false;
//        if (sameSpot(x,y) || sameColorMove(x,y) || moveOutsideBoard(x,y)){
//            return false;
//        }
//        else if(currBoard.getPiece(x,y) == null || currBoard.getPiece(x,y).white == this.white){
//            return true;
//        }
//       return possible;
//    }
//}


/**
 * This returns a list of all possible moves for this specific piece
 * @return ArrayList<String> is the arraylist of possible moves
 * */
//    public ArrayList<String> possibleMoves(){
//        ArrayList<String> moves = new ArrayList<>();
//        for (int i = 0; i < 4; i++) { //loop to control how many in directions to search
//            for (int j = 0; j < 8; j++) { //Loop to control how many spaces forward to check
//                if(i == 0){
//                    if (isPossible(x+j, y)){
//                        moves.add(translateNum(new ArrayList<>(List.of(x+j,y))));
//                    }
//                    else{
//                        break;
//                    }
//                }
//                else if (i == 1){
//                    if (isPossible(x-j, y)){
//                        moves.add(translateNum(new ArrayList<>(List.of(x-j,y))));
//                    }
//                    else{
//                        break;
//                    }
//                }
//                else if (i == 2){
//                    if (isPossible(x, y+j)){
//                        moves.add(translateNum(new ArrayList<>(List.of(x,y+j))));
//                    }
//                    else{
//                        break;
//                    }
//                }
//                else if (i == 3){
//                    if (isPossible(x, y-j)){
//                        moves.add(translateNum(new ArrayList<>(List.of(x,y-j))));
//                    }
//                    else{
//                        break;
//                    }
//                }
//            }
//        }
//        return moves;
//    }