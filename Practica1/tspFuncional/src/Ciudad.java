/**
 * Clase Ciudad
 */
public class Ciudad
{
    /**
     * Declaración de variables
     */
    private String etiqueta;
    private double x;
    private double y;

    /**
     * Constructor de la clase
     * @param etiqueta
     * @param x
     * @param y
     */
    public Ciudad(String etiqueta, double x, double y)
    {
        this.etiqueta = etiqueta;
        this.x = x;
        this.y = y;
    }

    /**
     * Devuelve la etiqueta
     * @return
     */
    public String obtenerEtiqueta()
    {
        return etiqueta;
    }

    /**
     * Devuelve el valor del dato miembro x
     * @return
     */
    public double obtenerX()
    {
        return x;
    }

    /**
     * Devuelve el valor del dato miembro y
     * @return
     */
    public double obtenerY()
    {
        return y;
    }

    /**
     * Devuelve una cadena de información del objeto
     * @return
     */
    public String toString()
    {
        return "Ciudad: " + etiqueta + "   x: " + x + "   y:" + y + "\n";
    }
}
