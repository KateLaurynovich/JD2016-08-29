package by.it.emelyanov.jd01_04;

public class Util {
    static double[] findRoot(double[][] a, double[] y) {
        double x[] = new double[y.length];
        //подготовим операционную матрицу
        int n = a.length;
        double m[][] = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = a[i][j];
            }
            m[i][n] = y[i];
        }
        InOut.printArr(m);
        //прямой ход(метод жордана)
        for (int diag = 0; diag < n - 1; diag++) {
            for (int row = diag + 1; row < n; row++) {
                double k = m[row][diag] / m[diag][diag];
                for (int col = 0; col < n + 1; col++) {
                    m[row][col] = m[row][col] - m[diag][col] * k;
                }
            }
        }
        InOut.printArr(m);
        //обратный ход(метод гаусса)
        for (int diag = n - 1; diag > 0; diag--) {
            for (int row = diag - 1; row >= 0; row--) {
                double k = m[row][diag] / m[diag][diag];
                for (int col = 0; col < n + 1; col++) {
                    m[row][col] = m[row][col] - m[diag][col] * k;
                }
            }
        }
        InOut.printArr(m);
        //решение системы
        for (int i = 0; i < n; i++) {
            double div = m[i][i];
            for (int j = 0; j < n + 1; j++) {
                m[i][j] = m[i][j] / div;
            }
        }
        InOut.printArr(m);
        return x;
    }

}
