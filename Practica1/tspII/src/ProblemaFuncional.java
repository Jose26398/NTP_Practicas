import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ProblemaFuncional {
    /**
     * Coleccion de ciudades del problema
     */
    private ArrayList<Ciudad> ciudades;

    /**
     * Matriz de distancias
     */
    private double[][] distancias;

    /**
     * Constructor para leer datos de archivo
     * @param nombreFichero
     */
    public ProblemaFuncional(String nombreFichero) {
        // creo objeto para ciudades
        ciudades = new ArrayList<>();

        // procesamiento del archivo
        try (Stream<String> lines = Files.lines(Path.of(nombreFichero))) {

            ciudades = lines.skip(1)
                    .map(x -> new Ciudad(
                                    x.split("\\s+")[0],
                                    Double.parseDouble(x.split("\\s+")[1]),
                                    Double.parseDouble(x.split("\\s+")[2])
                            )
                    )
                    .collect(Collectors.toCollection(ArrayList::new));

            calcularDistanciasFuncional();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Metodo de calculo de ciudades
     */
    private void calcularDistanciasFuncional(){
        distancias = ciudades.stream()
                .map(ciudad1 -> ciudades.stream()
                        .mapToDouble(ciudad2 -> Utilidades.calcularDistanciaEuclidea(ciudad1, ciudad2))
                        .toArray())
                .toArray(double[][]::new);
    }


}
