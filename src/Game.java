import java.util.ArrayList;

public class Game {
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

    /**
     *
     * @return true if king is in check, false otherwise
     */
    public boolean check()
    {
        return false;
    }


    /**
     *
     * @param x1 initial piece x coordinate
     * @param y1 initial piece y coordinate
     * @param x2 where you want to move in the x
     * @param y2 where you want to move in the y
     */
    public void nextMove(int x1, int y1, int x2, int y2)
    {
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
