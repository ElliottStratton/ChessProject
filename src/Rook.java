import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(boolean color, int x, int y, Board b) {
        super(color, x, y, b);
        b.occupy(this,x,y);
    }

    @Override
    public void move(int x,int y) {
        if(!check(x,y)) {
            super.move(x,y);
        }
    }



    //TODO change everything below this

    public ArrayList<String> possibleMoves(){
        ArrayList<String> moves = new ArrayList<>();
        for (int i = 0; i < 4; i++) { //loop to control how many in directions to search
            inner: for (int j = 0; j < 8; j++) { //Loop to control how many spaces forward to check
                if(i == 0){
                    if (this.isPossible(x+i, y)){
                        moves.add(); //Add this current location in Chess notion
                    }
                    else{
                        break inner;
                    }
                }
                else if (i == 1){
                    if (this.isPossible(x-i, y)){
                        moves.add(); //Add this current location in Chess notion
                    }
                    else{
                        break inner;
                    }
                }
                else if (i == 2){
                    if (this.isPossible(x, y+i)){
                        moves.add(); //Add this current location in Chess notion
                    }
                    else{
                        break inner;
                    }
                }
                else if (i == 3){
                    if (this.isPossible(x, y-i)){
                        moves.add(); //Add this current location in Chess notion
                    }
                    else{
                        break inner;
                    }
                }
            }
        }


        return moves;
    }

    @Override
    public boolean isPossible(int x, int y) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if(getX() + i == x && getY() == y) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(int x, int y) {
        return false;
    }
}
