
public class King extends Piece{

    public King(boolean color, int x, int y, int value, Board b) {
        super(color, x, y, value, b);
        b.occupy(this,x,y);
    }

    public King(){

    }

    /**
     * Copy Constructor
     * */
    public King(Piece p) {
        super(p);
    }


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

