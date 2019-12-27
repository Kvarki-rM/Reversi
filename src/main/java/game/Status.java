package game;

import board.Tile;
import org.jetbrains.annotations.Contract;

public enum Status {
    EMPTY("Empty"),
    BLACK("Black"),
    WHITE("White"),
    BLACK_L("Black_L"),
    WHITE_L("White_L");

    private String name;

    @Contract(pure = true)
    Status(String title) {
        this.name = title;
    }

    public Tile getTile() {
        if (name.equals("Black")) return Tile.B;
        if (name.equals("White")) return Tile.W;
        if (name.equals("Black_L")) return Tile.B_L;
        if (name.equals("White_L")) return Tile.W_L;
        return Tile.E;
    }
}
