import org.jetbrains.annotations.Contract;

class Coordinate {
    int x;
    int y;

    @Contract(pure = true)
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
