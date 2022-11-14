public class Knight extends Piece{
    /**
     * default constructor
     * @param color player
     * @param x initial x position
     * @param y initial y position
     * @param b board that the pawn will be on
     */
    public Knight(boolean color, int x, int y, Board b) {
        super(color, x, y, b);
        b.occupy(this,x,y);
    }

    @Override
    public void move(int x,int y) {
        super.move(x,y);
    }


    @Override
    public boolean isPossible(int x, int y) throws IllegalArgumentException{
        return false;
    }
}
