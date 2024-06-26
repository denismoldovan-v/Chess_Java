package Logic.Characters;

import Logic.Board;
import Logic.Piece;
import Logic.Player;
import Logic.Position;

public class Pawn extends Piece {
    public Player player;
    public String color;
    public Board board;
    public Pawn(Player player, String color, Position position, Board board) {
        super(player, color, position, board);
        this.player = player;
        this.color = color;
        this.board = board;
    }

    protected boolean isValidMove(Position newPosition) {
        System.out.println(this.position);
        boolean res = false;
        if(((Math.abs(newPosition.y-this.position.y) == 1 && newPosition.x - this.position.x == 0) || (this.steps == 0 && (Math.abs(newPosition.y- this.position.y) == 2 && (newPosition.x - this.position.x == 0)))) && isEnemyThere(newPosition) == false) {
            System.out.println("Valid Move no enemy");
            res = true;
            return res;
        } else if((Math.abs(newPosition.y - this.position.y) == 1) && (Math.abs(newPosition.x - this.position.x) == 1)) {
            System.out.println("Valid move - enemy is there going to method isEnemyThere()");
            res = isEnemyThere(newPosition);
            return res;
        } else {
            System.out.println(this.position);
            System.out.println("Invalid Move");
        }
        return res;
    }

    @Override
    public boolean omitEnemy(Position start, Position newPos) {
        //x = const
        //y+
        if((newPos.y - start.y)>0) {
            if(checkForPiece(new Position(start.y+1, start.x))) {
                System.out.println("Invalid Move, the piece is in the scope!");
                this.board.playersTurn(this.player);
                return false;
            }
        } //y-
        else {
            if(checkForPiece(new Position(start.y-1, start.x))) {
                System.out.println("Invalid Move, the piece is in the scope!");
                this.board.playersTurn(this.player);
                return false;
            }
        }
        return true;
    }


}
