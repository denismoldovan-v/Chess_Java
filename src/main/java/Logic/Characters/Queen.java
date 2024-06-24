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
            // y != const, x != const
        } else  {
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
        }
        return true;
    }
}
