package Logic.Characters;

import Logic.Board;
import Logic.Piece;
import Logic.Player;
import Logic.Position;

public class Queen extends Piece {
    public Player player;
    public String color;
    public Board board;
    public Queen(Player player, String color, Position position, Board board) {
        super(player, color, position, board);
        this.player = player;
        this.color = color;
        this.board = board;
    }
    protected boolean isValidMove(Position newPosition) {
        return true;
    }

//    @Override
    public boolean omitEnemy(Position start, Position newPos) {
        return true;
    }
//        //1. x = const:
//        if(start.x - newPos.x == 0) {
//            if(this.color == "white") {
//
//            } else {
//
//            }
//        }
//    }
}
