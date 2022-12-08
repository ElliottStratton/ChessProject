import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.Math.*;

public class AI_AlphaBeta extends AI{

    /**
     * This AI uses Minimax Alpha Beta pruning to find the best move for the current player
     * */

    //TODO if this needs to be faster, implement possibleMoves() where it searches just the possible moves rather than the entire board

    //Iterative Deepening //TODO only look at this when everything else is perfect
    //
    //Favor boards that have the opponent's king closer to the corners
    //And where the distance between the kings is smaller
    //Change the weight of these positions as they grow closer to the endgame
    //Find boards that allow for no possible captures in the next move from the opponent
    //In order to create a greater possibility for pruning to occur, place the more valuable turns first.
    //Search through the pawns first because if they are able to capture a piece, then they will have a very high value
    // Common additions are: pawn structure, ability to castle, development of pieces (their locations), and King safety
    // Additional value elements
    //Make the value for promotions very high
    //Takes into account the difference between a piece and the piece it captures
    //If a pawn captures a Queen, the value is very high.
    //Return the value difference.
    //Create high values for check and checkmate



    //Start with best pieces
    //Iterative

    //Give me the best move in 5 seconds
    //Goodness Value
    //Piece values and add those up

    //Major ways to speed it up are set time for running and alpha beta pruning


    //Chess heuristic for alpha beta pruning.
    //See if a specific piece can be taken on the next turn
    //What the capture ratio is of this move
    //Capture ratio of the last move was a capture
    //if a pawn takes a queen but is then taken by a pawn
    //It is an okay trade
    //if a queen takes a pawn but is then taken by a pawn
    //It is not an okay trade
    //Find the maps for the most strategic positions of each piece
    //Weight that piece

    //Ordering of moves in order to prune the most possible moves
    //such as trying captures first, then threats, then forward moves, and then backward moves
    //Also search the ones at the end of the forward moves
    //Bishop, rook, and queen start at the end of the possible forward moves because it is the most likely to have a capture
    //The end will be either hitting an opponent's piece(which it could capture), the edge of a board, or a friendly piece
    //The ordering of moves happens in possible moves.



    private Board board;
    private ArrayList<Move> moves;
    private int bestIndex =0;

    //TODO create a separate makeMove() method that handles pawn promotion, castling, and en passant
    //Make promotion always be a queen


    /**
     * A basic constructor
     * @param board is the current board
     *
     * */
    public AI_AlphaBeta(Board board){
        this.board = board;
    }


    /**
     * Finds all the pieces on that board that belong to that current player and create an arraylist of those pieces
     * @param player the current player
     * @param board the current board
     * @return an arraylist of all the pieces of that player
     * */
    public ArrayList<Piece> getPieces(boolean player, Board board) { //Returns an arrayList of all the pieces of a specific player
        ArrayList<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < board.arrPieces.size(); i++) {
            if (board.arrPieces.get(i).white == player) {
                pieces.add(board.arrPieces.get(i));
            }
        }
        return pieces;
    }

    //Also search the ones at the end of the forward moves
    //Bishop, rook, and queen start at the end of the possible forward moves because it is the most likely to have a capture
    //The end will be either hitting an opponent's piece(which it could capture), the edge of a board, or a friendly piece

    /**
     * Determines if a specific move by a specific piece will capture an opponent's piece
     * @param arrPieces is an arraylist of the current pieces on the board
     * @param player the current player
     * @param move the move in chess notation
     * @param piece the piece that is moving
     * @return true if the piece makes the move and captures a piece
     * */
    public boolean capture(String move, Piece piece, boolean player, ArrayList<Piece> arrPieces){
        int x = piece.translateLetNum(move).get(0);
        int y = piece.translateLetNum(move).get(1);
        boolean captured = false;

        ArrayList<Piece> pieces = new ArrayList<>();
        for (Piece p : arrPieces) {
            if (p.white != player){
                pieces.add(p);
            }
        }
        for (Piece cP: pieces){
            if (x == cP.x && y == cP.y){
                captured = true;
                break;
            }
        }
        return captured;
    }

    /**
     * Takes a piece and determines if any of its possible moves after a single move can capture a piece
     * @param board the current board
     * @param move the desired move
     * @param p the current piece
     * @return true if the piece is a threat
     * */
    public boolean threat(Piece p, Board board, String move){
        Board nBoard = new Board(board);
        boolean isThreat = false;
        int x = p.translateLetNum(move).get(0);
        int y = p.translateLetNum(move).get(1);
        System.out.println(p + " "  + p.white); //For testing
        System.out.println(p.x);
        System.out.println(p.y);
        System.out.println(move);
        nBoard.movePiece(p,x,y);

        for (String loc: p.possibleMoves()){
            if (capture(loc, p, p.white, nBoard.arrPieces)){
                isThreat = true;
                break;
            }
        }
        return isThreat;
    }

    /**
     * Takes a moves and determines if it is a forward move for that piece
     * @param move the current move
     * @param p the current piece
     * @return true if it is a forward move
     * */
    public boolean forward(Piece p, String move){
        boolean isForward = false;
        int y = p.translateLetNum(move).get(1);
        if (p.white){
            if (y > p.y){
                isForward = true;
            }
        }
        else{
            if (y < p.y){
                isForward = true;
            }
        }
        return isForward;
    }

    /**
     * Takes a board and creates a list of Pieces ordered by their average possible effectiveness and each Pieces possible moves ordered by their value
     * @param player is the current player
     * @param board is the current board
     * @return a list of pieces and their respective possible moves
     * */
    public Map<Piece, ArrayList<String>> optimalOrder(boolean player, Board board){
        ArrayList<Piece> playerPieces = getPieces(player, board);

        Map<Piece, Map<Integer, String>> optimalMap = new HashMap<>();

        for (Piece p : playerPieces){
            TreeMap<Integer, String> optimalMoves = new TreeMap<>();
            for (String move : p.possibleMoves()){ //Determines the value of each piece
                int moveV = 0;
                if (capture(move, p, player, board.arrPieces)){
                    moveV = 5;
                }
                else if ( threat(p,board,move)){
                    moveV = 3;
                }
                else if (forward(p, move)) {
                    moveV = 2;
                }
                optimalMoves.put(moveV, move);
                optimalMap.put(p, optimalMoves);
            }
        }

        int avgVals;

        Map<Integer, Piece> orderByValue = new TreeMap<>();
        for(Piece p : optimalMap.keySet()){ //Summing all the values of the possible moves of a piece and averaging them
            Map<Integer, String> ordering = optimalMap.get(p);
            int sumValues = 0;
            for(Integer value : ordering.keySet()){
                sumValues = sumValues + value;
            }
            avgVals = sumValues/ optimalMap.size();
            orderByValue.put(avgVals, p);
        }

        Map<Piece, ArrayList<String>> optimalOrder = new HashMap<>();
        ArrayList<String> treeOrdered = new ArrayList<>();

        for (Integer value : orderByValue.keySet()){ //TODO is there any way so simplify these three for loops????
            for (Piece p : optimalMap.keySet()){
                treeOrdered = new ArrayList<>();
                for (Integer n : optimalMap.get(p).keySet()){
                    treeOrdered.add(optimalMap.get(p).get(n));
                }
            }
            optimalOrder.put(orderByValue.get(value), treeOrdered);
        }
        return optimalOrder;
    }

    /**
     * Takes in a board and returns all the possible boards that could result from that
     * @param board the current board
     * @param player the current player
     * @return an arraylist of boards
     * */
    public ArrayList<Board> possibleBoards(boolean player, Board board){//Returns all possible boards from a specific board
        ArrayList<Board> boards = new ArrayList<>();
        moves = new ArrayList<>();
        Map<Piece, ArrayList<String>> optimal = optimalOrder(player, board);

        for(Piece p : optimal.keySet()){
            for (String s : optimal.get(p)){
                Board newBoard = new Board(board); //Does this make a new one or just change all the current ones each time
                int x = p.translateLetNum(s).get(0);
                int y = p.translateLetNum(s).get(1);
                newBoard.movePiece(p, x, y);
                boards.add(newBoard);
                moves.add(new Move(s, p));
            }
        }
        return boards;
    }

    /**
     * Takes in a board and finds the sum of all the pieces on that board
     * @param currBoard the current board
     * @param player the current player
     * @return the sum of the piece values on that board
     * */
    public int boardValue(boolean player, Board currBoard){ //Finds the value of each board
        int total = 0;
        for (Piece p : currBoard.arrPieces){
            if (p.white){
                total = total + p.value;
            }
            else{
                total = total +(p.value*(-1));
            }
        }
        return total;
    }



    //Initial call will pass negative infinity as alpha and positive infinity as beta
    /**
     *
     * @param alpha alpha for alpha-beta pruning
     * @param beta beta for alpha-beta pruning
     * @param depth the depth of the search
     * @param maximizingPlayer the white player
     * @param position the current board
     * @return the value of a specific board
     * */
    public int minimaxAB(Board position, int depth, int alpha, int beta, boolean maximizingPlayer){
        if (depth == 0 || position.checkmate()){
            return boardValue(maximizingPlayer, position);
        }

        ArrayList<Board> positions = possibleBoards(maximizingPlayer, position);

        bestIndex = 0;
        if (maximizingPlayer){
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0; i < positions.size(); i++) { //Loops through all the children of position or all the possible positions attainable from each possible move.
                Board child = positions.get(i);
                int eval = minimaxAB(child, depth - 1, alpha, beta, false);
                if (maxEval<eval){
                    maxEval = eval;
                    bestIndex = i;
                }
                if (maxEval <= alpha){
                    break;
                }
                alpha = max(alpha, maxEval); //Why does this go after it
            }
            return maxEval;
        }

        else{
            int minEval = Integer.MAX_VALUE;
            for (int i = 0; i < positions.size(); i++) { //Loops through all the children of position or all the possible positions attainable from each possible move.
                Board child = positions.get(i);
                int eval = minimaxAB(child, depth - 1,alpha, beta, true);
                minEval = max(minEval, eval);
                if (minEval > eval){
                    minEval = eval;
                    bestIndex = i;
                }
                if (minEval <= alpha){
                    break;
                }
                beta = min(beta, minEval);
            }
            return minEval;
        }
    }


    public Move executeMove(boolean player, Board board){
        minimaxAB(board, 5, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        Move m = moves.get(bestIndex);
        int x = m.getCurrPiece().translateLetNum(m.getNewLocation()).get(0);
        int y = m.getCurrPiece().translateLetNum(m.getNewLocation()).get(1);
        board.movePiece(m.getCurrPiece(), x, y);
        return m;
    }













    /**
     *
     *
     *
     * */
    public int minimax(Board position, int depth, boolean maximizingPlayer){
        if (depth == 0 || position.checkmate()){
            return boardValue(maximizingPlayer, position); //Return static evaluation of position
        }

        ArrayList<Board> positions = possibleBoards(maximizingPlayer, position);


        if (maximizingPlayer){
            int maxEval = Integer.MIN_VALUE;
            for (Board child : positions) { //Loops through all the children of position or all the possible positions attainable from each possible move.
                int eval = minimax(child, depth - 1, false);
                maxEval = max(maxEval, eval);
            }
            return maxEval;
        }

        else{
            int minEval = Integer.MAX_VALUE;
            for (Board child : positions) { //Loops through all the children of position or all the possible positions attainable from each possible move.
                int eval = minimax(child, depth - 1, true);
                minEval = max(minEval, eval);
            }
            return minEval;
        }
    }



    //Intial call will pass negative infinity as alpha and positive infinity as beta
//    public int minimaxAB(Board position, int depth, int alpha, int beta boolean maximizingPlayer){
//        if (depth == 0 || position.gameOver()){
//            return position; //Return static evaluation of position
//        }
//        if (maximizingPlayer){
//            int maxEval = Integer.MIN_VALUE;
//            for (Board child : positions) { //Loops through all the children of position or all the possible positions attainable from each possible move.
//                int eval = minimaxAB(child, depth - 1, alpha, beta, false);
//                maxEval = max(maxEval, eval);
//                alpha = max(alpha, eval);
//                if (beta <= alpha){
//                    break;
//                }
//            }
//            return maxEval;
//        }
//        else{
//            int minEval = Integer.MAX_VALUE;
//            for (Board child : positions) { //Loops through all the children of position or all the possible positions attainable from each possible move.
//                int eval = minimaxAB(child, depth - 1,alpha, beta, true);
//                minEval = max(minEval, eval);
//                beta = max(beta, eval);
//                if (beta <= alpha){
//                    break;
//                }
//            }
//            return minEval;
//        }
//    }
}
