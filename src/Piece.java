/**
 * Piece class controls most of the functions of each piece
 * The child classes control the possible moves
 */
public abstract class Piece {

    boolean white; //white is true
    int x;
    int y;
    Board currBoard;

    /**
     * default constructor
     */
    public Piece() {
    }

    /**
     *
     * @param color If the color is white color == true
     * @param x x coordinate of the piece
     * @param y y          "
     * @param b Which Board is the piece on
     */
    public Piece(boolean color, int x, int y, Board b) {
        this.white = color;
        this.x = x;
        this.y = y;
        currBoard = b;
    }

    /**
     * Sets position to the parameters x and y
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return the X coordinate of the current piece
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return the Y coordinate of the current piece
     */
    public int getY() {
        return y;
    }

    /**
     * Compares the move a player wants to the current piece
     * @param x coordinate of the new move
     * @param y             "
     * @return true if the move is possible
     * @throws IllegalArgumentException If the move is impossible it throws an exception
     */
    public abstract boolean isPossible(int x, int y) throws IllegalArgumentException;

    /**
     * Moves a piece
     * @param x the new coordinate of the piece
     * @param y                 "
     */
    public void move(int x, int y) throws IllegalArgumentException{
        if(sameSpot(x,y))
        {
            throw new IllegalArgumentException("Coordinates to move are in the same spot.");
        }

        else if(sameColorMove(x,y))
        {
            throw new IllegalArgumentException("Cannot move onto another one of your pieces.");
        }
        else if(moveOutsideBoard(x,y))
        {
            throw new IllegalArgumentException("Desired location is outside the board. Please enter a valid location");
        }
        else if(isPossible(x, y)) {
            currBoard.unOccupy(getX(), getY());
            setPosition(x, y);
            currBoard.occupy(this, x, y);
        }
    }

    /**
     * Ensures that a piece does not try to move to the spot it currently occupies
     * @param x new coordinates of the piece
     * @param y
     * @return true if the new move is the same as the spot it currently occupies
     */
    public boolean sameSpot(int x,int y) {
        if (x == this.x && y == this.y) {
            return true;
        } else { return false; }
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean sameColorMove(int x, int y) {
        if (currBoard.getPiece(x,y) != null) {
            if (white) {
                if (currBoard.getPiece(x, y).white) {
                    return true;
                }
            } else {
                if (!currBoard.getPiece(x, y).white) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Ensures that the location you input is not outside the board
     * @param x x coordinate of the new location
     * @param y y coordinate of the new location
     * @return true or false whether you are trying to move to a location not on the board
     */
    public boolean moveOutsideBoard(int x, int y) {
        if (x < 0 || y < 0 || x > 7 || y > 7)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
