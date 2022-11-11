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
                    System.out.println("enter space of piece you want to move");
                    int y = scnr.nextInt();
                    int x = scnr.nextInt();

                    System.out.println("enter space where you want to move the piece");
                    int newY = scnr.nextInt();
                    int newX = scnr.nextInt();

                    valid = true;
                    game.nextMove(x,y,newX,newY);
                }
                catch(Exception e){
                    System.out.println("Invalid move attempt");
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
