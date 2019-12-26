class BotCell {
    private double Sum;
    private double posValue;
    private double enemyNext;

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

    void setEnemyNext(int x) {
        enemyNext = x;
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
