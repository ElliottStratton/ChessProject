
public class King extends Piece{

    public King(boolean color, int x, int y, int value, Board b) {
        super(color, x, y, value, b);
        b.occupy(this,x,y);
    }

//    public King(){
//
//    }

    /**
     * This returns a list of all possible moves for this specific piece
     * @return ArrayList<String> is the arraylist of possible moves
     * */
//    public ArrayList<String> possibleMoves() {
//        ArrayList<String> moves = new ArrayList<>();
//
//        for (int i = -1; i < 2; i++) {
//            for (int j = -1; j < 2; j++) {
//                if (isPossible(x+i, y+j)){
//                    moves.add(translateNum(new ArrayList<>(List.of(x+i,y+j))));
//                }
//            }
//        }

    //If the above for loops don't work, the lines below definitely will.
//
//        if (this.isPossible(x-1, y)){
//            moves.add(translateNum(new ArrayList<>(List.of(x-1,y))));
//        }
//        if (this.isPossible(x-1, y+1)){
//            moves.add(translateNum(new ArrayList<>(List.of(x-1,y+1))));
//        }
//        if (this.isPossible(x, y+1)){
//            moves.add(translateNum(new ArrayList<>(List.of(x,y+1))));
//        }
//        if (this.isPossible(x+1, y+1)){
//            moves.add(translateNum(new ArrayList<>(List.of(x+1,y+1))));
//        }
//        if (this.isPossible(x+1, y)){
//            moves.add(translateNum(new ArrayList<>(List.of(x+1,y))));
//        }
//        if (this.isPossible(x+1, y-1)){
//            moves.add(translateNum(new ArrayList<>(List.of(x+1,y-1))));
//        }
//        if (this.isPossible(x, y-1)){
//            moves.add(translateNum(new ArrayList<>(List.of(x,y-1))));
//        }
//        if (this.isPossible(x-1, y-1)){
//            moves.add(translateNum(new ArrayList<>(List.of(x-1,y-1))));
//        }
//        return moves;
//    }

    @Override
    public boolean isPossible(int newX, int newY) throws IllegalArgumentException{
        int dx = newX - x;
        int dy = newY - y;
        Board b = currBoard;
        if(dy > 1 || dy < -1 || dx > 1 || dx < -1 || (dx == 0 && dy == 0))
        {
            return false;
        }
        Piece p = b.getPiece(newX,newY);
        if(white) // white is at the bottom
        {
            if(p == null || !p.white)
            {
                return true;
            }
            return false;
        }

        else // player black
        {
            if(p == null || p.white)
            {
                return true;
            }
            return false;

        }
    }

}

