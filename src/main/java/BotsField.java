import org.jetbrains.annotations.NotNull;

class BotsField {
    static private int size;
    private static BotCell[][] fieldValue;
    private Board field;

    BotsField(@NotNull Board field) {
        this.field = field;
        size = field.board.length;
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

    void comparatorAbuility() {//разболовка позиции за ход занятую позицию(только за позицию)
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field.board[i][j].getStatus() == field.helper) {
                    fieldValue[i][j].setSum(fieldValue[i][j].getPosValue());
                }
            }
        }
    }


    static Coordinate end() {
        double max = -100;
        int x = 0;
        int y = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (fieldValue[i][j].getSum() > max) {
                    max = fieldValue[i][j].getSum();
                    x = i;
                    y = j;
                }
            }
        }
        print();
        return new Coordinate(x, y);
    }

    void futureEnemyTurns() {
        int temp = field.numTurn;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field.board[i][j].getStatus() == field.helper) {
                    field.add(i, j);
                    field.switchTurn();
                    field.scanner();
                    fieldValue[i][j].setEnemyNext(Board.manyTurns);
                    field.backTurn();
                    field.switchTurn();
                }
            }
        }
        field.numTurn = temp;
        converter();
    }

    static void converter() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (true) {//ПРОВЕРКА ЭТАПА ИГРЫ
                    fieldValue[i][j].addSum(fieldValue[i][j].getEnemyNext() / Bot.times[0]*5);
                } else if (true) {
                } else if (true) {
                }
                if (true) {
                }
            }
        }
    }

    void scanSingle() {
        for (int i = 0; i < size / 2; i++) {
            for (int j = 0; j < size / 2; j++) {
                double temp = Bot.forSingle;
                if (field.board[i * 2][j * 2].getStatus() != field.turn) temp -= Bot.forSingle / 4;
                if (field.board[i * 2 + 1][j * 2].getStatus() != field.turn) temp -= Bot.forSingle / 4;
                if (field.board[i * 2][j * 2 + 1].getStatus() != field.turn) temp -= Bot.forSingle / 4;
                if (field.board[i * 2 + 1][j * 2 + 1].getStatus() != field.turn) temp -= Bot.forSingle / 4;

                if (field.board[i * 2][j * 2].getStatus() == field.turn) fieldValue[i * 2][j * 2].addSum(temp);
                if (field.board[i * 2 + 1][j * 2].getStatus() == field.turn) fieldValue[i * 2 + 1][j * 2].addSum(temp);
                if (field.board[i * 2][j * 2 + 1].getStatus() == field.turn) fieldValue[i * 2][j * 2 + 1].addSum(temp);
                if (field.board[i * 2 + 1][j * 2 + 1].getStatus() == field.turn)
                    fieldValue[i * 2 + 1][j * 2 + 1].addSum(temp);

            }
        }
    }


    static void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(fieldValue[i][j].getSum() + " ");
            }
            System.out.println();
        }
        System.out.println("_____________________________________________________");
    }

    static void clearSumAndMany() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                fieldValue[i][j].setSum(0);
                fieldValue[i][j].setEnemyNext(0);
            }
    }

}
