import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase para heurística MonteCarlo
 */
public class HeuristicaMonteCarlo extends Heuristica
{
    /**
     * Declaración de variables
     */
    private int muestras;
    private ArrayList<Integer> indices;


    /**
     * Método de resolución a partir del problema
     * @return
     */
    @Override
    public void resolver(Problema problema)
    {
        this.problema = problema;

        // asignar el número de muestras a generar
        muestras = problema.obtenerDimension() * 100;

        indices = IntStream.rangeClosed(0, problema.obtenerDimension()-1)
                .boxed().collect(Collectors.toCollection(ArrayList::new));

        rutaOptima = IntStream.range(0, muestras)
                .mapToObj(rutaNueva -> generarAleatoria())
                .min(Comparator.comparing(Ruta::obtenerCoste))
                .get();
    }

    /**
     * Método de generación de rutas aleatorias
     * @return
     */
    private Ruta generarAleatoria(){
        Ruta resultado = new Ruta();

        // se desordena el array de índices
        Collections.shuffle(indices);

        // se van agregando las ciudades en el orden en que
        // aparecen en índices
        resultado.agregarCiudad(problema.obtenerCiudad(indices.get(0)), 0);

        // agregamos el resto de ciudades
        // (no es una forma correcta de usar 'stream' pero para acceder a los valores
        // previo o posterior, no es posible hacerlo usándolos o es muy complejo)
        IntStream.range(1, indices.size()).forEach(i -> {
            Ciudad previa = problema.obtenerCiudad(indices.get(i - 1));
            Ciudad siguiente = problema.obtenerCiudad(indices.get(i));
            double distancia = Utilidades.calcularDistanciaEuclidea(previa, siguiente);
            resultado.agregarCiudad(siguiente, distancia);
        });

        // se agrega el coste de cierre
        Ciudad inicio = problema.obtenerCiudad(indices.get(0));
        Ciudad fin = problema.obtenerCiudad(indices.get(indices.size()-1));
        double distanciaCierre = Utilidades.calcularDistanciaEuclidea(inicio, fin);
        resultado.agregarCoste(distanciaCierre);

        // se devuelve el resultado
        return resultado;
    }
}
