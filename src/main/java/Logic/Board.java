package Logic;

import Logic.Characters.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
Represents chessboard itself
 */
public class Board {
    int totalSteps;
    ArrayList<Player> players = new ArrayList<>();
    public final Piece[][] GRID = new Piece[8][8];
    public void setup() {
        GRID[0] = new Piece[] {new Rook(getWhitePlayer(), "white", new Position(0,0), this), new Knight(getWhitePlayer(), "white",new Position(0,1), this), new Bishop(getWhitePlayer(), "white",new Position(0,2), this), new Queen(getWhitePlayer(), "white",new Position(0,3), this), new King(getWhitePlayer(), "white",new Position(0,4), this), new Bishop(getWhitePlayer(), "white",new Position(0,5), this), new Knight(getWhitePlayer(), "white",new Position(0,6), this), new Rook(getWhitePlayer(), "white",new Position(0,7), this)};
        GRID[1] = new Piece[]{new Pawn(getWhitePlayer(), "white", new Position(1,0), this), new Pawn(getWhitePlayer(), "white", new Position(1,1), this), new Pawn(getWhitePlayer(), "white", new Position(1,2), this), new Pawn(getWhitePlayer(), "white", new Position(1,3), this), new Pawn(getWhitePlayer(), "white", new Position(1,4), this), new Pawn(getWhitePlayer(), "white", new Position(1,5),  this), new Pawn(getWhitePlayer(), "white", new Position(1,6),  this), new Pawn(getWhitePlayer(), "white", new Position(1,7), this)};
        GRID[6] = new Piece[]{new Pawn(getBlackPlayer(), "black", new Position(6,0), this), new Pawn(getBlackPlayer(), "black", new Position(6,1), this), new Pawn(getBlackPlayer(), "black", new Position(6,2), this), new Pawn(getBlackPlayer(), "black", new Position(6,3), this), new Pawn(getBlackPlayer(), "black", new Position(6,4), this), new Pawn(getBlackPlayer(), "black", new Position(6,5),  this), new Pawn(getBlackPlayer(), "black", new Position(6,6),  this), new Pawn(getBlackPlayer(), "black", new Position(6,7), this)};
        GRID[7] = new Piece[] {new Rook(getBlackPlayer(), "black", new Position(7,0), this), new Knight(getBlackPlayer(), "black", new Position(7,1), this), new Bishop(getBlackPlayer(), "black", new Position(7,2), this), new Queen(getBlackPlayer(), "black", new Position(7,3), this), new King(getBlackPlayer(), "black", new Position(7,4), this), new Bishop(getBlackPlayer(), "black", new Position(7,5),  this), new Knight(getBlackPlayer(), "black", new Position(7,6),  this), new Rook(getBlackPlayer(), "black", new Position(7,7), this)};
    }
    public void addPlayers(Player player) {
        if(players.size()<2){
            players.add(player);
        } else {
            System.out.println("There are two players already!");
        }
    }
    private void boardRepresentation() {
        for(int i = 0; i<this.GRID.length; i++) {
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println();
            for(int j = 0; j<this.GRID[i].length; j++) {
                if(this.GRID[i][j]!=null) {
                    String out = String.format("%25.25s", this.GRID[i][j] + String.valueOf(this.GRID[i][j].position));
                    System.out.print('|' + out);
                } else {
                    String out = String.format("%25.25s", this.GRID[i][j] + "(" + i + ", " + j + ")");
                    System.out.print('|' + out);
                }
            }
        }
        System.out.println();
        System.out.println();
    }

    public Player whoseTurn() {
        if(totalSteps == 0) {
            for(Player player : players) {
                if (player.color == "white"){
                    totalSteps++;
                    return player;
                }
            }
        } else if(totalSteps % 2 == 1) {
            for(Player player : players) {
                if (player.color == "black"){
                    totalSteps++;
                    return player;
                }
            }
        } else {
            for(Player player : players) {
                if (player.color == "white"){
                    totalSteps++;
                    return player;
                }
            }
        }
        totalSteps++;
        return null;
    }
    public void playersTurn(Player player) {

        boardRepresentation();
        System.out.println(player.color+ " Player's turn: ");
        Scanner scannerStart = new Scanner(System.in);
        System.out.println("Choose (y,x) from where to go");
        String values = scannerStart.nextLine();
        String[] vals = values.split(",");
        int yStart = Integer.valueOf(vals[0]);
        int xStart = Integer.valueOf(vals[1]);
        Position fromWhere = new Position(yStart, xStart);
        Piece operatingPiece = findPiece(fromWhere);

        System.out.println("Choose (y,x) where to go");
        Scanner scanner = new Scanner(System.in);
        String valuesToGo = scanner.nextLine();
        String[] valsToGo = valuesToGo.split(",");
        int y = Integer.valueOf(valsToGo[0]);
        int x = Integer.valueOf(valsToGo[1]);

        Position toGo = new Position(y, x);
        if(player.color == operatingPiece.color) {
            operatingPiece.move(toGo);
            if(pieceCounter()) {
                playersTurn(whoseTurn());
            } else {
                finishGame();
            }
        } else {
            System.out.println("Not piece of your color!");
            playersTurn(player);
        }
    }
    private Piece findPiece(Position position) {
        for(int i = 0; i< GRID.length; i++) {
            for(int j = 0; j < GRID.length; j++) {
                if(i == position.y && j == position.x && GRID[i][j] != null) {
                    return GRID[i][j];
                }
            }
        }
        return null;
    }
    private boolean pieceCounter() {
        int whiteCounter = 0;
        int blackCounter = 0;
        for(int i=0; i< GRID.length; i++) {
            for(int j = 0; j<GRID[i].length; j++) {
                if(GRID[i][j]!=null) {
                    switch (GRID[i][j].color){
                        case "black":
                            blackCounter++;
                            break;
                        case "white":
                            whiteCounter++;
                            break;
                    }
                }
            }
        }
        return (whiteCounter!=0 && blackCounter!=0);
    }
    public void startGame() {
        reset();
        setup();
        playersTurn(whoseTurn());
    }
    private void finishGame() {
        System.out.println("Game is finished!");
    }
    private void reset() {
        for(Player player : players) {
            player.points = 0;
        }
        totalSteps = 0;
    }
    private Player getWhitePlayer() {
        for(Player player : players) {
            if(player.color == "white") {
                return player;
            }
        }
        return null;
    }

    private Player getBlackPlayer() {
        for(Player player : players) {
            if(player.color == "black") {
                return player;
            }
        }
        return null;
    }
}
