package board;


import game.Status;
import org.jetbrains.annotations.Contract;

public class Cell {
    private Status status;

    @Contract(pure = true)
    Cell() {
        this.status = Status.EMPTY;
    }

    void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

}

