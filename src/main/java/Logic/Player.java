package Logic;

import java.util.ArrayList;

/*
Represents a player in the game
( manages such attributes as the player's pieces pn the board and whether it's their turn to move)
 */
public class Player {
    private String name;
    String color;
    Board board;
    int points;
    private ArrayList<String> capturedPieces = new ArrayList<>();

    public Player(String name, String color, Board board) {
        this.name = name;
        this.color = color;
        this.board = board;
    }

    public int getPoints() {
        return points;
    }
    public ArrayList<String> getCapturedPieces() {
        return this.capturedPieces;
    }
    public void CapturePiece(Piece piece) {
        if(this.color == "black")
            this.capturedPieces.add(piece.getClass().getSimpleName() + "-" + "white");
        else{
            this.capturedPieces.add(piece.getClass().getSimpleName() + "-" + "black");
        }
    }
}
