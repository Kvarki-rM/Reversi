class Board {
    Cell[][] board;
    Status turn = Status.BLACK;
    Status helper = Status.BLACK_L;
    Status pastTurn = Status.WHITE;

    Board() {
        this.board = new Cell[8][8];
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                this.board[x][y] = new Cell();
        this.board[3][3].setStatus(Status.WHITE);
        this.board[3][4].setStatus(Status.BLACK);
        this.board[4][3].setStatus(Status.BLACK);
        this.board[4][4].setStatus(Status.WHITE);
    }

    void switchTurn() {
        this.pastTurn = this.turn;
        if (this.turn == Status.WHITE) {
            this.turn = Status.BLACK;
            this.helper = Status.BLACK_L;
        } else {
            this.turn = Status.WHITE;
            this.helper = Status.WHITE_L;
        }
    }
}


