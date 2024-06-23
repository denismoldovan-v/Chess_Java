package Logic;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hi");
        Board board = new Board();
        Player player1 = new Player("whitePlayer", "white", board);
        Player player2 = new Player("blackPlayer", "black", board);
        board.addPlayers(player1);
        board.addPlayers(player2);
        board.startGame();
    }
}
