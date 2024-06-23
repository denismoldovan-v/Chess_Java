package Logic.Characters;

import Logic.Board;
import Logic.Piece;
import Logic.Player;
import Logic.Position;

public class Bishop extends Piece {
    public Player player;
    public String color;
    public Board board;
    public Bishop(Player player, String color, Position position, Board board) {
        super(player, color, position, board);
        this.player = player;
        this.color = color;
        this.board = board;
    }

    protected boolean isValidMove(Position newPosition) {
        if(Math.abs((this.position.y - newPosition.y)) == Math.abs((this.position.x-this.position.x))) {
            return true;
        }
        return false;
    }

    @Override
    public boolean omitEnemy(Position start, Position newPos) {
        for(int i = 0; i<(Math.abs(newPos.x-start.x)); i++) {
            if(isPieceThere(new Position((start.y+i),(start.x+i)))){
                System.out.println("Piece is in the scope u have to change your position selection!");
                this.board.playersTurn(this.player);
                return false;
            }
        }
        return true;
    }

}
