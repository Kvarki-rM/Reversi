package Bot;

import Board.Board;
import Board.Coordinate;
import Game.Reversi;
import org.jetbrains.annotations.NotNull;

public class Bot {
    static double[] val = new double[]{1.0, 2.0, 4.0, 6.0};//начало, середина, преконец, конец //Ценность прироста от стадии игры
    static double[] enemyTurnsVal = new double[]{0.5, 1.5, 2.5, 6.0};//Ценность количества ходов сопернику
    private static double[] times;//Времени на каждую стадию игры для вышеуказанных ценоностей
    static double[] vallCell = new double[]{-3.0, 1.0, 1.1, 1.0, 2.5, 10.0, 0.0};//**номера с 1 по 8 на поле 8 на 8** углы, (a7, a2, b8, b1 ||..), (*2, *7, b*, g*), квадрат у начальных, у стенок, (b2, b7, g2, g7),центр
    static double forSingle = 4;
    /*Характеристика хода(бальная система):
     1) позиция
     2) количество фишек до && стадия игры
     3) количество прибовлеине фишек за ход на конкретной стадия игры
     3) ходов соперника на след ход (ход-стоимость)
     4) **одинокие фишки && стадия игры**
      */

    @NotNull
    public static Coordinate coordinate() {
        int temp = Reversi.size * Reversi.size;
        times = new double[]{temp * 0.24, temp * 0.55, temp * 0.70, temp * 0.80};
        Board.scanner();
        System.out.println(Board.numTurn);
        new BotsField();
        BotsField.clearSumAndMany();
        BotsField.comparatorAbuility();

        int time = 0; //ПРОВЕРКА ЭТАПА ИГРЫ
        if (Board.numTurn >= Bot.times[1]) time = 1;
        if (Board.numTurn >= Bot.times[2]) time = 2;
        if (Board.numTurn >= Bot.times[3]) time = 3;

        BotsField.futureEnemyTurnsAndManyAbuility(time);

        BotsField.scanSingle();
        return BotsField.end();
    }
}