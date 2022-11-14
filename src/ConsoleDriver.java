import java.util.Scanner;

public class ConsoleDriver {
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

            String player = "";
            if(game.isCurrentPlayer()){
                player = "white";
            }
            else{
                player = "black";
            }

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

                    valid = true;
                    game.nextMove(x,y,newX,newY);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    valid = false;
                }
            }
            if (game.checkmate()){
                hasWon = true;
                System.out.println(player + " has won with a checkmate");
            }
            else{
                game.changePlayer();
            }

        }
    }

    public static int convertX(String str)
    {
        return (int)str.charAt(0) - 65;
    }

    public static int convertY(int y)
    {
        return 8-y;
    }
}
