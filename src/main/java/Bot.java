public class Bot {
    static double[] val = new double[]{1.0, 2.0, 4.0, 6.0};//начало, середина, преконец, конец
    static int[] times = new int[]{16, 21, 16, 9};//ходов на начало, середина, преконец, конец
    static double[] vallCell = new double[]{-3.0, -2.0, 0.0, 1.0, 2.5, 5.0, 0.0};//**номера с 1 по 8 на поле 8 на 8**(a1, a8, h8, h1), (a7, a2, b8, b1 ||..), (*2, *7, b*, g*), квадрат у начальных, у стенок, (b2, b7, g2, g7),центр
    static private int manyTurns;
    static private int enemyThat;
    static private int enemyNext;
    /*Характеристика хода(бальная система):
     1) позиция
     2) количество фишек до && стадия игры
     3) ходов соперника на след ход
     4) **одинокие фишки && стадия игры**
      */

    Bot() {
        manyTurns = 0;
    }

    public static void main(String[] args) {

        BotsField field = new BotsField(8, vallCell);
        field.print();
    }


    static Coordinate coordinate(Board actualBoard) {
        BotsField field = new BotsField(actualBoard.board.length, vallCell);
        for (Cell[] cells : actualBoard.board)//подсчет статистики
            for (int j = 0; j < actualBoard.board[0].length; j++) {
                if (cells[j].getStatus() == actualBoard.helper)
                    manyTurns++;
            }

        return new Coordinate(0, 0);
    }
}
