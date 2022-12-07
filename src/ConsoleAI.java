import java.util.Scanner;

public class ConsoleAI {

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

    /**
     * This takes in an integer of one character and makes it the correct integer
     * @param y is the integer coordinate that the player inputted
     * @return An integer that is the correct coordinate to navigate the 2d array
     * */
    public static int convertY(int y)
    {
        return 56-y;
    }

    public static void main(String[] args) {
        AI artIntel;
        Game g = new Game();
        Scanner scnr = new Scanner(System.in);
        System.out.println("Choose level of opponent:");
        System.out.println("Enter 1 for Easy, 2 for medium, 3 for Hard");
        int choice = scnr.nextInt();

        while(!g.checkmate(true, g.getBoard())){ //for testing
            System.out.println(g.getBoard().toString());


            //Player move
            System.out.println("Enter initial piece's coordinates: ");
            String currLoc = scnr.next();
            int x = convertX(currLoc.charAt(0));//For testing purposes
            System.out.println("x " + x);
            int y = convertY(currLoc.charAt(1));//For testing purposes
            System.out.println("y " + y);

            System.out.println("Enter coordinates you want to move: ");
            String newLoc = scnr.next();
            int newX = convertX(newLoc.charAt(0));//For testing purposes
            System.out.println("newX " + newX);
            int newY = convertY(newLoc.charAt(1));//For testing purposes
            System.out.println("newY " + newY);


            //AI Move


        }












        //TODO DEFINITELY NOT THE WAY TO DO THIS
        //You have to call each of the methods below per turn not just once
        if (choice == 1){
            artIntel = new AI_Random();
            artIntel.executeMove(g.isCurrentPlayer(), g.getBoard(), 0);
        }
        if (choice == 2){
            artIntel = new AI_Capture();
            artIntel.executeMove(g.isCurrentPlayer(), g.getBoard(), 0);
        }
        if (choice == 3){
            artIntel = new AI_AlphaBeta(g.getBoard());
            artIntel.executeMove(g.isCurrentPlayer(), g.getBoard(), 0);

//            alphaBetaAI.minimaxAB(g.getBoard(), 4, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }
    }
}
