import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    /**
     * default constructor
     *
     * @param color player
     * @param x     initial x position
     * @param y     initial y position
     * @param b     board that the pawn will be on
     */
    public Pawn(boolean color, int x, int y, int value, Board b) {
        super(color, x, y, value, b);
        b.occupy(this, x, y);
    }

    /**
     * Copy Constructor
     * */
    public Pawn(Piece p) {
        super(p);
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
        if(white) // white is at the bottom
        {
            if(firstMove)
            {
                if (dx == 0)
                {
                    if(dy == -1)
                    {
                        if (b.getPiece(newX, newY) == null)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else if(dy == -2)
                    {
                        if (b.getPiece(newX, newY) == null && b.getPiece(newX, newY + 1) == null)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                if(dy == -1)
                {
                    if(dx == 0)
                    {
                        if(b.getPiece(newX, newY) == null)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else if (dx == -1 || dx == 1)
                    {
                        if(b.getPiece(newX, newY) != null)
                        {
                            if(!(b.getPiece(newX, newY).white))
                            {
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }

                }
                else
                {
                    return false;
                }
            }
        }


        else // player black
        {
           if(firstMove)
            {
                if (dx == 0)
                {
                    if(dy == 1)
                    {
                        if (b.getPiece(newX, newY) == null)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else if (dy == 2)
                    {
                        if (b.getPiece(newX, newY) == null && b.getPiece(newX, newY - 1) == null)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {
                if(dy == 1)
                {
                    if(dx == 0)
                    {
                        if(b.getPiece(newX, newY) == null)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else if (dx == 1 || dx == -1)
                    {
                        if(b.getPiece(newX, newY) != null)
                        {
                            if(b.getPiece(newX, newY).white)
                            {
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                }
                else
                {
                    return false;
                }
            }
        }
        return false;
    }
}