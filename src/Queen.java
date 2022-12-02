import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Queen extends Piece{
    /**
     * default constructor
     * @param color player
     * @param x initial x position
     * @param y initial y position
     * @param b board that the pawn will be on
     */
    public Queen(boolean color, int x, int y, int value, Board b) {
        super(color, x, y, value, b);
        b.occupy(this,x,y);
    }

    /**
     * Takes a possible move and checks if it is valid
     * @param newX is the x coordinate of a possible move
     * @param newY is the y coordinate of a possible move
     * @return true if a specific move is possible
     * */
    public boolean isPossible(int newX, int newY) {
        int dx = newX - x;
        int dy = newY - y;
        Board b = currBoard;

        // ------------ START OF ROOK FUNCTION ---------------
        if((dx == 0) ^ (dy == 0)) {
            if (dx == 0) {
                if (dy > 0) {
                    boolean isPossible = true;
                    for (int d = 1; d <= dy; d++) {
                        Piece p = b.getPiece(newX, y + d);
                        if (p == null) {
                            continue;
                        }

                        if (white) {
                            if (p.white) {
                                isPossible = false;
                            } else if (!(p.white) && d != dy) {
                                isPossible = false;
                            }
                        } else {
                            if (!(p.white)) {
                                isPossible = false;
                            } else if (p.white && d != dy) {
                                isPossible = false;
                            }
                        }


                    }
                    return isPossible;
                } else {
                    boolean isPossible = true;
                    for (int d = -1; d >= dy; d--) {
                        Piece p = b.getPiece(newX, y + d);
                        if (p == null) {
                            continue;
                        }

                        if (white) {
                            if (p.white) {
                                isPossible = false;
                            } else if (!(p.white) && d != dy) {
                                isPossible = false;
                            }
                        } else {
                            if (!(p.white)) {
                                isPossible = false;
                            } else if (p.white && d != dy) {
                                isPossible = false;
                            }
                        }
                    }
                    return isPossible;
                }

            }
            else   // dy == 0
            {
                if(dx > 0)
                {
                    boolean isPossible = true;
                    for(int d = 1; d <= dx; d++)
                    {
                        Piece p = b.getPiece(x + d, newY);
                        if (p == null) {
                            continue;
                        }

                        if (white) {
                            if (p.white) {
                                isPossible = false;
                            } else if (!(p.white) && d != dx) {
                                isPossible = false;
                            }
                        } else {
                            if (!(p.white)) {
                                isPossible = false;
                            } else if (p.white && d != dx) {
                                isPossible = false;
                            }
                        }
                    }
                    return isPossible;
                }
                else
                {
                    boolean isPossible = true;
                    for(int d = -1; d >= dx; d--)
                    {
                        Piece p = b.getPiece(x + d, newY);
                        if (p == null) {
                            continue;
                        }

                        if (white) {
                            if (p.white) {
                                isPossible = false;
                            } else if (!(p.white) && d != dx) {
                                isPossible = false;
                            }
                        } else {
                            if (!(p.white)) {
                                isPossible = false;
                            } else if (p.white && d != dx) {
                                isPossible = false;
                            }
                        }
                    }
                    return isPossible;
                }
            }
        }
        // ------------ END OF ROOK FUNCTION ---------------


        // ------------ START OF BISHOP FUNCTION ---------------
        else if(abs(dx) == abs(dy) && abs(dx) != 0)
        {
            if(dy!= 0 && dx/dy == 1)
            {
                if(dx > 0)
                {
                    boolean isPossible = true;
                    for (int d = 1; d <= dx; d++) {
                        Piece p = b.getPiece(x+d, y+d);
                        if (p == null) {
                            continue;
                        }

                        else if (white) {
                            if (p.white) {
                                isPossible = false;
                            } else if (!(p.white) && d != dx) {
                                isPossible = false;
                            }
                        }
                        else {
                            if (!(p.white)) {
                                isPossible = false;
                            } else if (p.white && d != dx) {
                                isPossible = false;
                            }
                        }
                    }
                    return isPossible;

                }
                else
                {
                    boolean isPossible = true;
                    for (int d = -1; d >= dx; d--) {
                        Piece p = b.getPiece(x + d, y + d);
                        if (p == null) {
                            continue;
                        }

                        if (white) {
                            if (p.white) {
                                isPossible = false;
                            } else if (!(p.white) && d != dx) {
                                isPossible = false;
                            }
                        } else {
                            if (!(p.white)) {
                                isPossible = false;
                            } else if (p.white && d != dx) {
                                isPossible = false;
                            }
                        }
                    }
                    return isPossible;
                }
            }

            else
            {
                if(dx > 0)
                {
                    boolean isPossible = true;
                    for (int d = 1; d <= dx; d++) {
                        Piece p = b.getPiece(x + d, y - d);
                        if (p == null) {
                            continue;
                        }

                        if (white) {
                            if (p.white) {
                                isPossible = false;
                            } else if (!(p.white) && d != dx) {
                                isPossible = false;
                            }
                        } else {
                            if (!(p.white)) {
                                isPossible = false;
                            } else if (p.white && d != dx) {
                                isPossible = false;
                            }
                        }
                    }
                    return isPossible;
                }
                else
                {
                    boolean isPossible = true;
                    for (int d = -1; d >= dx; d--) {
                        Piece p = b.getPiece(x + d, y - d);
                        if (p == null) {
                            continue;
                        }

                        if (white) {
                            if (p.white) {
                                isPossible = false;
                            } else if (!(p.white) && d != dx) {
                                isPossible = false;
                            }
                        } else {
                            if (!(p.white)) {
                                isPossible = false;
                            } else if (p.white && d != dx) {
                                isPossible = false;
                            }
                        }
                    }
                    return isPossible;
                }
            }
        }
        return false;
    }


//    public boolean isPossible(int x, int y) {
//        boolean possible = false;
//        if (sameSpot(x,y) || sameColorMove(x,y) || moveOutsideBoard(x,y)){
//            return false;
//        }
//        else if(currBoard.getPiece(x,y) == null || currBoard.getPiece(x,y).white == this.white){
//            return true;
//        }
//        return possible;
//    }
}
