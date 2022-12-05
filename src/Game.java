import java.util.*;

public class Game {
    private Board board;
    private boolean currentPlayer;
    private Map<Piece, ArrayList<String>> movesOutOfCheck;
    private Map<Piece, String> movesIntoCheck;

    /**
     * Default constructor
     * intitializes board and sets current player to false (team white)
     */
    public Game() {
        board = new Board();
        currentPlayer = true; // True is player white
        movesOutOfCheck = new HashMap<>();
        movesIntoCheck = new HashMap<>();
    }

    /**
     * @return true if king is in checkmate, false otherwise
     */
    public boolean checkmate() {
        if (movesOutOfCheck.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void findMovesOutOfCheck(boolean player) {
        for (Piece p : board.arrPieces) { //All pieces on the board
            ArrayList<String> movesOut = new ArrayList<>();
            if (p.white == player && !movesIntoCheck.isEmpty()) { //Just the current Player's pieces
                for (String move : p.possibleMoves()) { //All possible moves of that piece
                    Board nB = new Board(board);
                    nB.movePiece(p, p.x, p.y);
                    if (check(player, nB)) {
                        movesOut.add(move);
                    }
                }
            }
            movesOutOfCheck.put(p, movesOut);
        }
    }

    /**
     * @return true if king is in check, false otherwise
     */
    public boolean check(boolean player, Board board) {
        movesIntoCheck.clear();
        movesOutOfCheck.clear();
        findMovesOutOfCheck(player);

        boolean check = false;
        Piece king = new King();
        for (Piece p : board.arrPieces) { //Find the King of the current player
            if (p.white == player) {
                if (p instanceof King) {
                    king = p;
                }
            }
        }
        String kingLocation = king.translateNum(new ArrayList<Integer>(List.of(king.x, king.y)));

        for (Piece p : board.arrPieces) { //All pieces on the board
            if (p.white != player) {
                String checkMove = "";
                if (!p.possibleMoves().isEmpty()) {
                    for (String move : p.possibleMoves()) { //All possible moves of the opponent's piece(s)
                        if (Objects.equals(move, kingLocation)) {
                            checkMove = move;
                            check = true;
                        }
                    }
                }
                if (check) {
                    movesIntoCheck.put(p, checkMove);
                }
            }
        }
        return check;
    }


    /**
     * @param x1 initial piece x coordinate
     * @param y1 initial piece y coordinate
     * @param x2 where you want to move in the x
     * @param y2 where you want to move in the y
     */
    public void nextMove(int x1, int y1, int x2, int y2) throws IllegalArgumentException {
        if (x1 < 0 || y1 < 0 || x1 > 7 || y1 > 7) {
            throw new IllegalArgumentException("Initial piece coordinates are out of bounds.");
        }

        Piece currPiece = board.getPiece(x1, y1);
        if (currPiece.white != currentPlayer) {
            throw new IllegalArgumentException("This is not your piece to move.");
        } else {
            try {
                currPiece.move(x2, y2); //move() throws an IllegalArgumentException with a message for each possible incorrect solution
            } catch (IllegalArgumentException e) {
                throw e;
            }
        }
        /*
         * Errors: if you try to move a piece that's not yours
         * if you try to move into the same spot
         * if you try to move from an empty space
         * if you try to move to a place occupied by your piece or another piece is in the way
         *
         * */
    }

    /**
     * changes player from one player to another
     */
    public void changePlayer() {
        if (currentPlayer == true) {
            currentPlayer = false;
        } else {
            currentPlayer = true;
        }
    }

    /**
     * This method returns the current player.
     *
     * @return True is player white, false is player black.
     */
    public boolean isCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param white determines which team you want to see the moves of
     * @return an arraylist of every single possible move of a color
     */
    public ArrayList<String> allPossibleMoves(boolean white) {
        return null;
    }

    /**
     * castling method will allow for the castle move if the requirements for the rook and king positions is met
     */
    public void castling(boolean player, String kingMove) {
        Piece king = new King();
        for (Piece piece : board.arrPieces) { //Finds the King
            if (piece instanceof King){
                king = piece;
            }
        }
        if (king.firstMove) { //TODO make sure the spaces in between he king and the rook are all clear
            int x = king.translateLetNum(kingMove).get(0);
            int y = king.translateLetNum(kingMove).get(1);
            Piece thisRook;
            if (x < king.x && y == king.y) {
                thisRook = board.getPiece(0, y);
                if (thisRook != null && thisRook.firstMove) {
                    king.setPosition(x, y);
                    thisRook.setPosition(thisRook.x+3, thisRook.y );
                }
            } else {
                thisRook = board.getPiece(8, y);
                if (thisRook != null && thisRook.firstMove) {
                    king.setPosition(x, y);
                    thisRook.setPosition(thisRook.x-2, thisRook.y );
                }
            }
        }
        else{
            System.out.println("Invalid castling move");
        }
    }

    /**
     * pawnPromotion method will prompt the player to choose which piece to promote a pawn to if it reaches the opposite
     * side of the board.
     */
    public void pawnPromotion(boolean player, Piece pawn) {
        boolean valid = false;
        boolean promotion = false;
        Scanner scnr = new Scanner(System.in);
        if (player){
            if (pawn.y == 8){
                valid = true;
            }
        }
        else{
            if (pawn.y == 1){
                valid = true;
            }
        }

        if(valid) {
            while(!promotion) {
                System.out.println("Would you like to promote this pawn to a  queen, rook, bishop, or knight?");
                String choice = scnr.next().toLowerCase();
                if (choice.equals("queen")) {
                    board.board[pawn.y][pawn.x] = null;
                    Queen queen = new Queen(pawn.white, pawn.x, pawn.y, 900, board);
                    queen.firstMove = false;
                    promotion = true;
                } else if (choice.equals("rook")) {
                    board.board[pawn.y][pawn.x] = null;
                    Rook rook = new Rook(pawn.white, pawn.x, pawn.y, 500, board);
                    rook.firstMove = false;
                    promotion = true;
                } else if (choice.equals("bishop")) {
                    board.board[pawn.y][pawn.x] = null;
                    Bishop bishop = new Bishop(pawn.white, pawn.x, pawn.y, 300, board);
                    bishop.firstMove = false;
                    promotion = true;
                } else if (choice.equals("knight")) {
                    board.board[pawn.y][pawn.x] = null;
                    Queen queen = new Queen(pawn.white, pawn.x, pawn.y, 900, board);
                    queen.firstMove = false;
                    promotion = true;
                } else {
                    System.out.println("Invalid piece entry. Please reenter choice.");
                }
            }
        }
    }

    /**
     * This method is a simple getter for Board
     *
     * @return Board is the current board
     */
    public Board getBoard() {
        return board;
    }
}