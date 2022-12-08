import java.util.Scanner;

public class ConsoleAI {

    private static AI artIntel;
    private static String name;
    private static Scanner scnr = new Scanner(System.in);
    private static Game g;


    public ConsoleAI(Game g){
        this.g = g;
        String name = "";
    }

    public static void makeMove(Move move, Board board){
        Piece currPiece = move.getCurrPiece();
        String currLoc = move.getCurrLocation();
        int x = move.getCurrPiece().translateLetNum(move.getCurrLocation()).get(0);
        int y = move.getCurrPiece().translateLetNum(move.getCurrLocation()).get(1);
        String newLoc = move.getNewLocation();
        int newX = move.getCurrPiece().translateLetNum(move.getNewLocation()).get(0);
        int newY = move.getCurrPiece().translateLetNum(move.getNewLocation()).get(1);

        if (board.getPiece(newX,newY) != null){ //If there is a capture
            board.board[newY][newX] = null;
        }
        g.nextMove(x,y,newX,newY);
    }

    /**
     * This takes in a String of one character and makes it an integer
     * @param x is the character coordinate that the player inputted
     * @return An integer that is the correct coordinate to navigate the 2d array
     * */
    public static int convertX(char x)
    {
        x = Character.toLowerCase(x);
        int numX = 0;
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
//        return x-65;
        return numX;
    }

    public static Move getPlayerMove(boolean player, Board board){
        System.out.println("Enter initial piece's coordinates: ");
        String currLoc = scnr.next();
        int x = convertX(currLoc.charAt(0));
        int y = convertY(currLoc.charAt(1));

        System.out.println("Enter coordinates you want to move: ");
        String newLoc = scnr.next();
        //Player move
        return new Move(newLoc, board.getPiece(x,y));
    }

    /**
     * This takes in an integer of one character and makes it the correct integer
     * @param y is the integer coordinate that the player inputted
     * @return An integer that is the correct coordinate to navigate the 2d array
     * */
    public static int convertY(int y)
    {
        return 56-y;
    }

    public void run(Game game){
        boolean hasEnded = false;
        while(!hasEnded) { //for testing
            g = new Game();

            System.out.println("Enter name:");
            name = scnr.nextLine();
            System.out.println("Choose level of opponent:");
            System.out.println("Enter 1 for Easy, 2 for medium, 3 for Hard");
            while (true) {
                int input = scnr.nextInt();
                if (input == 1) {
                    artIntel = new AI_Random();
                    break;
                } else if (input == 2) {
                    artIntel = new AI_Capture();
                    break;
                } else if (input == 3) {
                    artIntel = new AI_AlphaBeta(g.getBoard());
//              alphaBetaAI.minimaxAB(g.getBoard(), 4, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
                    break;
                } else {
                    System.out.println("Incorrect entry. Please retry.");
                }
            }
            if (!g.isCurrentPlayer()) {
                name = "AI";
            }

            boolean hasWon = false;
            while (!hasWon) { //for testing
                boolean valid = false;
                while(!valid){
                    System.out.println(game.getBoard().toString());//For testing purposes

                    System.out.println(name + " is the white player.");
                    int x = 0;
                    int y = 0;
                    boolean hasPiece = false;
                    System.out.println(name + "'s turn to move.");
                    System.out.println("Enter initial piece's coordinates: ");
                    while(!hasPiece) {
                        String currLoc = scnr.next();
                        x = convertX(currLoc.charAt(0));//For testing purposes
                        //System.out.println("x " + x);
                        y = convertY(currLoc.charAt(1));//For testing purposes
                        //System.out.println("y " + y);

                        //System.out.println(game.getBoard().getPiece(x, y));//For testing purposes
                        if(x >= 0 && x <= 7 & y >= 0 && y <= 7 && game.getBoard().getPiece(x,y) != null){
                            hasPiece = true;
                        }
                        else{
                            System.out.println("There is not a piece at this location.");
                            System.out.println("Reenter initial piece's coordinates: ");
                        }
                    }
                    //System.out.println(game.getBoard().getPiece(x, y).possibleMoves());
                    System.out.println("Enter coordinates you want to move to: ");
                    String newLoc = scnr.next();
                    int newX = convertX(newLoc.charAt(0));//For testing purposes
                    // System.out.println("newX " + newX);
                    int newY = convertY(newLoc.charAt(1));//For testing purposes
                    // System.out.println("newY " + newY);

                    // System.out.println(game.getBoard().getPiece(newX, newY));//For testing purposes

                    try{
                        game.nextMove(x,y,newX,newY);
                        valid = true;
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("Try reentering the coordinates.");
                    }
                }

                System.out.println(g.getBoard().toString());
                g.changePlayer();

                //AI move
                Move aiMove = artIntel.executeMove(g.isCurrentPlayer(), g.getBoard());
                Piece moveP = aiMove.getCurrPiece();
                int aiX = moveP.translateLetNum(aiMove.getCurrLocation()).get(0);
//                System.out.println("x: " + aiX);
                int aiY = moveP.translateLetNum(aiMove.getCurrLocation()).get(1);
//                System.out.println("y: " + aiY);
//                System.out.println(g.getBoard().getPiece(aiX, aiY));//for testing
                int aiNewX = moveP.translateLetNum(aiMove.getNewLocation()).get(0);
//                System.out.println("newx: " + aiNewX);
                int aiNewY = moveP.translateLetNum(aiMove.getNewLocation()).get(1);
//                System.out.println("newy: " + aiNewY);
                g.nextMove(aiX, aiY, aiNewX, aiNewY);

                if (g.checkmate(g.isCurrentPlayer(), g.getBoard())) {
                    hasWon = true;
                }
                g.changePlayer();
            }
            System.out.println(name + " has won the game.");
        }
    }

//    public static void main(String[] args) {
//        boolean hasWon = false;
//        g = new Game();
//
//        System.out.println("Enter name:");
//        name = scnr.nextLine();
//        System.out.println("Choose level of opponent:");
//        System.out.println("Enter 1 for Easy, 2 for medium, 3 for Hard");
//        while(true){
//            int choice = scnr.nextInt();
//            if (choice == 1){
//                artIntel = new AI_Random();
//                break;
//            }
//            else if (choice == 2){
//                artIntel = new AI_Capture();
//                break;
//            }
//            else if (choice == 3){
//                artIntel = new AI_AlphaBeta(g.getBoard());
////              alphaBetaAI.minimaxAB(g.getBoard(), 4, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
//                break;
//            }
//            else{
//                System.out.println("Incorrect entry. Please retry.");
//            }
//        }
//
//
//
//        while(!hasWon){ //for testing
//            System.out.println(g.getBoard().toString());
//            if (!g.isCurrentPlayer()){
//                name = "AI";
//            }
//
//            //Player move
//            System.out.println("Enter initial piece's coordinates: ");
//            String currLoc = scnr.next();
//            int x = convertX(currLoc.charAt(0));
//            int y = convertY(currLoc.charAt(1));
//
//            System.out.println("Enter coordinates you want to move: ");
//            String newLoc = scnr.next();
//            int newX = convertX(newLoc.charAt(0));
//            int newY = convertY(newLoc.charAt(1));
//            //Player move
//            g.nextMove(x,y,newX,newY);
//
//            System.out.println(g.getBoard().toString());
//            g.changePlayer();
//
//            //AI move
//            Move aiMove = artIntel.executeMove(g.isCurrentPlayer(), g.getBoard());
//            Piece moveP = aiMove.getCurrPiece();
//            int aiX = moveP.translateLetNum(aiMove.getCurrLocation()).get(0);
//            System.out.println("x: " + aiX);
//            int aiY = moveP.translateLetNum(aiMove.getCurrLocation()).get(1);
//            System.out.println("y: " + aiY);
//            System.out.println(g.getBoard().getPiece(aiX,aiY));//for testing
//            int aiNewX = moveP.translateLetNum(aiMove.getNewLocation()).get(0);
//            System.out.println("newx: " + aiNewX);
//            int aiNewY = moveP.translateLetNum(aiMove.getNewLocation()).get(1);
//            System.out.println("newy: " + aiNewY);
//            g.nextMove(aiX,aiY,aiNewX,aiNewY);
//
//            if (g.checkmate(g.isCurrentPlayer(), g.getBoard())){
//                hasWon = true;
//            }
//            g.changePlayer();
//        }
//        System.out.println(name + " has won the game.");
//    }
}
