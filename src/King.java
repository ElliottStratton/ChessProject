public class King extends Piece{
    public King(boolean color, int x, int y, Board b) {
        super(color, x, y, b);
        b.occupy(this,x,y);
    }

    @Override
    public void move(int x,int y) {
        if(!check(x,y)) {
            super.move(x,y);
        }
    }

    @Override
    public boolean isPossible(int x, int y) throws IllegalArgumentException{
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if(getX() + i == x && getY() == y) {
                    return true;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean check(int x, int y) {
        return false;
    }

}
