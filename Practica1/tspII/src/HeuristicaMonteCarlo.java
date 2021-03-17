import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase para heurística MonteCarlo
 */
public class HeuristicaMonteCarlo implements HeuristicaTSP{
    // se asigna el problema
    private Problema problema;

    // dato miembro para guardar el número de muestras
    private int muestras;

    // dato miembro auxiliar para guardar soluciones aleatorias
    private ArrayList<Integer> indices;

    /**
     * Método de resolución a partir del problema
     * @param problema
     */
    @Override
    public Ruta resolver(Problema problema) {
        Ruta rutaOptima;
        this.problema = problema;

        // asignar el número de muestras a generar
        muestras = problema.obtenerDimension() * 100;

        // se genera el array de índices
        indices = new ArrayList<>();
        for (int i = 0; i < problema.obtenerDimension(); i++) {
            indices.add(i);
        }

        // se genera solución aleatoria
        rutaOptima = generarAleatoria();

        // consideración de las muestras indicadas
        for (int i = 0; i < muestras; i++) {
            Ruta aleatoria = generarAleatoria();

            // comprobar si hay que actualizar la óptima
            if (rutaOptima.obtenerCoste() > aleatoria.obtenerCoste()){
                rutaOptima = aleatoria;
            }
        }

        // se devuelve la ruta óptima
        return rutaOptima;
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
        for (int i = 1; i < indices.size(); i++) {
            Ciudad previa = problema.obtenerCiudad(indices.get(i-1));
            Ciudad siguiente = problema.obtenerCiudad(indices.get(i));
            double distancia = problema.obtenerDistancia(previa, siguiente);
            resultado.agregarCiudad(siguiente, distancia);
        }

        // se agrega el coste de cierre
        Ciudad inicio = problema.obtenerCiudad(indices.get(0));
        Ciudad fin = problema.obtenerCiudad(indices.get(indices.size()-1));
        double distanciaCierre = problema.obtenerDistancia(inicio, fin);
        resultado.agregarCoste(distanciaCierre);

        // se devuelve el resultado
        return resultado;
    }
}

