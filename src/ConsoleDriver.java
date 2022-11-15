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
     * @param str is the character coordinate that the player inputted
     * @return An integer that is the correct coordinate to navigate the 2d array
     * */
    public static int convertX(String str)
    {
        return (int)str.charAt(0) - 65;
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
        System.out.println("Enter Y to Start game or N to close");
        boolean start = false;
        if (scnr.next().charAt(0) == 'Y'){
            start = true;
        }
        Game game = new Game();

        boolean hasWon = false;
        while(!hasWon){


            String player = getPlayer(game.isCurrentPlayer());

            boolean valid = false;
            while(!valid){
                try{
                    System.out.println("Enter initial piece's coordinates: ");
                    scnr.useDelimiter("");
                    int x = convertX(scnr.next());
                    int y = convertY(scnr.nextInt());


                    System.out.println("Enter coordinates you want to move: ");
                    int newX = convertX(scnr.next());
                    int newY = convertY(scnr.nextInt());

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
    }

}
