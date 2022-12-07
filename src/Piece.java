import java.util.ArrayList;

/**
 * Piece class controls most of the functions of each piece
 * The child classes control the possible moves
 */
public abstract class Piece {

    boolean white; //white is true and white is at the bottom moving upward
    int x;
    int y;
    Board currBoard;
    int value;
    boolean firstMove = true;

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
    public Piece(boolean color, int x, int y, int value, Board b) {
        this.white = color;
        this.x = x;
        this.y = y;
        currBoard = b;
        this.value = value;
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
     * Compares the move a player wants to the current piece location
     * Each piece has different moves so each piece overrides this
     * @param x coordinate of the new move
     * @param y             "
     * @return true if the move is possible
     * @throws IllegalArgumentException If the move is impossible it throws an exception
     */
    public abstract boolean isPossible(int x, int y) throws IllegalArgumentException;

    /**
     *
     * @return an arraylist of strings of the possible moves for any piece
     */
    public ArrayList<String> possibleMoves() {
        ArrayList<String> moves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 7; j >= 0; j--) {
                if(isPossible(i,j)) {
                    ArrayList<Integer> nums = new ArrayList<>();
                    nums.add(i);
                    nums.add(j);
                    moves.add(translateNum(nums));
                }
            }
        }
        return moves;
    }

    /**
     * Moves a piece
     * @param x the new coordinate of the piece
     * @param y                 "
     */
    public void move(int x, int y) throws IllegalArgumentException{
//        System.out.println("Is Possible: " + isPossible(x,y));
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
        else if(capture(x,y) != null && isPossible(x, y)){
            Piece p = capture(x,y);
            currBoard.unOccupy(x, y);
            currBoard.unOccupy(this.x,this.y);
            currBoard.occupy(this, x,y);
            setPosition(x,y);
            currBoard.arrPieces.remove(p);
            firstMove = false;
        }
        else if(isPossible(x, y)) {
            currBoard.unOccupy(x, y);
            currBoard.unOccupy(this.x,this.y);
            setPosition(x, y);
            currBoard.occupy(this, x, y);
            firstMove = false;
        }
        else
        {
            throw new IllegalArgumentException("The move is not possible.");
        }
    }


    /**
     * This returns a specific piece of the opponent if it is able to be captured by this specific piece
     * @return a Piece that is null if there is no piece that is able to be captured
     * */
    public Piece capture(int x, int y){
        Piece capturePiece = null;
        ArrayList<Piece> pieces = new ArrayList<>();
        for (Piece p : currBoard.arrPieces) {//Gets all enemy pieces
            if (p.white != this.white){
                pieces.add(p);
            }
        }

        for (Piece cP: pieces){
            if (x == cP.x && y == cP.y){
                capturePiece = cP;
                break;
            }
        }
        return capturePiece;
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
        if (x < 0 || y < 0 || x > 7 || y > 7) //If it is outside the board
        {
            return true;
        }
        else //If it is inside the board
        {
            return false;
        }
    }

    /**
     * A getter for the variable value
     * @return the variable value
     * */
    public int getValue() {
        return value;
    }


    /**
     * Translates a current x,y location to the Chess annotation of LetterNumber
     * @param loc is an arraylist of an x and a y coordinate
     * */
    public String translateNum(ArrayList<Integer> loc){
        int newX = loc.get(0);
        int newY = 8 - loc.get(1);
        StringBuilder sb = new StringBuilder();
        if (newX == 0){
            sb.append("a");
        }
        else if (newX == 1){
            sb.append("b");
        }
        else if (newX == 2){
            sb.append("c");
        }
        else if (newX == 3){
            sb.append("d");
        }
        else if (newX == 4){
            sb.append("e");
        }
        else if (newX == 5){
            sb.append("f");
        }
        else if (newX == 6){
            sb.append("g");
        }
        else if (newX == 7){
            sb.append("h");
        }
        sb.append(newY);
        return sb.toString();
    }

    public ArrayList<Integer> translateLetNum(String loc){
        ArrayList<Integer> location = new ArrayList<>();
        char x = loc.charAt(0);
        int numX = 0;
        int y = 56-loc.charAt(1) ;

        if (x == 'a'){
            numX = 0;
        }
        else if (x == 'b'){
            numX = 1;
        }
        else if (x == 'c'){
            numX = 2;
        }
        else if (x == 'd'){
            numX = 3;
        }
        else if (x == 'e'){
            numX = 4;
        }
        else if (x == 'f'){
            numX = 5;
        }
        else if (x == 'g'){
            numX = 6;
        }
        else if (x == 'h'){
            numX = 7;
        }
        location.add(numX);
        location.add(y);
        return location;
    }
}
