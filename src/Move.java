import java.util.ArrayList;
import java.util.List;

public class Move {

    private String currLocation;
    private String newLocation;
    private Piece currPiece;
    private int value;


    public Move(String newLocation, Piece currPiece){
        currLocation  = currPiece.translateNum( new ArrayList<Integer>(List.of(currPiece.x, currPiece.y)));
        this.newLocation = newLocation;
        this.currPiece = currPiece;
        value = 0;
    }

    public String getCurrLocation() {
        return currLocation;
    }

    public void setCurrLocation(String currLocation) {
        this.currLocation = currLocation;
    }

    public String getNewLocation() {
        return newLocation;
    }

    public void setNewLocation(String newLocation) {
        this.newLocation = newLocation;
    }

    public Piece getCurrPiece() {
        return currPiece;
    }

    public void setCurrPiece(Piece currPiece) {
        this.currPiece = currPiece;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString(){
        String color = "";
        if (currPiece.white){
            color = "White";
        }
        else{
            color = "Black";
        }
        return color + " " + currPiece.getClass() + "  " + currLocation + " -> " + newLocation; //TODO get the name of the piece
    }
}
