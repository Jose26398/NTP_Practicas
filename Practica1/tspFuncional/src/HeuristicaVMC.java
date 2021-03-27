import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Heurística del vecino más cercano
 */
public class HeuristicaVMC extends Heuristica
{
    /**
     * Resuelve TSP mediante el vecino más cercano
     * @return
     */
    @Override
    public void resolver(Problema problema)
    {
        this.problema = problema;
        ArrayList<Ruta> rutas;

        rutas = problema.obtenerCiudades().stream()
                .map(ciudad -> {
                    Ruta rutaNueva = new Ruta();
                    rutaNueva.agregarCiudad(ciudad, 0);
                    completarRuta(rutaNueva);
                    return rutaNueva;
                })
                .collect(Collectors.toCollection(ArrayList::new));

        rutaOptima = seleccionarRuta(rutas);
    }

    /**
     * Completa la ruta agregando siempre la ciudad mas cercana
     * @param ruta
     */
    private void completarRuta(Ruta ruta){
        // determinar si ya esta completa
        if(ruta.obtenerLongitud() == problema.obtenerDimension()){
            // ruta completa: se agrega la distancia entre ciudad
            // inicial y final
            Ciudad inicio = ruta.obtenerInicio();
            Ciudad fin = ruta.obtenerFin();

            // tenemos que calcular la distancia entre inicio y fin
            double distancia = Utilidades.calcularDistanciaEuclidea(inicio, fin);

            // agregar distancia de fin de recorrido
            ruta.agregarCoste(distancia);
        }
        else{
            // determinar la ciudad mas cercana a la ultima ciudad
            // de la ruta
            Ciudad masCercana = problema.obtenerMasCercana(ruta);

            // se determina el coste de ir desde fin hasta masCercana
            double distancia = Utilidades.calcularDistanciaEuclidea(ruta.obtenerFin(), masCercana);

            // agregar la nueva ciudad a la ruta, con sus distancia
            ruta.agregarCiudad(masCercana, distancia);

            // llamada recursiva a completarRuta
            completarRuta(ruta);
        }
    }

    /**
     * Selecciona de la coleccion la ruta de menor coste
     * @param rutas
     */
    private Ruta seleccionarRuta(ArrayList<Ruta> rutas){
        return rutas.stream()
                .min(Comparator.comparing(Ruta::obtenerCoste))
                .get();
    }
}
