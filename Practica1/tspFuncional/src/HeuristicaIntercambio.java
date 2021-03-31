import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HeuristicaIntercambio extends Heuristica
{
    /**
     * Declaración de variables
     */
    private ArrayList<Integer> indices;
    private int intercambios;


    @Override
    public void resolver(Problema problema)
    {
        this.problema = problema;

        intercambios = problema.obtenerDimension() * 5000;

        // se genera una colección de rutas aleatorias
        indices = IntStream.rangeClosed(0, problema.obtenerDimension()-1)
                .boxed().collect(Collectors.toCollection(ArrayList::new));

        Ruta[] rutaActual = {new Ruta()};

        rutaOptima = IntStream.range(0,intercambios)
                .mapToObj(rutaNueva -> {
                    rutaActual[0] = intercambiar(indices, rutaActual[0]);
                    return rutaActual[0];
                })
                .min(Comparator.comparing(Ruta::obtenerCoste))
                .get();
    }

    /**
     * Intercambiar dos ciudades aleatorias de la ruta
     * @param indices
     * @return
     */
    private Ruta intercambiar(ArrayList<Integer> indices, Ruta rutaActual)
    {
        Ruta resultado = new Ruta();

        int pos1 = (int) (Math.random() * indices.size());
        int pos2;

        do {
            pos2 = (int) (Math.random() * indices.size());
        } while (pos1 == pos2);

        Collections.swap(indices, pos1, pos2);

        // se van agregando las ciudades en el orden en que
        // aparecen en índices
        resultado.agregarCiudad(problema.obtenerCiudad(indices.get(0)), 0);

        // agregamos el resto de ciudades
        // (no es la mejor forma de usar 'stream' pero para acceder a los valores
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

        if (resultado.obtenerCoste() < rutaActual.obtenerCoste()
                || rutaActual.obtenerCoste() == 0)
            rutaActual = resultado;

        return rutaActual;
    }

}
