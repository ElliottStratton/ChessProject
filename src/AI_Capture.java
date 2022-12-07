import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class AI_Capture extends AI{
    /**
     * This AI will prioritize moves that take opponent's pieces.
     * It will go through the arraylist of all possible moves and make a new ArrayList of moves that take pieces.
     * Then it will find the move that takes the piece with the highest assigned value.
     *      If none of the moves take pieces, then do a random move
     *      If there are two moves with the highest piece value, then do the second.
     *
     * Could assign pieces a value and have a queue that store the value of each move
     * Then chose the piece that takes the highest valued opponent's piece
     *
     * */

    //COMPLETE FOR CURRENT NEEDS


    private ArrayList<Piece> opponentPieces;
    private ArrayList<String> oppPositions;//Contains all the locations of the opponent's pieces
    private ArrayList<Move> capturePieces;

    /**
     * A basic Constructor
     * */
    public AI_Capture(){
        oppPositions = new ArrayList<>();
        capturePieces = new ArrayList<>();
    }

    /**
     * This method creates a list of a single player's pieces and reaturns them
     * @param player The player's pieces you want to gather
     * @param board The current board of the game
     * @return An arraylist of all of a single player's peice currently on the board
     * */
    public ArrayList<Piece> getPieces(boolean player, Board board) { //Returns an arrayList of all the pieces of a specific player
        ArrayList<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < board.arrPieces.size(); i++) {
            if (board.arrPieces.get(i).white == player) {
                pieces.add(board.arrPieces.get(i));
            }
        }
        return pieces;
    }//end method

    /**
     * It takes a piece and returns the location in chess notation
     * @param currPiece
     * @return a string that is the currPiece's location
     * */
    public String getLocNotation(Piece currPiece){ //Gets the chess notation location of a specific piece from x,y coordinates
        return currPiece.translateNum( new ArrayList<>(List.of(currPiece.getX(), currPiece.getY())));
    }

    /**
     * This method searches through the current players pieces and finds a piece that can capture an opponent's piece
     * @param player the current player
     * @param board the current state of the board
     * @return the piece that has an option to capture an opponent's piece
     * */
    public Move randCapture(boolean player, Board board){
        Random rand1 = new Random();
        Move capMove;
        Piece currPiece;
        opponentPieces = getPieces(!player, board); //Stores a list of all the opponent's pieces

        for (Piece opponentPiece : opponentPieces) { //Creating an ArrayList of the positions of all opponent's pieces
            oppPositions.add(getLocNotation(opponentPiece));
        }

        for (int i = 0; i < getPieces(player, board).size(); i++) {
            currPiece = getPieces(player, board).get(i);

            for (int j = 0; j < currPiece.possibleMoves().size(); j++) { //Loops through possible moves of currPiece
                String loc = currPiece.possibleMoves().get(j);

                for (int k = 0; k < oppPositions.size(); k++) { //Loops through location of opponents pieces
                    String oppLoc = oppPositions.get(k);
                    if (Objects.equals(loc, oppLoc)) {
                        capMove = new Move(loc, currPiece);
                        capturePieces.add(capMove);
                        break;
                    }
                }
            }
        }
        if (capturePieces.isEmpty()){
            AI_Random r = new AI_Random();
            return r.executeMove(player, board);
        }
        return capturePieces.get(rand1.nextInt(capturePieces.size()));
    }//end method

    /**
     * Runs randCapture() and executes the move that captures a piece
     * @param player the current player
     * @param board the current state of the board
     * */
    public Move executeMove(boolean player, Board board){
        Move move = randCapture(player, board);
        return move;
    }//end method
}
