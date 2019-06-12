public enum Status {

    EMPTY("Empty"),
    BLACK("Black"),
    WHITE("White"),
    BLACK_L("Black_L"),
    WHITE_L("White_L");

    private String title;

    Status(String title) {
        this.title = title;
    }

    public Tile getTile() {
        if (title.equals("Black")) return Tile.B;
        if (title.equals("White")) return Tile.W;
        if (title.equals("Black_L")) return Tile.B_L;
        if (title.equals("White_L")) return Tile.W_L;
        return Tile.E;
    }
}
