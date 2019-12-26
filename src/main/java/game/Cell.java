package game;

public class Cell {
    private Status status;

    Cell() {
        this.status = Status.EMPTY;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

}

