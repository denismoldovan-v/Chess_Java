package Logic;

import java.util.ArrayList;
import java.util.Arrays;

public class checker {
    public static void main(String[] args) {
        Position[][] checker = new Position[3][3];
        checker[0] = new Position[] {new Position(1, 1), new Position(1, 1), new Position(1, 1)};
        checker[2] = new Position[] {new Position(1, 1), new Position(1, 1), new Position(1, 1)};
        System.out.println(checker[1][0]);
    }
}
