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
        Rook bRTwo = new Rook(false,0,7,5,this);
        arrPieces.add(bRTwo);
        Knight bKOne = new Knight(false,0,1,3,this);
        arrPieces.add(bKOne);
        Knight bKTwo = new Knight(false,0,6,3,this);
        arrPieces.add(bKTwo);
        Bishop bBOne = new Bishop(false,0,2,3,this);
        arrPieces.add(bBOne);
        Bishop bBTwo = new Bishop(false,0,5,3,this);
        arrPieces.add(bBTwo);
        King bKing = new King(false,0,3,18,this);
        arrPieces.add(bKing);
        Queen bQueen = new Queen(false,0,4,9,this);
        arrPieces.add(bQueen);

        Pawn bPOne = new Pawn(false,1,0, 1,this);
        arrPieces.add(bPOne);
        Pawn bPTwo = new Pawn(false,1,1, 1,this);
        arrPieces.add(bPTwo);
        Pawn bPThree = new Pawn(false,1,2 , 1,this);
        arrPieces.add(bPThree);
        Pawn bPFour = new Pawn(false,1,3, 1,this);
        arrPieces.add(bPFour);
        Pawn bPFive = new Pawn(false,1,4, 1,this);
        arrPieces.add(bPFive);
        Pawn bPSix = new Pawn(false,1,5, 1,this);
        arrPieces.add(bPSix);
        Pawn bPSeven = new Pawn(false,1,6, 1,this);
        arrPieces.add(bPSeven);
        Pawn bPEight = new Pawn(false,1,7, 1,this);
        arrPieces.add(bPEight);


        Pawn wPOne = new Pawn(true,6,0, 1,this);
        arrPieces.add(wPOne);
        Pawn wPTwo = new Pawn(true,6,1, 1,this);
        arrPieces.add(wPTwo);
        Pawn wPThree = new Pawn(true,6,2, 1,this);
        arrPieces.add(wPThree);
        Pawn wPFour = new Pawn(true,6,3, 1,this);
        arrPieces.add(wPFour);
        Pawn wPFive = new Pawn(true,6,4, 1,this);
        arrPieces.add(wPFive);
        Pawn wPSix = new Pawn(true,6,5, 1,this);
        arrPieces.add(wPSix);
        Pawn wPSeven = new Pawn(true,6,6, 1,this);
        arrPieces.add(wPSeven);
        Pawn wPEight = new Pawn(true,6,7, 1,this);
        arrPieces.add(wPEight);

        Rook wROne = new Rook(true,7,0,5,this);
        arrPieces.add(wROne);
        Rook wRTwo = new Rook(true,7,7,5,this);
        arrPieces.add(wRTwo);
        Knight wKOne = new Knight(true,7,1,3,this);
        arrPieces.add(wKOne);
        Knight wKTwo = new Knight(true,7,6,3,this);
        arrPieces.add(wKTwo);
        Bishop wBOne = new Bishop(true,7,2,3,this);
        arrPieces.add(wBOne);
        Bishop wBTwo = new Bishop(true,7,5,3,this);
        arrPieces.add(wBTwo);
        King wKing = new King(true,7,3,18,this);
        arrPieces.add(wKing);
        Queen wQueen = new Queen(true,7,4,9,this);
        arrPieces.add(wQueen);
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
            if (i<7){
                b = b + "\n---------------\n";
            }
        }
        b = b + "\n";
        return b;
    }


}
