package unam.ciencias.computoconcurrente.graph;

/**
 * Clase que calcula el diámetro de una gráfica de forma paralela
 */

public class GraphDiameterCalculator {
    
    /**
     * Calcula el diámetro de la gráfica especificada
     * Se asume que la gráfica tiene al menos un vértice y es conexa
     * @param graph es la matriz de adyacencias de la gráfica
     * @param threads es el número de hilos con los que se va a realizar el cálculo
     * @return el valor del diámetro de la gráfica
     */

    public int getDiameter (boolean [] [] graph, int threads) throws InterruptedException {
        if (threads <= 0) throw new IllegalArgumentException ();
        GraphTraverser.graph = graph;
        GraphTraverser.monitor = new EccentricityMonitor ();

        // Crear los hilos e iniciar su ejecución
        Thread [] traversers = new Thread [threads];
        int length = 1 + (graph.length - 1) / threads;
        int index = 0;
        int from = 0;
        int to = length - 1;
        while (to < graph.length) {
            traversers [index] = new Thread (new GraphTraverser (from, to));
            traversers [index].start ();
            from = to + 1;
            to += length;
            index ++;
        }
        if (from < graph.length) {
            traversers [index] = new Thread (new GraphTraverser (from, graph.length - 1));
            traversers [index].start ();
            index ++;
        }

        // Esperar a que todos los hilos terminen
        while (index > 0) {
            index --;
            traversers [index].join ();
        }

        // El diámetro de la gráfica es la excentricidad máxima de sus vértices
        return GraphTraverser.monitor.getMaxEccentricity ();
    }

}
