/**
 * Análisis de recorridos vía TSP
 */
public class Analizador {
    // dato miembro con la heurística a usar
    private HeuristicaTSP heuristicaTSP;

    /**
     * Metodo para obtener la heurística a usar
     * @param heuristica
     */
    public void asignarHeuristica(HeuristicaTSP heuristica){
        heuristicaTSP = heuristica;
    }

    /**
     * Metodo para el análisis de un problema
     * @param nombreFichero
     * @return
     */
    public Ruta analizar(String nombreFichero){
        // se crea el problema
        Problema problema = new Problema("./data/" + nombreFichero);

        // se delega el dato miembro a la heurísticaTSP
        return heuristicaTSP.resolver(problema);
    }

    /**
     * Método main para pruebas
     * @param args
     */
    public static void main(String args[]){
        String nombreArchivo = "berlin52.tsp";
        // se genera la heurística
        HeuristicaVMC heuristicaVMC = new HeuristicaVMC();
        HeuristicaMonteCarlo heuristicaMonteCarlo = new HeuristicaMonteCarlo();

        // se crea el analizador
        Analizador analizador = new Analizador();

        // se asigna la heurística
        analizador.asignarHeuristica(heuristicaVMC);

        //se produce el resultado del problema
        Ruta rutaOptimaVMC = analizador.analizar(nombreArchivo);

        // cambiamos la heurística del analizador
        analizador.asignarHeuristica(heuristicaMonteCarlo);

        // se resuelve con la nueva heurística
        Ruta rutaOptimaMonteCarlo = analizador.analizar(nombreArchivo);

        // se visualiza la ruta
        Visualizador visualizador = new Visualizador(nombreArchivo, rutaOptimaVMC,
                rutaOptimaMonteCarlo);
    }
}
