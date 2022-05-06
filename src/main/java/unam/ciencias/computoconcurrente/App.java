package unam.ciencias.computoconcurrente;

import unam.ciencias.computoconcurrente.graph.GraphDiameterCalculator;
import unam.ciencias.computoconcurrente.matrix.MatrixMultiplicationCalculator;

import java.util.Arrays;

/**
 * Programa que implementa algoritmos paralelos para el cálculo del diámetro de una gráfica y la multiplicación de matrices
 */

public class App {

    /**
     * Inicia la ejecución del programa
     * @param args son los argumentos de la línea de comandos
     */

    public static void main (String [] args) throws InterruptedException {

        // Gráfica de ejemplo con diámetro 4
        /*
                0   1 —— 2
              / | \ |    |
            3   |   4 —— 5
                |    \  /
                6      7
        */
        boolean [] [] graph = {
                   /*  0      1      2      3      4      5      6      7  */
            /* 0 */ {false, false, false,  true,  true, false,  true, false},
            /* 1 */ {false, false,  true, false,  true, false, false, false},
            /* 2 */ {false,  true, false, false, false,  true, false, false},
            /* 3 */ { true, false, false, false, false, false, false, false},
            /* 4 */ { true,  true, false, false, false,  true, false,  true},
            /* 5 */ {false, false,  true, false,  true, false, false,  true},
            /* 6 */ { true, false, false, false, false, false, false, false},
            /* 7 */ {false, false, false, false,  true,  true, false, false}
        };

        GraphDiameterCalculator diameterCalculator = new GraphDiameterCalculator ();
        System.out.println ("Graph diameter: " + diameterCalculator.getDiameter (graph, 2));

        // Matrices de ejemplo
        /*
            |  5 -3  2 |                   |  -23   17  -20  -47 |
            | -2  0  6 | |  4  5 -9 -6 |   |  -56   38   30   24 |
            | -6  3 -8 | |  9  8 -7  7 | = |   67  -70   17   41 |
            |  3  4 -6 | | -8  8  2  2 |   |   96   -1  -67   -2 |
            | -6 -8  7 |                   | -152  -38  124   -6 |
        */
        int [] [] matrix1 = {
            { 5, -3,  2},
            {-2,  0,  6},
            {-6,  3, -8},
            { 3,  4, -6},
            {-6, -8,  7}
        };

        int [] [] matrix2 = {
            { 4,  5, -9, -6},
            { 9,  8, -7,  7},
            {-8,  8,  2,  2}
        };

        MatrixMultiplicationCalculator multiplicationCalculator = new MatrixMultiplicationCalculator ();
        System.out.println ("Matrices product:");
        for (int [] row : multiplicationCalculator.multiplyMatrices (matrix1, matrix2, 2)) System.out.println (Arrays.toString (row));
    }
}
