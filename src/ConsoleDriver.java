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
            sPlayer = "white";
        }
        else{
            sPlayer = "black";
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
        return numX;
    }

    /**
     * This takes in an integer of one character and makes it the correct integer
     * @param y is the integer coordinate that the player inputted
     * @return An integer that is the correct coordinate to navigate the 2d array
     * */
    public static int convertY(int y)
    {
        return 8-y;
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Game game = new Game();
        boolean hasWon = false;

        System.out.println(game.getBoard().toString()); //For testing purposes

        while(!hasWon){
            String player = getPlayer(game.isCurrentPlayer());

            boolean valid = false;
            while(!valid){
                System.out.println("Enter initial piece's coordinates: ");
                scnr.useDelimiter("");
                int x = convertX(scnr.next().charAt(0));
                System.out.println("x " + x);
                int y = convertY(scnr.nextInt());
                System.out.println("y " + y);

                System.out.println("Enter coordinates you want to move: ");
                int newX = convertX(scnr.next().charAt(0));
                newX = convertX(scnr.next().charAt(0));
                System.out.println("newX " + newX);
                int newY = convertY(scnr.nextInt());
                System.out.println("newY " + newY);

                try{
                    game.nextMove(x,y,newX,newY);
                    valid = true;
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    valid = false;
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
    }//End main
}
