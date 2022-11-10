public class Game {
    private Board board;
    private boolean currentPlayer;

    public Game() {
        board = new Board();
        currentPlayer = false;
    }

    public boolean checkmate()
    {
        return false;
    }

    public boolean check()
    {
        return false;
    }

    public boolean hasWon()
    {
        return false;
    }

    public void nextMove(int x1, int y1, int x2, int y2)
    {

    }

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
