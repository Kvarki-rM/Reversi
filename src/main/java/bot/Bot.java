package bot;

import board.Board;
import board.Coordinate;
import game.Reversi;
import org.jetbrains.annotations.NotNull;

public class Bot {
    static double[] growthVal = new double[]{0.2, 0.4, 1.0, 4.0};//начало, середина, преконец, конец //Ценность прироста от стадии игры
    static double[] enemyTurnsVal = new double[]{0.4, 0.33, 0.25, 0.1};//Ценность количества ходов сопернику
    private static double[] timings;//Времени на каждую стадию игры для вышеуказанных ценоностей
    static double[] cellVall = new double[]{-3.0, -4.0, 0.5, 1.0, 3.5, 15.0, 0.0};//**номера с 1 по 8 на поле 8 на 8** углы, (a7, a2, b8, b1 ||..), (*2, *7, b*, g*), квадрат у начальных, у стенок, (b2, b7, g2, g7),центр
    static double forSingle = 4;
    static double forHelper = 5;
    private static double forHelp = 3;

    /*Параметры от которых зависит выбор хода бота:
     1) позиция на поле, все поле разделено на сеторы разной стоимости (стоимость (CellVall[]) распределяется в BotsField из ) и сравнивается в comparatorAbuility()
     2) количество фишек (если фишек катострофически мало то автоматически выбирается самый прибльный вариант helper())
     3) количество прибовлеине фишек за ход на конкретной стадия игры (разбаловка из growthVal[] и распределяется в futureEnemyTurnsAndMany())
     3) ходов соперника на след ход (так же зависит от стадии игры enemyTurnsVal[] и распределяется в futureEnemyTurnsAndMany())
     4) **пустые клетки среди вражеских фишек получают небольшой бонус scanSingle**
      */

    @NotNull
    public static Coordinate coordinate() {
        int temp = Reversi.size * Reversi.size - 4;
        timings = new double[]{temp * 0.1, temp * 0.2, temp * 0.6, temp * 0.85};
        Board.scanner();
        System.out.print(Board.numTurn);
        int phase = 0; //ПРОВЕРКА ЭТАПА ИГРЫ
        if (Board.numTurn >= Bot.timings[1]) phase = 1;
        if (Board.numTurn >= Bot.timings[2]) phase = 2;
        if (Board.numTurn >= Bot.timings[3]) phase = 3;

        new BotsField();
        BotsField.clearSumAndMany();
        BotsField.comparatorAbuility();

        System.out.println(" " + phase);
        if ((Bot.forHelp >= Board.white || Bot.forHelp >= Board.black) && phase > 0)
            BotsField.futureEnemyTurnsAndMany(phase, true);
        else BotsField.futureEnemyTurnsAndMany(phase, false);

        BotsField.scanSingle();
        return BotsField.end();
    }
}