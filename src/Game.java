public class Game {
    private Board board;
    private boolean currentPlayer;

    /**
     * Default constructor
     * intitializes board and sets current player to false (team white)
     */
    public Game() {
        board = new Board();
        currentPlayer = false;
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
     * @return true if
     */
    public boolean hasWon()
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

    public boolean isCurrentPlayer() {
        return currentPlayer;
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
