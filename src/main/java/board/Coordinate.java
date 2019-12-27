package board;

import org.jetbrains.annotations.Contract;

public class Coordinate {
    public int x;
    public int y;

    @Contract(pure = true)
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
