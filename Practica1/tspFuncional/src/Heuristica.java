/**
 * Clase abstracta para el problema del TSP
 */
public abstract class Heuristica
{
    /**
     * Declaración de variables
     */
    protected Problema problema;
    protected Ruta rutaOptima;

    /**
     * Constructor de la clase
     * @param problema
     */
    public Heuristica(Problema problema)
    {
        this.problema = problema;
        rutaOptima = new Ruta();
    }

    /**
     * Método de resolución del problema
     * @return
     */
    public abstract Ruta resolver();
}
