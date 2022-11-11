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
        Board board = new Board();

        boolean hasWon = false;
        while(!hasWon){
            boolean valid = false;
            while(!valid){
                try{
                    System.out.println("enter space of piece you want to move");
                    char y = scnr.next().charAt(0);
                    int x = scnr.nextInt();

                    System.out.println("enter space where you want to move the piece");
                    char newY = scnr.next().charAt(0);
                    int newX = scnr.nextInt();

                    valid = true;
                    game.nextMove(x,y,newX,newY);
                }
                catch(Exception e){
                    System.out.println("Invalid move attempt");
                    valid = false;
                }
            }
            if (game.checkmate()){
                if(game.isCurrentPlayer()){
                    String player = "white";
                }
                System.out.println(game.isCurrentPlayer() + "");
            }
        }
    }
}
