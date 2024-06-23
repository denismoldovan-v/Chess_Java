package Logic.Characters;

import Logic.Board;
import Logic.Piece;
import Logic.Player;
import Logic.Position;

public class Rook extends Piece {
    public Player player;
    public String color;
    public Board board;
    public Rook(Player player, String color, Position position, Board board) {
        super(player, color, position, board);
        this.player = player;
        this.color = color;
        this.board = board;
    }
    protected boolean isValidMove(Position newPosition) {
        if(Math.abs(newPosition.x - this.position.x) == 0 || Math.abs(newPosition.y - this.position.y) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean omitEnemy(Position start, Position newPos) {
        if(Math.abs(start.x-newPos.x)==0) {
            // y - const
            for(int i = start.y; i<newPos.y; i++) {
                if(isPieceThere(new Position(start.y, i))) {
                    System.out.println("Piece is in the scope u have to change your position selection!");
                    this.board.playersTurn(this.player);
                    return false;
                }
            }
        } else if(Math.abs(start.y-newPos.y)==0) {
            // x - const
            for(int i = start.x; i<newPos.x; i++) {
                if(isPieceThere(new Position(i, start.x))) {
                    System.out.println("Piece is in the scope u have to change your position selection!");
                    this.board.playersTurn(this.player);
                    return false;
                }
            }
        }
        return true;
    }
}
