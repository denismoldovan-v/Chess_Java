package Logic.Characters;

import Logic.Board;
import Logic.Piece;
import Logic.Player;
import Logic.Position;


public class King extends Piece{
        public Player player;
        public String color;
        public Board board;
        public King(Player player, String color, Position position, Board board) {
            super(player, color, position, board);
            this.player = player;
            this.color = color;
            this.board = board;
        }
        protected boolean isValidMove(Position newPosition) {
            if((Math.abs(this.position.y - newPosition.y) + Math.abs(this.position.x - newPosition.x) == 1) ||
                    (Math.abs(this.position.y - newPosition.y) + Math.abs(this.position.x - newPosition.x) == 2)) {
                return true;
            }
            return false;
        }

    @Override
    public boolean omitEnemy(Position start, Position newPos) {
        return true;
    }
}
