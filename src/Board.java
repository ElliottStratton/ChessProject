/**
 * Runs a chess board
 */
public class Board {
    Piece[][] board;


    /**
     * creates a board with every piece in the correct positions
     */
    public Board() {
        board = new Piece[8][8];
//        Rook bROne = new Rook(false,0,0,this);
//        Rook bRTwo = new Rook(false,0,7,this);
//        Knight bKOne = new Knight(false,0,1,this);
//        Knight bKTwo = new Knight(false,0,6,this);
//        Bishop bBOne = new Bishop(false,0,2,this);
//        Bishop bBTwo = new Bishop(false,0,5,this);
//        King bKing = new King(false,0,3,this);
//        Queen bQueen = new Queen(false,0,4,this);
//
//        Pawn bPOne = new Pawn(false,1,0,this);
//        Pawn bPTwo = new Pawn(false,1,1,this);
//        Pawn bPThree = new Pawn(false,1,2,this);
//        Pawn bPFour = new Pawn(false,1,3,this);
//        Pawn bPFive = new Pawn(false,1,4,this);
//        Pawn bPSix = new Pawn(false,1,5,this);
//        Pawn bPSeven = new Pawn(false,1,6,this);
//        Pawn bPEight = new Pawn(false,1,7,this);
//
//
//        Pawn wPOne = new Pawn(false,6,0,this);
//        Pawn wPTwo = new Pawn(false,6,1,this);
//        Pawn wPThree = new Pawn(false,6,2,this);
//        Pawn wPFour = new Pawn(false,6,3,this);
//        Pawn wPFive = new Pawn(false,6,4,this);
//        Pawn wPSix = new Pawn(false,6,5,this);
//        Pawn wPSeven = new Pawn(false,6,6,this);
//        Pawn wPEight = new Pawn(false,6,7,this);
//
//        Rook wROne = new Rook(false,7,0,this);
//        Rook wRTwo = new Rook(false,7,7,this);
//        Knight wKOne = new Knight(false,7,1,this);
//        Knight wKTwo = new Knight(false,7,6,this);
//        Bishop wBOne = new Bishop(false,7,2,this);
//        Bishop wBTwo = new Bishop(false,7,5,this);
//        King wKing = new King(false,7,3,this);
//        Queen wQueen = new Queen(false,7,4,this);
    }

    /**
     * constructor for testing
     * @param e doesnt matter just for differentiating from the other constructor
     */
    public Board(int e) {
        board = new Piece[8][8];
    }

    /**
     *
     * @param x coordinate of the piece
     * @param y coordinate of the piece
     * @return the piece at any location on the board
     */
    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    /**
     * puts a piece in a certain x y position
     * @param piece
     * @param x
     * @param y
     */
    public void occupy(Piece piece, int x, int y) {
        board[x][y] = piece;
    }

    /**
     * removes a piece from a position (used for moving)
     * @param x
     * @param y
     */
    public void unOccupy(int x, int y) {
        board[x][y] = null;
    }

    /**
     *
     * @return a string board where each piece is two letters, the first is either black or white
     * the second tells what type of piece it is
     */
    @Override
    public String toString() {
        String b = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    b = b + "0 ";
                } else {
                    if (board[i][j]instanceof Pawn) {
                        if(board[i][j].white) {
                            b = b + "wP";
                        } else {
                            b = b + "bP";
                        }
                    } else if (board[i][j]instanceof Rook) {
                        if(board[i][j].white) {
                            b = b + "wR";
                        } else {
                            b = b + "bR";
                        }
                    } else if (board[i][j]instanceof King) {
                        if(board[i][j].white) {
                            b = b + "wK";
                        } else {
                            b = b + "bK";
                        }
                    } else if (board[i][j]instanceof Queen) {
                        if(board[i][j].white) {
                            b = b + "wQ";
                        } else {
                            b = b + "bQ";
                        }
                    } else if (board[i][j]instanceof Bishop) {
                        if(board[i][j].white) {
                            b = b + "wB";
                        } else {
                            b = b + "bB";
                        }
                    } else if (board[i][j]instanceof Knight) {
                        if (board[i][j].white) {
                            b = b + "wH";
                        } else {
                            b = b + "bH";
                        }
                    }

            }
            }
            b = b + "\n---------------\n";
        }
        return b;
    }


}
