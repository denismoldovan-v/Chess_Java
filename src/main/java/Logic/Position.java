package Logic;

public class Position {
    public int y;
    public int x;
    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString() {
        return "(" + this.y + ", " + this.x + ")";
    }
}
