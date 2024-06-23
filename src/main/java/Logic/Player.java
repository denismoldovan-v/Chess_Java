package Logic;
/*
Represents a player in the game
( manages such attributes as the player's pieces pn the board and whether it's their turn to move)
 */
public class Player {
    String name;
    String color;
    Board board;
    int points;

    public Player(String name, String color, Board board) {
        this.name = name;
        this.color = color;
        this.board = board;
    }

    public int getPoints() {
        return points;
    }
}
