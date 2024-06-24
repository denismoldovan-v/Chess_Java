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
        //x = const
        if(start.x == newPos.x) {
            for(int i = Math.min(start.y, newPos.y); i<Math.max(start.y, newPos.y)-1; i++) {
                if(board.GRID[start.y][i] == this) {
                    continue;
                }
                if(checkForPiece(new Position(i,start.x))) {
                    System.out.println("Invalid Move, the piece is in the scope!");
                    this.board.playersTurn(this.player);
                    return false;
                }
            }
            //y = const
        } else if(start.y == newPos.y) {
            for(int i = Math.min(start.x, newPos.x); i<Math.max(start.x, newPos.x)-1; i++) {
                if(board.GRID[start.y][i] == this) {
                    continue;
                }
                if(checkForPiece(new Position(i,start.x))) {
                    System.out.println("Invalid Move, the piece is in the scope!");
                    this.board.playersTurn(this.player);
                    return false;
                }
            }
        }
        return true;
    }
}
