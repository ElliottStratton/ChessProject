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

//    /**
//     * @return true if king is in checkmate, false otherwise
//     */
//    public boolean checkmate(boolean player, Board board) {
//        if (check(player, board) && movesOutOfCheck.isEmpty()) {
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public void findMovesOutOfCheck(boolean player) {
//        for (Piece p : board.arrPieces) { //All pieces on the board
//            ArrayList<String> movesOut = new ArrayList<>();
//            if (p.white == player && !movesIntoCheck.isEmpty()) { //Just the current Player's pieces
//                for (String move : p.possibleMoves()) { //All possible moves of that piece
//                    Board nB = new Board(board);
//                    nB.movePiece(p, p.x, p.y);
//                    if (check(player, nB)) {
//                        movesOut.add(move);
//                    }
//                }
//            }
//            movesOutOfCheck.put(p, movesOut);
//        }
//    }

//    /**
//     * @return true if king is in check, false otherwise
//     */
//    public boolean check(boolean player, Board board) {
//
//        movesIntoCheck.clear();
//        movesOutOfCheck.clear();
//        findMovesOutOfCheck(player);
//
//        boolean check = false;
//        Piece king = new King();
//        for (Piece p : board.arrPieces) { //Find the King of the current player
//            if (p.white == player) {
//                if (p instanceof King) {
//                    king = p;
//                }
//            }
//        }
//        String kingLocation = king.translateNum(new ArrayList<Integer>(List.of(king.x, king.y)));
//
//        for (Piece p : board.arrPieces) { //All pieces on the board
//            if (p.white != player) {
//                String checkMove = "";
//                if (!p.possibleMoves().isEmpty()) {
//                    for (String move : p.possibleMoves()) { //All possible moves of the opponent's piece(s)
//                        if (Objects.equals(move, kingLocation)) {
//                            checkMove = move;
//                            check = true;
//                        }
//                    }
//                }
//                if (check) {
//                    movesIntoCheck.put(p, checkMove);
//                }
//            }
//        }
//        return check;
//    }

    public boolean check(Board b, boolean player) {
        King king = new King();
        for (Piece p : b.arrPieces) { //Find the King of the current player
            if (p.white == player) {
                if (p instanceof King) {
                    king = (King) p;
                }
            }
        }
        ArrayList<String> allPossMoves = new ArrayList<>();
        for (int i = 0; i < b.arrPieces.size(); i++) {
            if (b.arrPieces.get(i).white != player) {
                allPossMoves.addAll(b.arrPieces.get(i).possibleMoves());
            }
        }
        for (String s : allPossMoves) {
            if(king.x == Convert.convertX(s.charAt(0)) && king.y == Convert.convertY(s.charAt(1))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkMove(Piece p, int x, int y) {
        if(p instanceof King) {
            ArrayList<String> allPossMoves = new ArrayList<>();
            for (int i = 0; i < board.arrPieces.size(); i++) {
                if (board.arrPieces.get(i).white == currentPlayer) {
                    allPossMoves.addAll(board.arrPieces.get(i).possibleMoves());
                }
            }
            for (String s : allPossMoves) {
                if(x == Convert.convertX(s.charAt(0)) && y == Convert.convertY(s.charAt(1))) {
                    return true;
                }
            }
        } else {
            board.unOccupy(p.x, p.y);
            if(check(board, currentPlayer)) {
                board.occupy(p, p.x, p.y);
                return true;
            }
        }

        return false;
    }

    public boolean checkmate() {
        King king = new King();
        for (Piece p : board.arrPieces) { //Find the King of the current player
            if (p.white != currentPlayer) {
                if (p instanceof King) {
                    king = (King) p;
                }
            }
        }

        ArrayList<String> allPossMoves = new ArrayList<>();
        for (int i = 0; i < board.arrPieces.size(); i++) {
            if (board.arrPieces.get(i).white == currentPlayer) { //might need to be != here
                allPossMoves.addAll(board.arrPieces.get(i).possibleMoves());
            }
        }
        int i = 0;
        for (String s : king.possibleMoves()) {
            for (String g : allPossMoves) {
                if (s.equalsIgnoreCase(g)) {
                    ++i;
                    break;
                }
            }
        }
        if (i == king.possibleMoves().size()) {
            for(Piece p : board.arrPieces) {
                if(p.white != currentPlayer) {
                    for(String s : p.possibleMoves()) {
                        Board b = new Board(board);
                        System.out.println(p);
                        System.out.println(p.possibleMoves());
                        System.out.println(b);
                        board.movePiece(p, Convert.convertX(s.charAt(0)), Convert.convertY(s.charAt(1)));
                        if(!check(b, !currentPlayer)) {
                            return false;
                        }
                    }
                }
            }



        }


        return true;
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
            if(!checkMove(board.getPiece(x1,y1), x2, y2)) {
                try {
                    currPiece.move(x2, y2); //move() throws an IllegalArgumentException with a message for each possible incorrect solution
                } catch (IllegalArgumentException e) {
                    throw e;
                }
            } else {
                throw new IllegalArgumentException("Cannot move into check.");
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
    //Make this method add the move to king possible moves
    //In console driver check if the piece being moved is a King.
    //If so, check if the move is larger than 1 move away, then find the move and move the rook accordingly
    //EN PASSANT keep track of the last move and if it was the first move and if the y change is more than 1 and if it is found next to an enemy pawn place the location.y-1 behind it in the possible moves
    public void castling(String kingMove) { //TODO how do we want these special moves to operate?
        Piece king = new King();
        for (Piece piece : board.arrPieces) { //Finds the King
            if (piece instanceof King){
                king = piece;
            }
        }

        int x = king.translateLetNum(kingMove).get(0);
        int y = king.translateLetNum(kingMove).get(1);

        if (king.firstMove) { //if the king hasn't moved
            Piece thisRook;
            if (x < king.x && y == king.y) { //King side castle
                thisRook = board.getPiece(0, y);
                if (thisRook != null && thisRook.firstMove && board.getPiece(1,y) == null && board.getPiece(2, y) == null && board.getPiece(3, y) == null){ //if a rook is there, if it has not moved, and if all the spaces in between them are empty
                    king.setPosition(x, y);
                    thisRook.setPosition(thisRook.x+3, thisRook.y );
                }
            } else if(x > king.x && y == king.y) { //Queen side castle
                thisRook = board.getPiece(8, y);
                if (thisRook != null && thisRook.firstMove && board.getPiece(7,y) == null && board.getPiece(6, y) == null) { //if a rook is there, if it has not moved, and if all the spaces in between them are empty
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
     * Takes in a pawn and checks if a promotion is possible
     * @param player the current player
     * @param pawn the pawn to check for a promotion
     * @return true if a promotion is possible
     * */
    public boolean promotionPossible(boolean player, Piece pawn){
        boolean valid = false;
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
        return valid;
    }

    /**
     * pawnPromotion method will prompt the player to choose which piece to promote a pawn to if it reaches the opposite
     * side of the board.
     * @param player
     * @param pawn
     */
    public void pawnPromotion(boolean player, Piece pawn) {
        boolean promotion = false;
        Scanner scnr = new Scanner(System.in);

        if(promotionPossible(player, pawn)) { //if a pawn is in the place to be promoted
            System.out.println("Would you like to promote this pawn to a  queen, rook, bishop, or knight?");
            while(!promotion) { //to ensure a valid input
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
            } //end while
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