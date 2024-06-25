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
        if(Math.abs((this.position.y - newPosition.y)) == Math.abs((this.position.x-newPosition.x))) {
            return true;
        }
        return false;
    }

    @Override
    public boolean omitEnemy(Position start, Position newPos) {
        //y-, x-
        if((newPos.y - start.y)<0 && (newPos.x-start.x)<0) {
            for(int i = 1; i<Math.abs(newPos.y - start.y)-1; i++) {
                if(board.GRID[start.y][i] == this) {
                    continue;
                }
                if(checkForPiece(new Position(start.y-i,start.x-i))) {
                    System.out.println("Invalid Move, the piece is in the scope!");
                    this.board.playersTurn(this.player);
                    return false;
                }
            }
            //y-, x+
        } else if((newPos.y - start.y)<0 && (newPos.x-start.x)>0) {
            for(int i = 1; i<Math.abs(newPos.y - start.y)-1; i++) {
                if(board.GRID[start.y][i] == this) {
                    continue;
                }
                if(checkForPiece(new Position(start.y-i,start.x+i))) {
                    System.out.println("Invalid Move, the piece is in the scope!");
                    this.board.playersTurn(this.player);
                    return false;
                }
            }
            //y+, x-
        } else if((newPos.y - start.y)>0 && (newPos.x-start.x)<0) {
            for(int i = 1; i<Math.abs(newPos.y - start.y)-1; i++) {
                if(board.GRID[start.y][i] == this) {
                    continue;
                }
                if(checkForPiece(new Position(start.y+i,start.x-i))) {
                    System.out.println("Invalid Move, the piece is in the scope!");
                    this.board.playersTurn(this.player);
                    return false;
                }
            }
        } //y+,x+
        else {
            for(int i = 1; i<Math.abs(newPos.y - start.y)-1; i++) {
                if(board.GRID[start.y][i] == this) {
                    continue;
                }
                if(checkForPiece(new Position(start.y+i,start.x+i))) {
                    System.out.println("Invalid Move, the piece is in the scope!");
                    this.board.playersTurn(this.player);
                    return false;
                }
            }
        }
        return true;
    }

}
