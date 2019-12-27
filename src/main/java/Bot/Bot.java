package Bot;

import Board.Board;
import Board.Coordinate;
import Game.Reversi;
import org.jetbrains.annotations.NotNull;

public class Bot {
    static double[] growthVal = new double[]{0.5, 0.7, 1.2, 2.0};//начало, середина, преконец, конец //Ценность прироста от стадии игры
    static double[] enemyTurnsVal = new double[]{0.4, 0.3, 0.2, 0.1};//Ценность количества ходов сопернику
    private static double[] times;//Времени на каждую стадию игры для вышеуказанных ценоностей
    static double[] CellVall = new double[]{-3.0, 1.0, 1.1, 1.0, 2.5, 15.0, 0.0};//**номера с 1 по 8 на поле 8 на 8** углы, (a7, a2, b8, b1 ||..), (*2, *7, b*, g*), квадрат у начальных, у стенок, (b2, b7, g2, g7),центр
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
        int temp = Reversi.size * Reversi.size-4;
        times = new double[]{temp * 0.2, temp * 0.45, temp * 0.6, temp * 0.825};
        Board.scanner();
        System.out.print(Board.numTurn);

        new BotsField();
        BotsField.clearSumAndMany();
        BotsField.comparatorAbuility();

        int time = 0; //ПРОВЕРКА ЭТАПА ИГРЫ
        if (Board.numTurn >= Bot.times[1]) time = 1;
        if (Board.numTurn >= Bot.times[2]) time = 2;
        if (Board.numTurn >= Bot.times[3]) time = 3;

        System.out.println(" " + time);
        BotsField.futureEnemyTurnsAndManyAbuility(time);

        BotsField.scanSingle();
        return BotsField.end();
    }
}