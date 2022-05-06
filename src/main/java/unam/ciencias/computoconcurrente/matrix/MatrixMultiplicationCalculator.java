package unam.ciencias.computoconcurrente.matrix;

/**
 * Clase que calcula el producto de dos matrices de forma paralela
 */

public class MatrixMultiplicationCalculator {

    /**
     * Calcula el producto matrix1 por matrix2
     * @param matrix1 es la primer matriz que se va a multiplicar
     * @param matrix2 es la segunda matriz que se va a multiplicar
     * @param threads es el número de hilos con los que se va a realizar el cálculo
     * @return la matriz resultante de multiplicar matrix1 por matrix2
     * @throws InterruptedException
     */

    public int [] [] multiplyMatrices (int [] [] matrix1, int [] [] matrix2, int threads) throws InterruptedException {
        if (matrix1 [0].length != matrix2.length || threads <= 0) throw new IllegalArgumentException ();
        MatrixFiller.matrix1 = matrix1;
        MatrixFiller.matrix2 = matrix2;
        MatrixFiller.product = new int [matrix1.length] [matrix2 [0].length];

        // Crear los hilos e iniciar su ejecución
        Thread [] fillers = new Thread [threads];
        int size = matrix1.length * matrix2 [0].length;
        int length = 1 + (size - 1) / threads;
        int index = 0;
        int from = 0;
        int to = length - 1;
        while (to < size) {
            fillers [index] = new Thread (new MatrixFiller (from, to));
            fillers [index].start ();
            from = to + 1;
            to += length;
            index ++;
        }
        if (from < size) {
            fillers [index] = new Thread (new MatrixFiller (from, size - 1));
            fillers [index].start ();
            index ++;
        }

        // Esperar a que todos los hilos terminen
        while (index > 0) {
            index --;
            fillers [index].join ();
        }

        return MatrixFiller.product;
    }

}
