public enum Tile {
    E("E"),
    B("B"),
    W("W"),
    B_L("B_L"),
    W_L("W_L"),
    ICON("ICON");

    private String title;

    Tile(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title.toLowerCase();
    }

}