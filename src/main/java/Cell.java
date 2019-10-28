import org.jetbrains.annotations.Contract;

class Cell {
    private Status status;

    @Contract(pure = true)
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

