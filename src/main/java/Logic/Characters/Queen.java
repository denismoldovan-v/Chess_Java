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

    @Override
    public boolean omitEnemy(Position start, Position newPos) {
        if(Math.abs(start.x-newPos.x)==0) {
            // x - const
            for(int i = start.y+1; i<newPos.y; i++) {
                if(checkForPiece(new Position(i, start.x))) {
                    System.out.println("Piece is in the scope u have to change your position selection!");
                    this.board.playersTurn(this.player);
                    return false;
                }
            }
        } else if(Math.abs(start.x-newPos.x)==0) {
            // x - const
            for(int i = start.y+1; i<newPos.y; i++) {
                if(isPieceThere(new Position(i, start.x))) {
                    System.out.println("Piece is in the scope u have to change your position selection!");
                    this.board.playersTurn(this.player);
                    return false;
                }
            }
        } else {
            for(int i = 0; i<(Math.abs(newPos.x-start.x)); i++) {
                if(isPieceThere(new Position((start.y+i),(start.x+i)))){
                    System.out.println("Piece is in the scope u have to change your position selection!");
                    this.board.playersTurn(this.player);
                    return false;
                }
            }
        }
        return true;
    }
}
