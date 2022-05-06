package unam.ciencias.computoconcurrente.graph;

/**
 * Clase que representa un monitor que sincroniza el acceso a la variable compartida maxEccentricity
 */

public class EccentricityMonitor {

    private int maxEccentricity; // El valor de la máxima excentricidad encontrada hasta el momento

    /**
     * Actualiza el valor de la máxima excentricidad cuando un hilo calcula la excentricidad de un nuevo vértice
     * Solo un hilo a la vez puede actualizar el máximo para evitar condiciones de competencia
     * @param eccentricity es la excentricidad de un vértice calculada por un hilo
     */

    public synchronized void updateEccentricity (int eccentricity) {
        if (eccentricity > maxEccentricity) maxEccentricity = eccentricity;
    }

    /**
     * Permite obtener el valor actual de la mayor excentricidad de un vértice
     * @return la máxima excentricidad encontrada por los hilos
     */

    public int getMaxEccentricity () {
        return maxEccentricity;
    }

}
