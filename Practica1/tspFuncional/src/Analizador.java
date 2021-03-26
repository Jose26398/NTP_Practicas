/**
 * Análisis de recorridos vía TSP
 */
public class Analizador
{
    // dato miembro con la heurística a usar
    private Heuristica heuristica;

    /**
     * Metodo para obtener la heurística a usar
     * @param heuristica
     */
    public void asignarHeuristica(Heuristica heuristica)
    {
        this.heuristica = heuristica;
    }

    /**
     * Método main para pruebas
     * @param args
     */
    public static void main(String args[])
    {
        String nombreArchivo = "small10.tsp";
        // se crea el problema
        Problema problema = new Problema("./data/" + nombreArchivo);
        // se genera la heurística
        HeuristicaVMC heuristicaVMC = new HeuristicaVMC(problema);

        // se crea el analizador
        Analizador analizador = new Analizador();
        // se asigna la heurística
        analizador.asignarHeuristica(heuristicaVMC);
        //se produce el resultado del problema
        Ruta rutaOptimaVMC = analizador.heuristica.resolver();


        // se visualiza la ruta
        Visualizador visualizador = new Visualizador(nombreArchivo, rutaOptimaVMC);
    }
}
