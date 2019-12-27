package bot;

import org.jetbrains.annotations.Contract;

class BotCell {
    private double Sum;
    private double posValue;
    private double enemyNext;
    private double growth;

    @Contract(pure = true)
    BotCell() {
        this.posValue = 0.0;
        enemyNext = 0;
    }

    void setPosValue(double val) {
        this.posValue = val;
    }

    void setSum(double val) {
        Sum = val;
    }

    void addSum(double val) {
        Sum += val;
    }

    void setEnemyNext(double x) {
        enemyNext = x;
    }

    void setGrowth(double x) {
        growth = x;
    }

    double getGrowth() {
        return growth;
    }

    double getEnemyNext() {
        return enemyNext;
    }

    double getPosValue() {
        return this.posValue;
    }

    double getSum() {
        return this.Sum;
    }
}
