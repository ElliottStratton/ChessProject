import java.util.Scanner;

public class ConsoleAI {

    private static AI artIntel;
    private static String name;
    private static Scanner scnr = new Scanner(System.in);;
    private static Game g;


    public ConsoleAI(Game g){
        this.g = g;
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

    public void run(){
        boolean hasWon = false;

        System.out.println("Enter name:");
        name = scnr.nextLine();
        System.out.println("Choose level of opponent:");
        System.out.println("Enter 1 for Easy, 2 for medium, 3 for Hard");
        int choice = scnr.nextInt();

        if (choice == 1){
            artIntel = new AI_Random();
        }
        else if (choice == 2){
            artIntel = new AI_Capture();
        }
        else if (choice == 3){
            artIntel = new AI_AlphaBeta(g.getBoard());
//            alphaBetaAI.minimaxAB(g.getBoard(), 4, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }


        while(!hasWon){ //for testing
            System.out.println(g.getBoard().toString());
            if (!g.isCurrentPlayer()){
                name = "AI";
            }

            //Player move
            System.out.println("Enter initial piece's coordinates: ");
            String currLoc = scnr.next();
            int x = convertX(currLoc.charAt(0));
            int y = convertY(currLoc.charAt(1));

            System.out.println("Enter coordinates you want to move: ");
            String newLoc = scnr.next();
            System.out.println("1");
            int newX = convertX(newLoc.charAt(0));
            int newY = convertY(newLoc.charAt(1));
            //Player move
            System.out.println("2");
            g.nextMove(x,y,newX,newY);
            System.out.println("3");

            System.out.println(g.getBoard().toString());
            g.changePlayer();

            //AI move
            Move aiMove = artIntel.executeMove(g.isCurrentPlayer(), g.getBoard());
            Piece moveP = aiMove.getCurrPiece();
            int aiX = moveP.translateLetNum(aiMove.getCurrLocation()).get(0);
            int aiY = moveP.translateLetNum(aiMove.getCurrLocation()).get(0);
            int aiNewX = moveP.translateLetNum(aiMove.getNewLocation()).get(0);
            int aiNewY = moveP.translateLetNum(aiMove.getNewLocation()).get(0);
            g.nextMove(aiX,aiY,aiNewX,aiNewY);

            if (g.checkmate()){
                hasWon = true;
            }
        }
        System.out.println(name + " has won the game.");
    }

    public static void main(String[] args) {
        boolean hasWon = false;
        g = new Game();

        System.out.println("Enter name:");
        name = scnr.nextLine();
        System.out.println("Choose level of opponent:");
        System.out.println("Enter 1 for Easy, 2 for medium, 3 for Hard");
        while(true){
            int choice = scnr.nextInt();
            if (choice == 1){
                artIntel = new AI_Random();
                break;
            }
            else if (choice == 2){
                artIntel = new AI_Capture();
                break;
            }
            else if (choice == 3){
                artIntel = new AI_AlphaBeta(g.getBoard());
//              alphaBetaAI.minimaxAB(g.getBoard(), 4, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
                break;
            }
            else{
                System.out.println("Incorrect entry. Please retry.");
            }
        }



        while(!hasWon){ //for testing
            System.out.println(g.getBoard().toString());
            if (!g.isCurrentPlayer()){
                name = "AI";
            }

            //Player move
            System.out.println("Enter initial piece's coordinates: ");
            String currLoc = scnr.next();
            int x = convertX(currLoc.charAt(0));
            int y = convertY(currLoc.charAt(1));

            System.out.println("Enter coordinates you want to move: ");
            String newLoc = scnr.next();
            int newX = convertX(newLoc.charAt(0));
            int newY = convertY(newLoc.charAt(1));
            //Player move
            g.nextMove(x,y,newX,newY);

            System.out.println(g.getBoard().toString());
            g.changePlayer();

            //AI move
            Move aiMove = artIntel.executeMove(g.isCurrentPlayer(), g.getBoard());
            Piece moveP = aiMove.getCurrPiece();
            int aiX = moveP.translateLetNum(aiMove.getCurrLocation()).get(0);
            System.out.println("x: " + aiX);
            int aiY = moveP.translateLetNum(aiMove.getCurrLocation()).get(1);
            System.out.println("y: " + aiY);
            System.out.println(g.getBoard().getPiece(aiX,aiY));//for testing
            int aiNewX = moveP.translateLetNum(aiMove.getNewLocation()).get(0);
            System.out.println("newx: " + aiNewX);
            int aiNewY = moveP.translateLetNum(aiMove.getNewLocation()).get(1);
            System.out.println("newy: " + aiNewY);
            g.nextMove(aiX,aiY,aiNewX,aiNewY);

            if (g.checkmate()){
                hasWon = true;
            }
            g.changePlayer();
        }
        System.out.println(name + " has won the game.");
    }
}
