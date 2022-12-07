import java.util.ArrayList;
import java.util.Random;

public class AI_Random extends AI{
    /**
     * This class will make a random move for a random piece. It will create two different random numbers.
     * The first will be the piece on the board and the second will be of that move option.
     */

    //COMPLETE FOR CURRENT NEEDS


    /**
     * @param board
     * @param player
     * @return
     * */
    public ArrayList<Piece> getNumPieces(boolean player, Board board) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < board.arrPieces.size(); i++) {
            if (board.arrPieces.get(i).white == player) {
                pieces.add(board.arrPieces.get(i));
            }
        }
        return pieces;
    }

    //If a piece has a possible move then add the piece then pick a piece at random and then create the arraylist of possible moves

    public void executeMove(boolean player, Board board, int n) { //Complete as far as I know
        boolean found = false;
        Random rand1 = new Random();
        int randPiece = 0;
        Piece piece = getNumPieces(player, board).get(0);
        while (!found) {
            randPiece = rand1.nextInt(getNumPieces(player, board).size());
            Piece currPiece = getNumPieces(player, board).get(randPiece);
            if (!currPiece.possibleMoves().isEmpty()) {
                piece = currPiece;
                found = true;
            }
        }
        int x = piece.translateLetNum(piece.possibleMoves().get(0)).get(0);
        int y = piece.translateLetNum(piece.possibleMoves().get(0)).get(1);

        piece.move(x,y);
    }
}
