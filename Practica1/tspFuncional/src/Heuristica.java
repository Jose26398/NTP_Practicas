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
     */
    public Heuristica()
    {
        rutaOptima = new Ruta();
    }

    /**
     * Método de resolución del problema
     * @return
     */
    public abstract void resolver(Problema problema);

    /**
     * Devuelve la ruta optima
     * @return
     */
    public Ruta obtenerOptima(){
        return rutaOptima;
    }
}
