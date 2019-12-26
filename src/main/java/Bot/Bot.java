package Bot;

import game.Board;
import game.Coordinate;
import org.jetbrains.annotations.NotNull;

public class Bot {
    static double[] val = new double[]{1.0, 2.0, 4.0, 6.0};//начало, середина, преконец, конец
    static double[] enemyTurnsVal = new double[]{6.0, 4.0, 2.0, 1.0};//ценность дать ход противнику
    static int[] times = new int[]{15, 35, 45, 52};//Номер ход на начало, середина, преконец, конец
    static double[] vallCell = new double[]{1.0, 1.0, 1.1, 1.0, 3.5, 6.0, 1.1};//**номера с 1 по 8 на поле 8 на 8**(a1, a8, h8, h1), (a7, a2, b8, b1 ||..), (*2, *7, b*, g*), квадрат у начальных, у стенок, (b2, b7, g2, g7),центр
    static double forSingle = 2;

    /*Характеристика хода(бальная система):
     1) позиция
     2) количество фишек до && стадия игры
     3) количество прибовлеине фишек за ход на конкретной стадия игры
     3) ходов соперника на след ход (ход-стоимость)
     4) **одинокие фишки && стадия игры**
      */

    @NotNull
    public static Coordinate coordinate() {
        System.out.println(Board.numTurn);
        new BotsField();
      BotsField.clearSumAndMany();
      BotsField.comparatorAbuility();
      BotsField.futureEnemyTurnsAndManyAbuility();

        BotsField.scanSingle();
        return BotsField.end();
    }
}