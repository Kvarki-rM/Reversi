public class BotsField {
    private int size;
    private static double[][] fieldValue;

    BotsField(int size, double[] vallCell) {
        this.size = size;
        fieldValue = new double[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                if (i == 0 || j == 0 || i == size - 1 || j == size - 1) {
                    if ((j == 0 && (i == size - 1 || i == 0)) || ((i == 0 || i == size - 1) && j == size - 1)) {
                        fieldValue[i][j] = vallCell[5];
                    } else if ((j == 0 && (i == 1 || i == size - 2)) || (j == size - 1 && (i == 1 || i == size - 2)) ||
                            (i == 0 && (j == 1 || j == size - 2)) || (i == size - 1 && (j == 1 || j == size - 2))) {
                        fieldValue[i][j] = vallCell[1];

                    } else fieldValue[i][j] = vallCell[4];
                } else fieldValue[i][j] = vallCell[2];
                if ((i >= size / 2 - 2) && (i <= size / 2 + 1) && (j >= size / 2 - 2) && (j <= size / 2 + 1)){
                    fieldValue[i][j] = vallCell[3];
                } else if ((j == 1 && (i == 1 || i == size - 2) || (j == size - 2 && (i == size - 2|| i == 1))))
                    fieldValue[i][j] = vallCell[0]; }
        fieldValue[size / 2 - 1][size / 2 - 1] = vallCell[6];
        fieldValue[size / 2 - 1][size / 2]= vallCell[6];
        fieldValue[size / 2][size / 2 - 1]= vallCell[6];
        fieldValue[size / 2][size / 2]= vallCell[6];
    }

    void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(fieldValue[i][j] + " ");
            }
            System.out.println();
        }
    }
}
