package unam.ciencias.computoconcurrente;

import unam.ciencias.computoconcurrente.graph.GraphDiameterCalculator;
import unam.ciencias.computoconcurrente.matrix.MatrixMultiplicationCalculator;
import java.util.Arrays;
import java.util.Random;
/**
 * Programa que implementa algoritmos paralelos para el cálculo del diámetro de una gráfica y la multiplicación de matrices
 */

public class App {

    /**
     * Inicia la ejecución del programa
     * @param args son los argumentos de la línea de comandos
     */

    public static void main (String [] args) throws InterruptedException {
        long [] tiempos_iniciales=new long[5];
        long [] tiempos_finales=new long[5];
        long [] tiempos=new long[5];
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
        System.out.println("------------Diametro graficas-----------------");
        GraphDiameterCalculator diameterCalculator = new GraphDiameterCalculator ();
        tiempos_iniciales[0]=System.currentTimeMillis();
        System.out.println ("Graph diameter: " + diameterCalculator.getDiameter (graph, 1));
        tiempos_finales[0]=System.currentTimeMillis();
        
        tiempos_iniciales[1]=System.currentTimeMillis();
        System.out.println ("Graph diameter: " + diameterCalculator.getDiameter (graph, 2));
        tiempos_finales[1]=System.currentTimeMillis();

        tiempos_iniciales[2]=System.currentTimeMillis();
        System.out.println ("Graph diameter: " + diameterCalculator.getDiameter (graph, 4));
        tiempos_finales[2]=System.currentTimeMillis();

        tiempos_iniciales[3]=System.currentTimeMillis();
        System.out.println ("Graph diameter: " + diameterCalculator.getDiameter (graph, 8));
        tiempos_finales[3]=System.currentTimeMillis();

        tiempos_iniciales[4]=System.currentTimeMillis();
        System.out.println ("Graph diameter: " + diameterCalculator.getDiameter (graph, 16));
        tiempos_finales[4]=System.currentTimeMillis();

        tiempos[0]=tiempos_finales[0]-tiempos_iniciales[0];
        tiempos[1]=tiempos_finales[1]-tiempos_iniciales[1];
        tiempos[2]=tiempos_finales[2]-tiempos_iniciales[2];
        tiempos[3]=tiempos_finales[3]-tiempos_iniciales[3];
        tiempos[4]=tiempos_finales[4]-tiempos_iniciales[4];
        System.out.println("Tiempo secuencial: "+(tiempos_finales[0]-tiempos_iniciales[0]));
        System.out.println("Tiempo 2 hilos: "+(tiempos_finales[1]-tiempos_iniciales[1]));
        System.out.println("Tiempo 4 hilos: "+(tiempos_finales[2]-tiempos_iniciales[2]));
        System.out.println("Tiempo 8 hilos: "+(tiempos_finales[3]-tiempos_iniciales[3]));
        System.out.println("Tiempo 16 hilos: "+(tiempos_finales[4]-tiempos_iniciales[4]));

        System.out.println("Speedup:");
        System.out.println("2 hilos: "+(tiempos[0])+"/"+(tiempos[1])+"="+((double)tiempos[0]/tiempos[1]));
        System.out.println("4 hilos: "+(tiempos[0])+"/"+(tiempos[2])+"="+((double)tiempos[0]/tiempos[2]));
        System.out.println("8 hilos: "+(tiempos[0])+"/"+(tiempos[3])+"="+((double)tiempos[0]/tiempos[3]));
        System.out.println("16 hilos: "+(tiempos[0])+"/"+(tiempos[4])+"="+((double)tiempos[0]/tiempos[4]));

        System.out.println("Eficiencia");
        System.out.println("2 hilos: "+(tiempos[0])+"/"+(tiempos[1])+"="+((double)tiempos[0]/(2*tiempos[1])));
        System.out.println("4 hilos: "+(tiempos[0])+"/"+(tiempos[2])+"="+((double)tiempos[0]/(4*tiempos[2])));
        System.out.println("8 hilos: "+(tiempos[0])+"/"+(tiempos[3])+"="+((double)tiempos[0]/(8*tiempos[3])));
        System.out.println("16 hilos: "+(tiempos[0])+"/"+(tiempos[4])+"="+((double)tiempos[0]/(16*tiempos[4])));

        
        System.out.println("----Grafica aleatoria 1000x1000 probabilidad 1/10000 de conectar nodos-----");

        /* 
        for (boolean [] row : randGraph(8, 8)) 
            System.out.println (Arrays.toString (row));
        */ 
        graph=randGraph(1000, 1000,10000);
        //graph=randGraph(8, 8, 20);
        tiempos_iniciales[0]=System.currentTimeMillis();
        System.out.println ("Graph diameter: " + diameterCalculator.getDiameter (graph, 1));
        tiempos_finales[0]=System.currentTimeMillis();
        
        tiempos_iniciales[1]=System.currentTimeMillis();
        System.out.println ("Graph diameter: " + diameterCalculator.getDiameter (graph, 2));
        tiempos_finales[1]=System.currentTimeMillis();

        tiempos_iniciales[2]=System.currentTimeMillis();
        System.out.println ("Graph diameter: " + diameterCalculator.getDiameter (graph, 4));
        tiempos_finales[2]=System.currentTimeMillis();

        tiempos_iniciales[3]=System.currentTimeMillis();
        System.out.println ("Graph diameter: " + diameterCalculator.getDiameter (graph, 8));
        tiempos_finales[3]=System.currentTimeMillis();

        tiempos_iniciales[4]=System.currentTimeMillis();
        System.out.println ("Graph diameter: " + diameterCalculator.getDiameter (graph, 16));
        tiempos_finales[4]=System.currentTimeMillis();
        System.out.println("Tiempo secuencial: "+(tiempos_finales[0]-tiempos_iniciales[0]));
        System.out.println("Tiempo 2 hilos: "+(tiempos_finales[1]-tiempos_iniciales[1]));
        System.out.println("Tiempo 4 hilos: "+(tiempos_finales[2]-tiempos_iniciales[2]));
        System.out.println("Tiempo 8 hilos: "+(tiempos_finales[3]-tiempos_iniciales[3]));
        System.out.println("Tiempo 16 hilos: "+(tiempos_finales[4]-tiempos_iniciales[4]));
        tiempos[0]=tiempos_finales[0]-tiempos_iniciales[0];
        tiempos[1]=tiempos_finales[1]-tiempos_iniciales[1];
        tiempos[2]=tiempos_finales[2]-tiempos_iniciales[2];
        tiempos[3]=tiempos_finales[3]-tiempos_iniciales[3];
        tiempos[4]=tiempos_finales[4]-tiempos_iniciales[4];
        System.out.println("Speedup:");
        System.out.println("2 hilos: "+(tiempos[0])+"/"+(tiempos[1])+"="+((double)tiempos[0]/tiempos[1]));
        System.out.println("4 hilos: "+(tiempos[0])+"/"+(tiempos[2])+"="+((double)tiempos[0]/tiempos[2]));
        System.out.println("8 hilos: "+(tiempos[0])+"/"+(tiempos[3])+"="+((double)tiempos[0]/tiempos[3]));
        System.out.println("16 hilos: "+(tiempos[0])+"/"+(tiempos[4])+"="+((double)tiempos[0]/tiempos[4]));

        System.out.println("Eficiencia");
        System.out.println("2 hilos: "+(tiempos[0])+"/"+(tiempos[1])+"="+((double)tiempos[0]/(2*tiempos[1])));
        System.out.println("4 hilos: "+(tiempos[0])+"/"+(tiempos[2])+"="+((double)tiempos[0]/(4*tiempos[2])));
        System.out.println("8 hilos: "+(tiempos[0])+"/"+(tiempos[3])+"="+((double)tiempos[0]/(8*tiempos[3])));
        System.out.println("16 hilos: "+(tiempos[0])+"/"+(tiempos[4])+"="+((double)tiempos[0]/(16*tiempos[4])));


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


        System.out.println("------------Multiplicacion de matrices-----------------");
        MatrixMultiplicationCalculator multiplicationCalculator = new MatrixMultiplicationCalculator ();
        //

        tiempos_iniciales[0]=System.currentTimeMillis();
        multiplicationCalculator.multiplyMatrices (matrix1, matrix2, 1);
        tiempos_finales[0]=System.currentTimeMillis();
        System.out.println ("Matrices product:");
        for (int [] row : multiplicationCalculator.multiplyMatrices (matrix1, matrix2, 2)) 
            System.out.println (Arrays.toString (row));
        System.out.println("Tiempo secuencial: "+(tiempos_finales[0]-tiempos_iniciales[0]));
        
        tiempos_iniciales[1]=System.currentTimeMillis();
        multiplicationCalculator.multiplyMatrices (matrix1, matrix2, 2);
        tiempos_finales[1]=System.currentTimeMillis();
        System.out.println("Tiempo 2 hilos: "+(tiempos_finales[1]-tiempos_iniciales[1]));

        tiempos_iniciales[2]=System.currentTimeMillis();
        multiplicationCalculator.multiplyMatrices (matrix1, matrix2, 4);
        tiempos_finales[2]=System.currentTimeMillis();
        System.out.println("Tiempo 4 hilos: "+(tiempos_finales[2]-tiempos_iniciales[2]));

        tiempos_iniciales[3]=System.currentTimeMillis();
        multiplicationCalculator.multiplyMatrices (matrix1, matrix2, 8);
        tiempos_finales[3]=System.currentTimeMillis();
        System.out.println("Tiempo 8 hilos: "+(tiempos_finales[3]-tiempos_iniciales[3]));

        tiempos_iniciales[4]=System.currentTimeMillis();
        multiplicationCalculator.multiplyMatrices (matrix1, matrix2, 16);
        tiempos_finales[4]=System.currentTimeMillis();
        System.out.println("Tiempo 16 hilos: "+(tiempos_finales[4]-tiempos_iniciales[4]));

        tiempos[0]=tiempos_finales[0]-tiempos_iniciales[0];
        tiempos[1]=tiempos_finales[1]-tiempos_iniciales[1];
        tiempos[2]=tiempos_finales[2]-tiempos_iniciales[2];
        tiempos[3]=tiempos_finales[3]-tiempos_iniciales[3];
        tiempos[4]=tiempos_finales[4]-tiempos_iniciales[4];
        System.out.println("Speedup:");
        System.out.println("2 hilos: "+(tiempos[0])+"/"+(tiempos[1])+"="+((double)tiempos[0]/tiempos[1]));
        System.out.println("4 hilos: "+(tiempos[0])+"/"+(tiempos[2])+"="+((double)tiempos[0]/tiempos[2]));
        System.out.println("8 hilos: "+(tiempos[0])+"/"+(tiempos[3])+"="+((double)tiempos[0]/tiempos[3]));
        System.out.println("16 hilos: "+(tiempos[0])+"/"+(tiempos[4])+"="+((double)tiempos[0]/tiempos[4]));

        System.out.println("Eficiencia");
        System.out.println("2 hilos: "+(tiempos[0])+"/"+(tiempos[1])+"="+((double)tiempos[0]/(2*tiempos[1])));
        System.out.println("4 hilos: "+(tiempos[0])+"/"+(tiempos[2])+"="+((double)tiempos[0]/(4*tiempos[2])));
        System.out.println("8 hilos: "+(tiempos[0])+"/"+(tiempos[3])+"="+((double)tiempos[0]/(8*tiempos[3])));
        System.out.println("16 hilos: "+(tiempos[0])+"/"+(tiempos[4])+"="+((double)tiempos[0]/(16*tiempos[4])));



        matrix1=randMatriz(600,2000);
        matrix2= randMatriz(2000, 600);
        System.out.println("--------------Matrices 600x2000, 2000x600 ---------");
        /*
        System.out.println("--------------Matriz 1 ---------");
        
       for (int [] row : matrix1) 
            System.out.println (Arrays.toString (row));
        System.out.println("--------------Matriz 2 ---------");
        
        for (int [] row : matrix1) 
            System.out.println (Arrays.toString (row));
        System.out.println ("---------Matrices product ---------");
        for (int [] row : multiplicationCalculator.multiplyMatrices (matrix1, matrix2, 2)) 
            System.out.println (Arrays.toString (row));
        */
        
        
        tiempos_iniciales[0]=System.currentTimeMillis();
        multiplicationCalculator.multiplyMatrices (matrix1, matrix2, 1);
        tiempos_finales[0]=System.currentTimeMillis();
        System.out.println("Tiempo secuencial: "+(tiempos_finales[0]-tiempos_iniciales[0]));
        
        tiempos_iniciales[1]=System.currentTimeMillis();
        multiplicationCalculator.multiplyMatrices (matrix1, matrix2, 2);
        tiempos_finales[1]=System.currentTimeMillis();
        System.out.println("Tiempo 2 hilos: "+(tiempos_finales[1]-tiempos_iniciales[1]));

        tiempos_iniciales[2]=System.currentTimeMillis();
        multiplicationCalculator.multiplyMatrices (matrix1, matrix2, 4);
        tiempos_finales[2]=System.currentTimeMillis();
        System.out.println("Tiempo 4 hilos: "+(tiempos_finales[2]-tiempos_iniciales[2]));

        tiempos_iniciales[3]=System.currentTimeMillis();
        multiplicationCalculator.multiplyMatrices (matrix1, matrix2, 8);
        tiempos_finales[3]=System.currentTimeMillis();
        System.out.println("Tiempo 8 hilos: "+(tiempos_finales[3]-tiempos_iniciales[3]));

        tiempos_iniciales[4]=System.currentTimeMillis();
        multiplicationCalculator.multiplyMatrices (matrix1, matrix2, 16);
        tiempos_finales[4]=System.currentTimeMillis();
        System.out.println("Tiempo 16 hilos: "+(tiempos_finales[4]-tiempos_iniciales[4]));

        tiempos[0]=tiempos_finales[0]-tiempos_iniciales[0];
        tiempos[1]=tiempos_finales[1]-tiempos_iniciales[1];
        tiempos[2]=tiempos_finales[2]-tiempos_iniciales[2];
        tiempos[3]=tiempos_finales[3]-tiempos_iniciales[3];
        tiempos[4]=tiempos_finales[4]-tiempos_iniciales[4];
        System.out.println("Speedup:");
        System.out.println("2 hilos: "+(tiempos[0])+"/"+(tiempos[1])+"="+((double)tiempos[0]/tiempos[1]));
        System.out.println("4 hilos: "+(tiempos[0])+"/"+(tiempos[2])+"="+((double)tiempos[0]/tiempos[2]));
        System.out.println("8 hilos: "+(tiempos[0])+"/"+(tiempos[3])+"="+((double)tiempos[0]/tiempos[3]));
        System.out.println("16 hilos: "+(tiempos[0])+"/"+(tiempos[4])+"="+((double)tiempos[0]/tiempos[4]));

        System.out.println("Eficiencia");
        System.out.println("2 hilos: "+(tiempos[0])+"/"+(tiempos[1])+"="+((double)tiempos[0]/(2*tiempos[1])));
        System.out.println("4 hilos: "+(tiempos[0])+"/"+(tiempos[2])+"="+((double)tiempos[0]/(4*tiempos[2])));
        System.out.println("8 hilos: "+(tiempos[0])+"/"+(tiempos[3])+"="+((double)tiempos[0]/(8*tiempos[3])));
        System.out.println("16 hilos: "+(tiempos[0])+"/"+(tiempos[4])+"="+((double)tiempos[0]/(16*tiempos[4])));

    }

    private static int[][] randMatriz(int filas, int columnas){
        int [][] res = new int [filas][columnas];
        Random r = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                res[i][j]=r.nextInt(10);
            }
        }
        return res;

    }
    /**
     * 
     * @param filas
     * @param columnas
     * @param frecuencia se calucla como  una probablilidad de 1/frecuencia
     * @return
     */
    private static boolean[][] randGraph(int filas, int columnas,int frecuencia){
        boolean [][] res=new boolean [filas][columnas];
        Random r = new Random();
        int columna=0;
        for (int i = 1; i < filas; i++) {
            columna=r.nextInt(i);
            res[i][columna]=res[columna][i]=true;
        }

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res.length; j++) {
                if(r.nextInt(frecuencia)==0)
                    res[i][j]=res[j][i]=true;
            }
        }
        
        return res;
        
    }
}
