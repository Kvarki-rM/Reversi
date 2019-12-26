package game;

import org.jetbrains.annotations.Contract;

public enum Tile {
    E("resources\\e.png"),
    B("resources\\b.png"),
    W("resources\\w.png"),
    B_L("resources\\b_l.png"),
    W_L("resources\\w_l.png"),
    B_ICON("resources\\b_icon.png"),
    W_ICON("resources\\w_icon.png"),
    DOP("resources\\dop.png"),
    BACK("resources\\back.png"),
    BLACK("resources\\black.png");

    private String name;

    @Contract(pure = true)
    Tile(String title) {
        this.name = title;
    }

    @Contract(pure = true)
    public String getTitle() {
        return name;
    }

}