package unam.ciencias.computoconcurrente.matrix;

/**
 * Clase que se ejecuta en un hilo, llenando las entradas del producto de dos matrices
 */

public class MatrixFiller implements Runnable {

    static int [] [] matrix1; // Primer matriz que se multiplca
    static int [] [] matrix2; // Segunda matriz que se multiplica
    static int [] [] product; // Matriz resultante del producto de las dos matrices que se multiplican

    private int from; // Índice de la primer entrada que llenará este hilo
    private int to;   // Índice de la última entrada que llenará este hilo

    /**
     * Construye un llenador de entradas del producto de las matrices, que se ejecutará en un hilo
     * @param from es la primer entrada del producto que llenará
     * @param to es la última entrada del producto que se llenará
     */

    public MatrixFiller (int from, int to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Inicia la ejecución del hilo
     * LLena las entradas de la matriz resultante asignadas a este hilo
     */

    @ Override
    public void run () {
        int i, j;
        while (from <= to) {
            i = from / product [0].length;
            j = from - product [0].length * i;
            product [i] [j] = dotProduct (i, j);
            from ++;
        }
    }

    /**
     * Calcula el producto punto de la fila i de matrix1 y la columna j de matrix2
     * @param i es el índice de la fila
     * @param j es el índice de la columna
     * @return el valor de la entrada i, j en la matriz resultante del producto
     */

    private int dotProduct (int i, int j) {
        int sum = 0;
        int k = 0;
        while (k < matrix2.length) {
            sum += matrix1 [i] [k] * matrix2 [k] [j];
            k ++;
        }
        return sum;
    }

}
