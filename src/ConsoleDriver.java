import java.util.Scanner;

public class ConsoleDriver {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter Y to Start game or N to close");
        boolean start = false;
        if (scnr.next().charAt(0) == 'Y'){
            start = true;
        }
//        Game game = new Game();
        Board board = new Board();

//        while(!hasWon){
//            System.out.println("enter move");
//            try{
//                game.nextMove(x,y,x,y);
//            }
//            catch{
//
//            }
//        }

    }
}
