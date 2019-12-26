package Bot;

import Bot.BotCell;
import game.Board;
import game.Coordinate;
import game.Status;

public class BotsField {
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
                        fieldValue[i][j].setPosValue(Bot.vallCell[5]);
                    } else if ((j == 0 && (i == 1 || i == size - 2)) || (j == size - 1 && (i == 1 || i == size - 2)) ||
                            (i == 0 && (j == 1 || j == size - 2)) || (i == size - 1 && (j == 1 || j == size - 2))) {
                        fieldValue[i][j].setPosValue(Bot.vallCell[1]);

                    } else fieldValue[i][j].setPosValue(Bot.vallCell[4]);
                } else fieldValue[i][j].setPosValue(Bot.vallCell[2]);
                if ((i >= size / 2 - 2) && (i <= size / 2 + 1) && (j >= size / 2 - 2) && (j <= size / 2 + 1)) {
                    fieldValue[i][j].setPosValue(Bot.vallCell[3]);
                } else if ((j == 1 && (i == 1 || i == size - 2) || (j == size - 2 && (i == size - 2 || i == 1))))
                    fieldValue[i][j].setPosValue(Bot.vallCell[0]);
            }
        fieldValue[size / 2 - 1][size / 2 - 1].setPosValue(Bot.vallCell[6]);
        fieldValue[size / 2 - 1][size / 2].setPosValue(Bot.vallCell[6]);
        fieldValue[size / 2][size / 2 - 1].setPosValue(Bot.vallCell[6]);
        fieldValue[size / 2][size / 2].setPosValue(Bot.vallCell[6]);
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

    static void futureEnemyTurnsAndManyAbuility() {
        int temp = Board.numTurn;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Board.board[i][j].getStatus() == Board.helper) {
                    int growth = (Board.black - Board.white);
                    Board.add(i, j);
                    Board.switchTurn();
                    Board.scanner();
                    growth = growth - (Board.white - Board.black);
                    fieldValue[i][j].setGrowth(growth);
                    fieldValue[i][j].setEnemyNext(Board.manyTurns);
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
                if (Board.numTurn <= Bot.times[0]) {//ПРОВЕРКА ЭТАПА ИГРЫ
                    fieldValue[i][j].addSum(fieldValue[i][j].getEnemyNext() / Bot.enemyTurnsVal[0]);
                } else if (Board.numTurn <= Bot.times[1]) {
                    fieldValue[i][j].addSum(fieldValue[i][j].getEnemyNext() / Bot.enemyTurnsVal[1]);
                } else if (Board.numTurn <= Bot.times[2]) {
                    fieldValue[i][j].addSum(fieldValue[i][j].getEnemyNext() / Bot.enemyTurnsVal[2]);
                } else if (Board.numTurn <= Bot.times[3]) {
                    fieldValue[i][j].addSum(fieldValue[i][j].getEnemyNext() / Bot.enemyTurnsVal[3]);
                }
            }
        }
    }


    static void scanSingle() {//Доп баллы за одиночек на кважратах 2X2
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


    private static void paint() {
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                System.out.print(fieldValue[i][j].getSum() + " ");
            }
            System.out.println();
        }
        System.out.println("___________________________________________");
    }

    static void clearSumAndMany() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                fieldValue[i][j].setSum(0);
                fieldValue[i][j].setEnemyNext(0);
                fieldValue[i][j].setGrowth(0);
            }
    }

    static Coordinate end() {
        double max = -100;
        int x = 0;
        int y = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (fieldValue[i][j].getSum() > max && Board.board[i][j].getStatus() == Board.helper) {
                    max = fieldValue[i][j].getSum();
                    x = i;
                    y = j;
                }
            }
        }
        paint();
        return new Coordinate(x, y);
    }
}
