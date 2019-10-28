import org.jetbrains.annotations.Contract;

public enum Tile {
    E("e.png"),
    B("b.png"),
    W("w.png"),
    B_L("b_l.png"),
    W_L("w_l.png"),
    B_ICON("b_icon.png"),
    W_ICON("w_icon.png"),
    DOP("dop.png"),
    BACK("back.png"),
    BLACK("black.png");

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