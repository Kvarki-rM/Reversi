package board;

import game.Reversi;
import game.Status;

import java.util.Objects;

public class Board {
    public static Cell[][] board;
    private static Cell[][] lastBoard;
    public static Status turn = Status.BLACK;
    public static Status pastTurn = Status.WHITE;
    public static Status helper = Status.BLACK_L;
    private static int size = Reversi.size;
    private static int temp = 0;
    public static int black = 2;
    public static int white = 2;
    public static int manyTurns = 0;
    public static int numTurn = 1;

    public Board() {
        board = new Cell[size][size];
        lastBoard = new Cell[size][size];
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                lastBoard[x][y] = new Cell();
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                board[x][y] = new Cell();
        board[size / 2 - 1][size / 2 - 1].setStatus(Status.WHITE);
        board[size / 2 - 1][size / 2].setStatus(Status.BLACK);
        board[size / 2][size / 2 - 1].setStatus(Status.BLACK);
        board[size / 2][size / 2].setStatus(Status.WHITE);
        scanner();
    }


    public static void backTurn() {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                board[i][j].setStatus(lastBoard[i][j].getStatus());

    }

    public static void switchTurn() {

        manyTurns = 0;
        numTurn++;

        pastTurn = turn;
        if (turn == Status.WHITE) {//смена хода
            turn = Status.BLACK;
            helper = Status.BLACK_L;
        } else {
            turn = Status.WHITE;
            helper = Status.WHITE_L;
        }
        scanner();
        accountant();
        if (manyTurns == 0 && black + white != size * size && temp <= 1) {
            temp++;
            switchTurn();
        }
    }

    private static void accountant() {
        black = 0;
        white = 0;

        for (Cell[] cells : board)//подсчет статистики
            for (int j = 0; j < board[0].length; j++) {
                if (cells[j].getStatus() == Status.BLACK)
                    black++;
                if (cells[j].getStatus() == Status.WHITE)
                    white++;
            }
    }

    public static void add(int x, int y) {
        temp = 0;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                lastBoard[i][j].setStatus(board[i][j].getStatus());

        if (board[x][y].getStatus() != helper) throw new NullPointerException("Не совпадает вход и разрешение");
        board[x][y].setStatus(turn);
        cleaner();

        //вверх
        for (int i = 1; i <= x; i++)
            if (Objects.equals(board[x - i][y].getStatus(), turn)) {
                for (int j = i; j > 0; j--)
                    if (Objects.equals(board[x - j][y].getStatus(), pastTurn))
                        board[x - j][y].setStatus(turn);
                break;
            } else if (Objects.equals(board[x - i][y].getStatus(), Status.EMPTY)) {
                break;
            }

        //вправо
        for (int i = 1; i < size - y; i++) {
            if (Objects.equals(board[x][y + i].getStatus(), turn)) {
                for (int j = 0; j < i; j++)
                    if (Objects.equals(board[x][y + j].getStatus(), pastTurn))
                        board[x][y + j].setStatus(turn);

                break;
            } else if (Objects.equals(board[x][y + i].getStatus(), Status.EMPTY)) {
                break;
            }
        }

        //влево
        for (int i = 1; i <= y; i++) {
            if (Objects.equals(board[x][y - i].getStatus(), turn)) {
                for (int j = i; j > 0; j--) {
                    if (Objects.equals(board[x][y - i + j].getStatus(), pastTurn)) {
                        board[x][y - i + j].setStatus(turn);
                    }
                }
                break;
            } else if (Objects.equals(board[x][y - i].getStatus(), Status.EMPTY)) {
                break;
            }
        }

        //вниз
        for (int i = 1; i < size - x; i++)
            if (Objects.equals(board[x + i][y].getStatus(), turn)) {
                for (int j = i; j > 0; j--)
                    if (Objects.equals(board[x + i - j][y].getStatus(), pastTurn))
                        board[x + i - j][y].setStatus(turn);
                break;
            } else if (Objects.equals(board[x + i][y].getStatus(), Status.EMPTY)) {
                break;
            }

        //вниз-вправо
        for (int i = 1; i < Math.min(size - x, size - y); i++) {
            if (Objects.equals(board[x + i][y + i].getStatus(), turn)) {
                for (int j = i; j > 0; j--) {
                    if (Objects.equals(board[x + i - j][y + i - j].getStatus(), pastTurn)) {
                        board[x + i - j][y + i - j].setStatus(turn);
                    }
                }
                break;
            } else if (Objects.equals(board[x + i][y + i].getStatus(), Status.EMPTY)) {
                break;
            }
        }

        //вниз-влево
        for (int i = 1; i < Math.min(size - x, y + 1); i++) {
            if (Objects.equals(board[x + i][y - i].getStatus(), turn)) {
                for (int j = i; j > 0; j--) {
                    if (Objects.equals(board[x + j][y - j].getStatus(), pastTurn)) {
                        board[x + j][y - j].setStatus(turn);
                    }
                }
                break;
            } else if (Objects.equals(board[x + i][y - i].getStatus(), Status.EMPTY)) {
                break;
            }
        }

        //вверх-вправо
        for (int i = 1; i < Math.min(x + 1, size - y); i++)
            if (Objects.equals(board[x - i][y + i].getStatus(), turn)) {
                for (int j = i; j > 0; j--)
                    if (Objects.equals(board[x - j][y + j].getStatus(), pastTurn))
                        board[x - j][y + j].setStatus(turn);

                break;
            } else if (Objects.equals(board[x - i][y + i].getStatus(), Status.EMPTY)) {
                break;
            }

        //вверх-влево
        for (int i = 1; i <= Math.min(x, y); i++)
            if (Objects.equals(board[x - i][y - i].getStatus(), turn)) {
                for (int j = i; j > 0; j--)
                    if (Objects.equals(board[x - i + j][y - i + j].getStatus(), pastTurn))
                        board[x - i + j][y - i + j].setStatus(turn);

                break;
            } else if (Objects.equals(board[x - i][y - i].getStatus(), Status.EMPTY)) {
                break;
            }
    }

    public static void scanner() {
        manyTurns = 0;
        cleaner();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (Objects.equals(board[i][j].getStatus(), turn))
                    for (int z = 1; z < size - 1 - j; z++)
                        if (Objects.equals(board[i][j + z].getStatus(), pastTurn)) {
                            if (Objects.equals(board[i][j + z + 1].getStatus(), Status.EMPTY)) {

                                board[i][j + z + 1].setStatus(helper);
                                manyTurns++;
                            }

                        } else break;


        for (int i = size - 1; i >= 0; i--)
            for (int j = size - 1; j >= 0; j--)
                if (Objects.equals(board[i][j].getStatus(), turn))
                    for (int z = 1; z < j; z++)
                        if (Objects.equals(board[i][j - z].getStatus(), pastTurn)) {
                            if (Objects.equals(board[i][j - z - 1].getStatus(), Status.EMPTY)) {

                                board[i][j - z - 1].setStatus(helper);
                                manyTurns++;
                            }
                        } else break;

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (Objects.equals(board[i][j].getStatus(), turn))
                    for (int z = 1; z < size - 1 - i; z++)
                        if (Objects.equals(board[i + z][j].getStatus(), pastTurn)) {
                            if (Objects.equals(board[i + z + 1][j].getStatus(), Status.EMPTY)) {

                                board[i + z + 1][j].setStatus(helper);
                                manyTurns++;
                            }
                        } else break;


        for (int i = size - 1; i >= 0; i--)
            for (int j = size - 1; j >= 0; j--)
                if (Objects.equals(board[i][j].getStatus(), turn))
                    for (int z = 1; z < i; z++)
                        if (Objects.equals(board[i - z][j].getStatus(), pastTurn)) {
                            if (Objects.equals(board[i - z - 1][j].getStatus(), Status.EMPTY)) {
                                board[i - z - 1][j].setStatus(helper);

                                manyTurns++;
                            }
                        } else break;


        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (Objects.equals(board[i][j].getStatus(), turn))
                    for (int z = 1; z < Math.min(size - 1 - j, size - 1 - i); z++)
                        if (Objects.equals(board[i + z][j + z].getStatus(), pastTurn)) {
                            if (Objects.equals(board[i + z + 1][j + z + 1].getStatus(), Status.EMPTY)) {
                                board[i + z + 1][j + z + 1].setStatus(helper);
                                manyTurns++;
                            }
                        } else break;


        for (int i = 0; i < size; i++)
            for (int j = size - 1; j >= 0; j--)
                if (Objects.equals(board[i][j].getStatus(), turn))
                    for (int z = 1; z < Math.min(j, size - 1 - i); z++)
                        if (Objects.equals(board[i + z][j - z].getStatus(), pastTurn)) {
                            if (Objects.equals(board[i + z + 1][j - z - 1].getStatus(), Status.EMPTY)) {

                                board[i + z + 1][j - z - 1].setStatus(helper);
                                manyTurns++;
                            }
                        } else break;


        for (int i = size - 1; i >= 0; i--)
            for (int j = size - 1; j >= 0; j--) {
                if (Objects.equals(board[i][j].getStatus(), turn))
                    for (int z = 1; z < Math.min(i, j); z++)
                        if (Objects.equals(board[i - z][j - z].getStatus(), pastTurn)) {
                            if (Objects.equals(board[i - z - 1][j - z - 1].getStatus(), Status.EMPTY)) {

                                board[i - z - 1][j - z - 1].setStatus(helper);
                                manyTurns++;
                            }
                        } else break;

            }
        for (int i = size - 1; i >= 0; i--)
            for (int j = 0; j < size; j++)
                if (Objects.equals(board[i][j].getStatus(), turn))
                    for (int z = 1; z < Math.min(size - 1 - j, i); z++)
                        if (Objects.equals(board[i - z][j + z].getStatus(), pastTurn)) {
                            if (Objects.equals(board[i - z - 1][j + z + 1].getStatus(), Status.EMPTY)) {

                                board[i - z - 1][j + z + 1].setStatus(helper);
                                manyTurns++;
                            }
                        } else break;
    }

    private static void cleaner() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (Objects.equals(board[i][j].getStatus(), Status.WHITE_L) ||
                        Objects.equals(board[i][j].getStatus(), Status.BLACK_L))
                    board[i][j].setStatus(Status.EMPTY);
    }
}


