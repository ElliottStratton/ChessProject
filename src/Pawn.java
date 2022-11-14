import javax.swing.*;

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
    public Pawn(boolean color, int x, int y, Board b) {
        super(color, x, y, b);
        b.occupy(this, x, y);
        firstMove = true;
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        firstMove = false;
    }


    @Override
    public boolean isPossible(int newX, int newY) throws IllegalArgumentException {
        if(white) // white is at the bottom
        {
            if(firstMove)
            {

            }
            else
            {

            }
        }
        else
        {
            if(firstMove)
            {

            }
            else
            {

            }
        }

    }
}