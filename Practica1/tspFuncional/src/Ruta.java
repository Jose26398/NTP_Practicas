import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Recorrido entre un conjunto de ciudades
 */
public class Ruta
{
    /**
     * Declaración de variables
     */
    private ArrayList<Ciudad> recorridas;
    private double coste;

    /**
     * Constructor por defecto de la clase
     */
    public Ruta()
    {
        recorridas =  new ArrayList<>();
        coste = 0;
    }

    /**
     * Se agrega ciudad a la ruta
     * @param ciudad
     */
    public void agregarCiudad(Ciudad ciudad, double distancia)
    {
        recorridas.add(ciudad);
        coste += distancia;
    }

    /**
     * Devuelve las ciudades recorridas hasta el momento
     * @return
     */
    public int obtenerLongitud()
    {
        return recorridas.size();
    }

    /**
     * Se devuelve la ciudad inicial
     * @return
     */
    public Ciudad obtenerInicio(){
        return recorridas.get(0);
    }

    /**
     * Devuelve la ciudad final del recorrido
     * @return
     */
    public Ciudad obtenerFin(){
        return recorridas.get(recorridas.size()-1);
    }

    /**
     * Se agrega el coste del recorrido final
     * @param distancia
     */
    public void agregarCoste(double distancia){
        coste += distancia;
    }

    /**
     * Devuelve el coste de la ruta
     * @return
     */
    public double obtenerCoste(){
        return coste;
    }

    /**
     * Verifica si una ciudad ha sido visitada
     * @param ciudad
     * @return
     */
    public boolean visitada(Ciudad ciudad){
        int indice = recorridas.indexOf(ciudad);
        return indice != -1;
    }

    /**
     * Devuelve dos ArrayList con los valores de las coordenadas
     * @return
     */
    public ArrayList<ArrayList<Double>> obtenerCoordenadas(){
        // se crea el array general
        ArrayList<ArrayList<Double>> resultado = new ArrayList<>();

        // se crean los arrays para X e Y
        //resultado.add(new ArrayList<Double>());
        resultado.add(new ArrayList<Double>());
        resultado.add(new ArrayList<Double>());

        recorridas.forEach(
                ciudad -> {
                    resultado.get(0).add(ciudad.obtenerX());
                    resultado.get(1).add(ciudad.obtenerY());
                }
        );

        return resultado;
    }

    /**
     * Devuelve cadena con contenido de la ruta
     * @return
     */
    public String toString(){
        String salida = "Ruta: ";
        salida += recorridas.stream()
                .map(Ciudad::obtenerEtiqueta)
                .collect(Collectors.joining(" "));

        salida += " coste: " + coste + "\n";

        return salida;
    }
}
