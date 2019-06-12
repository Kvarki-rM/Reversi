class Cell {
    private Status status;

    Cell() {
        this.status = Status.EMPTY;
    }

    void setStatus(Status status) {
        this.status = status;
    }

    Status getStatus() {
        return this.status;
    }
}

