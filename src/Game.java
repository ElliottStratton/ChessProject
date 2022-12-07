import java.util.ArrayList;
import java.util.Iterator;

public class Game implements Convert{
    private Board board;
    private boolean currentPlayer;

    /**
     * Default constructor
     * intitializes board and sets current player to false (team white)
     */
    public Game() {
        board = new Board();
        currentPlayer = true; // True is player white
    }

    /**
     *
     * @return true if king is in checkmate, false otherwise
     */
    public boolean checkmate()
    {
        return false;
    }

    public ArrayList<String> allWMoves() {
        ArrayList<String> moves = new ArrayList<>();
        for(Piece i : board.arrPieces) {
            if(i.white) {
                moves.addAll(i.possibleMoves());
            }
        }
        return moves;
    }

    public ArrayList<String> allBMoves() {
        ArrayList<String> moves = new ArrayList<>();
        for(Piece i : board.arrPieces) {
            if(!i.white) {
                moves.addAll(i.possibleMoves());
            }
        }
        return moves;
    }

    /**
     *
     * @return true if king is in check, false otherwise
     */
    public boolean check() {
        if(currentPlayer) {
            int px = 0;
            int py = 0;
            for (String i : allBMoves()) {
                px = Convert.convertX(i.charAt(0));
                py = Convert.convertY(i.charAt(1));
                if(board.getWKing().getX() == px && board.getWKing().getY() == py) {
                    return true;
                }
            }
        } else {
            int px = 0;
            int py = 0;
            for (String i : allWMoves()) {
                px = Convert.convertX(i.charAt(0));
                py = Convert.convertY(i.charAt(1));
                if(board.getBKing().getX() == px && board.getBKing().getY() == py) {
                    return true;
                }
            }
        }

        return false;
    }


    /**
     *
     * @param x1 initial piece x coordinate
     * @param y1 initial piece y coordinate
     * @param x2 where you want to move in the x
     * @param y2 where you want to move in the y
     */
    public void nextMove(int x1, int y1, int x2, int y2) throws IllegalArgumentException {
        if(x1 < 0 || y1 < 0 || x1 > 7 || y1 > 7)
        {
            throw new IllegalArgumentException("Initial piece coordinates are out of bounds.");
        }

        Piece currPiece = board.getPiece(x1,y1);
        if (currPiece.white != currentPlayer){
            throw new IllegalArgumentException("This is not your piece to move.");
        }

        else{
            try{
                currPiece.move(x2,y2); //move() throws an IllegalArgumentException with a message for each possible incorrect solution
            }
            catch(IllegalArgumentException e){
                throw e;
            }
        }
        /*
        * Errors: if you try to move a piece that's not yours
        * if you try to move into the same spot
        * if you try to move from an empty space
        * if you try to move to a place occupied by your piece or another piece is in the way
        *
        * */
    }

    /**
     *  changes player from one player to another
     */
    public void changePlayer()
    {
        if(currentPlayer == true)
        {
            currentPlayer = false;
        }
        else
        {
            currentPlayer = true;
        }
    }

    /**
     * This method returns the current player.
     * @return True is player white, false is player black.
     */
    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    /**
     *
     * @param white determines which team you want to see the moves of
     * @return an arraylist of every single possible move of a color
     */
    public ArrayList<String> allPossibleMoves(boolean white){
        return null;
    }

    /**
     * castling method will allow for the castle move if the requirements for the rook and king positions is met
     */
    public void castling() {
    }

    /**
     * pawnPromotion method will prompt the player to choose which piece to promote a pawn to if it reaches the opposite
     * side of the board.
     */
    public void pawnPromotion(){
    }

    /**
     * This method is a simple getter for Board
     * @return Board is the current board
     * */
    public Board getBoard() {
        return board;
    }

    // NOTE: Right now we are doing game loop in the console class.
    //
    //    public void gameLoop()
//    {
//        while (hasWon() == false)
//        {
//            if(currentPlayer == true)
//            {
//                System.out.println("It is White's turn.");
//            }
//            else
//            {
//                System.out.println("It is Red's turn.");
//            }
//
//            System.out.println("Select piece to move: ");
//
//
//
//
//
//
//            // Change players at the end of your turn
//
//            if(currentPlayer == true)
//            {
//                currentPlayer = false;
//            }
//            else
//            {
//                currentPlayer = true;
//            }
//        }
//    }
}
