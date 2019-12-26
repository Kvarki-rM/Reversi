package game;

import org.jetbrains.annotations.Contract;

public class Coordinate {
    int x;
    int y;

    @Contract(pure = true)
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
