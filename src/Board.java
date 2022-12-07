import java.util.ArrayList;

/**
 * Runs a chess board
 */
public class Board {
    Piece[][] board;
    ArrayList<Piece> arrPieces = new ArrayList<>(); //Holds all the pieces on the board

    /**
     * creates a board with every piece in the correct positions
     */
    public Board() {
        board = new Piece[8][8];
        Rook bROne = new Rook(false,0,0,5,this);
        arrPieces.add(bROne);
        Rook bRTwo = new Rook(false,7,0,5,this);
        arrPieces.add(bRTwo);
        Knight bKOne = new Knight(false,1,0,3,this);
        arrPieces.add(bKOne);
        Knight bKTwo = new Knight(false,6,0,3,this);
        arrPieces.add(bKTwo);
        Bishop bBOne = new Bishop(false,2,0,3,this);
        arrPieces.add(bBOne);
        Bishop bBTwo = new Bishop(false,5,0,3,this);
        arrPieces.add(bBTwo);
        King bKing = new King(false,3,0,18,this);
        arrPieces.add(bKing);
        Queen bQueen = new Queen(false,4,0,9,this);
        arrPieces.add(bQueen);

        Pawn bPOne = new Pawn(false,0,1, 1,this);
        arrPieces.add(bPOne);
        Pawn bPTwo = new Pawn(false,1,1, 1,this);
        arrPieces.add(bPTwo);
        Pawn bPThree = new Pawn(false,2,1 , 1,this);
        arrPieces.add(bPThree);
        Pawn bPFour = new Pawn(false,3,1, 1,this);
        arrPieces.add(bPFour);
        Pawn bPFive = new Pawn(false,4,1, 1,this);
        arrPieces.add(bPFive);
        Pawn bPSix = new Pawn(false,5,1, 1,this);
        arrPieces.add(bPSix);
        Pawn bPSeven = new Pawn(false,6,1, 1,this);
        arrPieces.add(bPSeven);
        Pawn bPEight = new Pawn(false,7,1, 1,this);
        arrPieces.add(bPEight);


        Pawn wPOne = new Pawn(true,0,6, 1,this);
        arrPieces.add(wPOne);
        Pawn wPTwo = new Pawn(true,1,6, 1,this);
        arrPieces.add(wPTwo);
        Pawn wPThree = new Pawn(true,2,6, 1,this);
        arrPieces.add(wPThree);
        Pawn wPFour = new Pawn(true,3,6, 1,this);
        arrPieces.add(wPFour);
        Pawn wPFive = new Pawn(true,4,6, 1,this);
        arrPieces.add(wPFive);
        Pawn wPSix = new Pawn(true,5,6, 1,this);
        arrPieces.add(wPSix);
        Pawn wPSeven = new Pawn(true,6,6, 1,this);
        arrPieces.add(wPSeven);
        Pawn wPEight = new Pawn(true,7,6, 1,this);
        arrPieces.add(wPEight);

        Rook wROne = new Rook(true,0,7,5,this);
        arrPieces.add(wROne);
        Rook wRTwo = new Rook(true,7,7,5,this);
        arrPieces.add(wRTwo);
        Knight wKOne = new Knight(true,1,7,3,this);
        arrPieces.add(wKOne);
        Knight wKTwo = new Knight(true,6,7,3,this);
        arrPieces.add(wKTwo);
        Bishop wBOne = new Bishop(true,2,7,3,this);
        arrPieces.add(wBOne);
        Bishop wBTwo = new Bishop(true,5,7,3,this);
        arrPieces.add(wBTwo);
        King wKing = new King(true,3,7,18,this);
        arrPieces.add(wKing);
        Queen wQueen = new Queen(true,4,7,9,this);
        arrPieces.add(wQueen);
    }

    /**
     * constructor for testing
     * @param e doesnt matter just for differentiating from the other constructor
     */
    public Board(int e) {
        board = new Piece[8][8];
    }


    public Board(Board b) {
        board = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = b.board[i][j];
            }
        }
    }


    /**
     *
     * @param x coordinate of the piece
     * @param y coordinate of the piece
     * @return the piece at any location on the board
     */
    public Piece getPiece(int x, int y) {
        return board[y][x];
    }


    /**
     * Moves a piece from where it is to a possible desired location
     * @param y the y coordinate for the desired location
     * @param x the x coordinate for the desired location
     * @param p the piece that needs to be moved
     * */
    public void movePiece(Piece p, int x, int y){
        p.move(x,y);
    }


    /**
     * A basic getter for the 2d array Board
     * @return a 2d array of the current board
     * */
    public Piece[][] getBoard() {
        return board;
    }

    /**
     * puts a piece in a certain x y position
     * @param piece
     * @param x
     * @param y
     */
    public void occupy(Piece piece, int x, int y) {
        board[y][x] = piece;
    }

    /**
     * removes a piece from a position (used for moving)
     * @param x
     * @param y
     */
    public void unOccupy(int x, int y) {
        board[y][x] = null;
    }

    /**
     *
     * @return true if king is in checkmate, false otherwise
     */
    public boolean checkmate() //For testing and AI purposes
    {
        return false;
    }

    public Piece getWKing() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j]instanceof King) {
                    if (((King) board[i][j]).white) {
                        return board[i][j];
                    }
                }
            }
        }
        return null;
    }

    public Piece getBKing() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j]instanceof King) {
                    if (!((King) board[i][j]).white) {
                        return board[i][j];
                    }
                }
            }
        }
        return null;
    }

    /**
     *
     * @return a string board where each piece is two letters, the first is either black or white
     * the second tells what type of piece it is
     */
    @Override
    public String toString() {
        String b = "\n";
        for (int i = 0; i < 8; i++) {
            b = b + (8-(i) + " ");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    b = b  + "\u2003  " + (j == 7 ? "" : "|");
                } else {
                    if (board[i][j]instanceof Pawn) {
                        if(!board[i][j].white) {
                            b = b + " \u2659 ";
                        } else {
                            b = b + " \u265F ";
                        }
                        String end = (j == 7 ? "" : "|");
                        b += end;
                    } else if (board[i][j]instanceof Rook) {
                        if(!board[i][j].white) {
                            b = b + " \u2656 ";
                        } else {
                            b = b + " \u265C ";
                        }
                        String end = (j == 7 ? "" : "|");
                        b += end;
                    } else if (board[i][j]instanceof King) {
                        if(!board[i][j].white) {
                            b = b + " \u2654 ";
                        } else {
                            b = b + " \u265A ";
                        }
                        String end = (j == 7 ? "" : "|");
                        b += end;
                    } else if (board[i][j]instanceof Queen) {
                        if(!board[i][j].white) {
                            b = b + " \u2655 ";
                        } else {
                            b = b + " \u265B ";
                        }
                        String end = (j == 7 ? "" : "|");
                        b += end;
                    } else if (board[i][j]instanceof Bishop) {
                        if(!board[i][j].white) {
                            b = b + " \u2657 ";
                        } else {
                            b = b + " \u265D ";
                        }
                        String end = (j == 7 ? "" : "|");
                        b += end;
                    } else if (board[i][j]instanceof Knight) {
                        if (!board[i][j].white) {
                            b = b + " \u2658 ";
                        } else {
                            b = b + " \u265E ";
                        }
                        String end = (j == 7 ? "" : "|");
                        b += end;
                    }
                }
            }
            if (i<7){
                b = b + "\n  ------------------------------------\n";
            }
        }
        b = b + ("\n\u2003\u2003A\u2002   B\u2003  C\u2003  D\u2003  E\u2003  F\u2003  G\u2003  H");
        b = b + "\n";
        return b;
    }
}
