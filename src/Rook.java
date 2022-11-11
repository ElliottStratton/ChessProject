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
