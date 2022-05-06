package unam.ciencias.computoconcurrente.graph;

import java.util.Arrays;
import java.util.ArrayDeque;

/**
 * Clase que se ejecuta en un hilo, explorando la gráfica con BFS y calculando excentricidades de vértices
 */

public class GraphTraverser implements Runnable {
   
    static boolean [] [] graph;         // Gráfica representada como una matriz de adyacencias
    static EccentricityMonitor monitor; // Monitor que sincroniza el acceso a la variable de la máxima excentricidad

    private int from;                   // Índice del primer vértice para el que este hilo calculará la excentricidad
    private int to;                     // Índice del último vértice para el que este hilo calculará la excentricidad
    private boolean [] visited;         // Arreglo de los vértices visitados durante el recorrido BFS
    private ArrayDeque <Integer> queue; // Cola de vértices usada para el recorrido BFS

    /**
     * Construye un nuevo explorador de la gráfica, que se ejecutará en un hilo
     * @param from es el primer vértice para el que se calculará la excentricidad
     * @param to es el último vértice para el que se calculará la excentricidad
     */

    public GraphTraverser (int from, int to) {
        this.from = from;
        this.to = to;
        visited = new boolean [graph.length];
        queue = new ArrayDeque <> (graph.length << 1);
    }

    /**
     * Calcula la excentricidad de un vértice usando BFS
     * @param vertex es el índice del vértice al cual se le va a calcular su excentricidad
     * @return la excentricidad de vertex
     */

    public int vertexEccentricity (int vertex) {
        int index, distance = 0;
        enqueue (vertex, distance);        // El recorrido BFS se inicializa con vertex en la cola
        while (! queue.isEmpty ()) {       // Ciclo principal de BFS, continúa sacando nuevos vértices mientras la cola no está vacía
            vertex = queue.removeFirst ();
            distance = queue.removeFirst ();
            index = 0;
            while (index < graph.length) { // Ciclo para agregar a todos los vecinos no visitados del último vértice que se sacó de la cola
                if (graph [vertex] [index] && ! visited [index]) enqueue (index, distance + 1);
                index ++;
            }
        }
        Arrays.fill (visited, false);      // Se limpia el arreglo de visitados para que se pueda volver a usar en otro cálculo de excentricidad
        return distance;
    }

    /**
     * Inicia la ejecución del hilo
     * Calcula la excentricidad de todos los vértices asignados a este hilo
     * Cada vez que obtiene la excentricidad de un vértice, actualiza la variable de la máxima excentricidad
     */

    @ Override
    public void run () {
        while (from <= to) {
            monitor.updateEccentricity (vertexEccentricity (from));
            from ++;
        }
    }

    /**
     * Mete a la cola un vértice y lo marca como visitado
     * @param vertex es el índice del vértice que se mete a la cola
     * @param distance es la distancia de ese vértice al vértice inicial donde emepzó el recorrido BFS
     */

    private void enqueue (int vertex, int distance) {
        queue.addLast (vertex);
        queue.addLast (distance);
        visited [vertex] = true;
    }

}
