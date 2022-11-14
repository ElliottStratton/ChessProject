import java.util.Scanner;

public class ConsoleDriver {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
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
                    System.out.println("enter row number of the space of piece you want to move");
                    int x = scnr.nextInt();
                    System.out.println("enter column number of the space of piece you want to move");
                    int y = scnr.nextInt();

                    System.out.println("enter row number of the space you want to move the piece to");
                    int newX = scnr.nextInt();
                    System.out.println("enter row number of the space you want to move the piece to");
                    int newY = scnr.nextInt();

                    valid = true;
                    game.nextMove(x,y,newX,newY);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    valid = false;
                }
            }
            if (game.hasWon()){
                hasWon = true;
                System.out.println(player + " has won with a checkmate");
            }
            else{
                game.changePlayer();
            }

        }
    }
}
