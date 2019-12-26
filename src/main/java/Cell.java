class Cell {
    private Status status;
    int number;
    static int count = 0;

    Cell() {
        number = 0;
        this.status = Status.EMPTY;
    }

    void setStatus(Status status) {
        this.status = status;
    }

    Status getStatus() {
        return this.status;
    }

}

