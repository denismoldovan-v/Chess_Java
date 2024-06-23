package Logic;

import Logic.Characters.King;

import java.util.ArrayList;
import java.util.Scanner;

abstract public class Piece {
    public int steps;
     public String color;
     public Position position;
     public Board board;
     public ArrayList<Position> possibleAttackPositions = new ArrayList<>();
     public Player player;
     public Piece(Player player, String color, Position position, Board board){
         this.player = player;
         this.color = color;
         this.position = position;
         this.board = board;
     }
    public boolean move(Position newPosition) {
        if(isValidPosition(newPosition) && validateMove(newPosition)) {
            if(omitEnemy(this.position, newPosition)) {
                Position pos = this.position;
                this.position = newPosition;

                board.GRID[pos.y][pos.x] = null;
                board.GRID[newPosition.y][newPosition.x] = this;
//            isCheck();
                steps++;
                System.out.println("moved");
                System.out.println(this.position);
                return true;
            }
        }
        return false;
    }

    public boolean validateMove(Position newPosition) {
        if(isValidMove(newPosition)) {
            System.out.println("Valid Move");
            if(isPieceThere(newPosition) || isEmptyPlace(newPosition)) {
                System.out.println("ValidateMove satisfied!");
                return true;
            }
        }
        return false;
    }
    protected abstract boolean isValidMove(Position newPosition);
    protected boolean isPieceThere(Position newPosition) {
        if(this.board.GRID[newPosition.y][newPosition.x] != null) {

            Piece holdingPiece = this.board.GRID[newPosition.y][newPosition.x];
            if(holdingPiece.color == this.color) {
                System.out.println("Place is taken by your piece");
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose new position y,x:");
                String changedPosition = sc.nextLine();
                String[] changedPositionVals = changedPosition.split(",");

                int changedY = Integer.valueOf(changedPositionVals[0]);
                int changedX = Integer.valueOf(changedPositionVals[1]);

                Position changed = new Position(changedY, changedX);
                this.move(changed);

            } else if(holdingPiece.color != this.color) {
                removePiece(holdingPiece);
            }
        }
        return false;
    }
    protected boolean isEmptyPlace(Position position) {
        if(this.board.GRID[position.y][position.x] == null) {
            return true;
        }
        return false;
    }
    protected boolean isCheck() {
        if(isKingInScope()) {
            System.out.println("Check");
        }
        return false;
    }
    protected void removePiece(Piece piece) {
        outer:
        for(int i = 0; i<this.board.GRID.length; i++) {
            for(int j = 0; j<this.board.GRID[i].length; j++) {
                if(this.board.GRID[i][j]!=null) {
                    if(this.board.GRID[i][j].equals(piece)) {
                        this.board.GRID[this.position.y][this.position.x] = null;
                        this.position = piece.position;
                        this.board.GRID[i][j] = this;
                        System.out.println(this.board.GRID[i][j]);
                        piece.position = null;
                        System.out.println(this.position);
                        this.player.points++;
                        System.out.println(this.player.points);
                        break outer;
                    }
                }
            }
        }
    }
    protected boolean isValidPosition(Position position) {
        if((0<=position.y && position.y<=7) && (0<=position.x && position.x<=7)) {
            return true;
        }
        System.out.println("Incorrect position was chosen!");
        board.playersTurn(this.player);
        return false;
    }
    protected boolean isKingInScope() {
        getPossibleAttackPositions();
        for(Position pos : this.possibleAttackPositions) {
            if(this.board.GRID[pos.y][pos.x] != null) {
                Piece piece = this.board.GRID[pos.x][pos.y];
                if(piece instanceof King && piece.color != this.color) {
                    System.out.println("Check");
                }
            }
        }
        return false;
    }

    protected boolean isEnemyThere(Position position) {
        if (this.board.GRID[position.y][position.x] != null) {
            System.out.println("not null");
            Piece figure = this.board.GRID[position.y][position.x];
            if (!figure.color.equals(this.color)) {
                System.out.println("true");
                return true;
            }
        }
        return false;
    }
    protected void getPossibleAttackPositions() {
        if(!possibleAttackPositions.isEmpty()) {
            possibleAttackPositions.clear();
        }
        for(int i = 0; i<this.board.GRID.length; i++) {
            for(int j = 0; j<this.board.GRID[i].length; j++) {
                Position pos = new Position(i,j);
                if(isValidMove(pos)) {
                    possibleAttackPositions.add(pos);
                }
            }
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + color;
    }
    public abstract boolean omitEnemy(Position start, Position newPos);
}
