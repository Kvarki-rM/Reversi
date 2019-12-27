package bot;

import board.Board;
import board.Coordinate;
import game.Status;
import game.Reversi;
import javafx.util.Pair;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

class BotsField {
    static private int size;
    private static BotCell[][] fieldValue;

    BotsField() {
        size = Board.board.length;
        fieldValue = new BotCell[size][size];
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                fieldValue[x][y] = new BotCell();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                if (i == 0 || j == 0 || i == size - 1 || j == size - 1) {
                    if ((j == 0 && (i == size - 1 || i == 0)) || ((i == 0 || i == size - 1) && j == size - 1)) {
                        fieldValue[i][j].setPosValue(Bot.cellVall[5]);
                    } else if ((j == 0 && (i == 1 || i == size - 2)) || (j == size - 1 && (i == 1 || i == size - 2)) ||
                            (i == 0 && (j == 1 || j == size - 2)) || (i == size - 1 && (j == 1 || j == size - 2))) {
                        fieldValue[i][j].setPosValue(Bot.cellVall[1]);

                    } else fieldValue[i][j].setPosValue(Bot.cellVall[4]);
                } else fieldValue[i][j].setPosValue(Bot.cellVall[2]);
                if ((i >= size / 2 - 2) && (i <= size / 2 + 1) && (j >= size / 2 - 2) && (j <= size / 2 + 1)) {
                    fieldValue[i][j].setPosValue(Bot.cellVall[3]);
                } else if ((j == 1 && (i == 1 || i == size - 2) || (j == size - 2 && (i == size - 2 || i == 1))))
                    fieldValue[i][j].setPosValue(Bot.cellVall[0]);
            }
        fieldValue[size / 2 - 1][size / 2 - 1].setPosValue(Bot.cellVall[6]);
        fieldValue[size / 2 - 1][size / 2].setPosValue(Bot.cellVall[6]);
        fieldValue[size / 2][size / 2 - 1].setPosValue(Bot.cellVall[6]);
        fieldValue[size / 2][size / 2].setPosValue(Bot.cellVall[6]);
    }

    static void comparatorAbuility() {//разболовка позиции за ход занятую позицию(только за позицию)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Board.board[i][j].getStatus() == Board.helper) {
                    fieldValue[i][j].setSum(fieldValue[i][j].getPosValue());
                }
            }
        }
    }

    static void futureEnemyTurnsAndMany(int time, boolean ult) {
        int temp = Board.numTurn;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Board.board[i][j].getStatus() == Board.helper) {
                    int growth;

                    if (Reversi.pColor == Status.BLACK) growth = (Board.white);
                    else growth = (Board.black);

                    Board.add(i, j);
                    Board.switchTurn();

                    if (Reversi.pColor == Status.BLACK) growth = (Board.white) - growth - 1;
                    else growth = (Board.black) - growth - 1;
                    if (ult) fieldValue[i][j].setGrowth((growth * Bot.growthVal[time] * Bot.forHelper));
                    else fieldValue[i][j].setGrowth((growth * Bot.growthVal[time]));
                    fieldValue[i][j].setEnemyNext(Board.manyTurns * Bot.enemyTurnsVal[time]);
                    Board.backTurn();
                    Board.switchTurn();
                }
            }
        }
        Board.numTurn = temp;
        converter();
    }

    private static void converter() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fieldValue[i][j].addSum(fieldValue[i][j].getEnemyNext());
                fieldValue[i][j].addSum(fieldValue[i][j].getGrowth());
            }
        }

    }


    static void scanSingle() {//Доп баллы за одиночек на квадратах 2X2
        for (int i = 0; i < size / 2; i++) {
            for (int j = 0; j < size / 2; j++) {
                double temp = 0;
                if (Board.board[i * 2][j * 2].getStatus() == Board.pastTurn && Board.board[i * 2][j * 2].getStatus() != Status.EMPTY)
                    temp += Bot.forSingle / 4;
                if (Board.board[i * 2 + 1][j * 2].getStatus() == Board.pastTurn && Board.board[i * 2 + 1][j * 2].getStatus() != Status.EMPTY)
                    temp += Bot.forSingle / 4;
                if (Board.board[i * 2][j * 2 + 1].getStatus() == Board.pastTurn && Board.board[i * 2][j * 2 + 1].getStatus() != Status.EMPTY)
                    temp += Bot.forSingle / 4;
                if (Board.board[i * 2 + 1][j * 2 + 1].getStatus() == Board.pastTurn && Board.board[i * 2 + 1][j * 2 + 1].getStatus() != Status.EMPTY)
                    temp += Bot.forSingle / 4;

                if (Board.board[i * 2][j * 2].getStatus() == Board.helper) {
                    fieldValue[i * 2][j * 2].addSum(temp);
                }
                if (Board.board[i * 2 + 1][j * 2].getStatus() == Board.helper) {
                    fieldValue[i * 2 + 1][j * 2].addSum(temp);
                }
                if (Board.board[i * 2][j * 2 + 1].getStatus() == Board.helper) {
                    fieldValue[i * 2][j * 2 + 1].addSum(temp);
                }
                if (Board.board[i * 2 + 1][j * 2 + 1].getStatus() == Board.helper) {
                    fieldValue[i * 2 + 1][j * 2 + 1].addSum(temp);
                }
            }
        }
    }

    @NotNull
    @Contract(" -> new")
    static Coordinate end() {//поиск самого большого плюса на таблице fieldValue[][](на которой сраниваем значения)
        double max = -100;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (fieldValue[i][j].getSum() > max && Board.board[i][j].getStatus() == Board.helper)
                    max = fieldValue[i][j].getSum();

        ArrayList<Pair<Integer, Integer>> aListColors = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (fieldValue[i][j].getSum() == max && Board.board[i][j].getStatus() == Board.helper) {
                    aListColors.add(new Pair<>(i,j));
                }
            }
        }
        Pair<Integer, Integer> z = aListColors.get(new Random().nextInt(aListColors.size()));
        paint();
        return new Coordinate(z.getKey(), z.getValue());
    }

    static void clearSumAndMany() {//обычный клинер значений fieldValu[][]
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                fieldValue[i][j].setSum(0);
                fieldValue[i][j].setEnemyNext(0);
                fieldValue[i][j].setGrowth(0);
            }
    }

    private static void paint() {
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                System.out.print(fieldValue[i][j].getSum() + " ");
            }
            System.out.println();
        }
        System.out.println("________________________________");
    }
}
