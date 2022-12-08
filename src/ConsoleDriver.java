import java.util.Scanner;

public class ConsoleDriver {

    /**
     * This returns the color of the player from the boolean
     * @param player is a boolean that determines true for white or false for black
     * @return A string with the color of the player
     * */
    public static String getPlayer(boolean player){
        String sPlayer = "";
        if(player){
            sPlayer = "White";
        }
        else{
            sPlayer = "Black";
        }
        return sPlayer;
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
        else
        {
            numX = 8;
        }
//        return x-65;
        return numX;
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

    public static void run(Game game, Scanner scnr){
        boolean hasWon = false;
        while(!hasWon){
            String player = getPlayer(game.isCurrentPlayer());

            boolean valid = false;
            while(!valid){
                System.out.println(game.getBoard().toString());//For testing purposes

                int x = 0;
                int y = 0;
                boolean hasPiece = false;
                System.out.println(player + "'s turn to move.");
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
            System.out.println(game.getBoard().toString());
            if (game.checkmate()){
                hasWon = true;
                System.out.println(player + " has won with a checkmate");
            }
            else{
                game.changePlayer();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Game game = new Game();
        boolean hasWon = false;
        int choice = 0;
        boolean end = false;

//        System.out.println("Initial Board");
//        System.out.println(game.getBoard().toString()); //For testing purposes
//        System.out.println(Arrays.deepToString(game.getBoard().getBoard())); //For testing purposes

        while(!end) {
            System.out.println("Enter 1 for single player and 2 for two player");
            while (true) {
                int input = scnr.nextInt();
                if (input == 1) {
                    choice = 1;
                    break;
                } else if (input == 2) {
                    choice = 2;
                    break;
                } else {
                    System.out.println("Invalid input. Please reenter: ");
                }
            }
            if (choice == 1) {
                ConsoleAI aiConsole = new ConsoleAI(game);
                aiConsole.run();
            } else {
                run(game, scnr);
            }

            System.out.println("Enter Go to play again or exit to leave the program:");
            String exit = scnr.next();
            if (!exit.equalsIgnoreCase("go")) {
                end = true;
            }
        }
    }//End main

}
