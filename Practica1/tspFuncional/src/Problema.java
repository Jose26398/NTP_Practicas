import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clase Problema. Conjunto de localidades a recorrer
 */
public class Problema {
    /**
     * Declaración de variables
     */
    private ArrayList<Ciudad> ciudades;

    /**
     * Constructor de la clase
     * @param nombreFichero
     */
    public Problema(String nombreFichero)
    {
        // creo objeto para ciudades
        ciudades = new ArrayList<>();

        // procesamiento del archivo
        try (Stream<String> lines = Files.lines(Path.of(nombreFichero))) {

            ciudades = lines.skip(1)
                    .filter(line -> !line.equals(""))
                    .map(x -> new Ciudad(
                                    x.split("\\s+")[0],
                                    Double.parseDouble(x.split("\\s+")[1]),
                                    Double.parseDouble(x.split("\\s+")[2])
                            )
                    )
                    .collect(Collectors.toCollection(ArrayList::new));

        }catch(IOException e){
            System.out.println("Excepcion: " + e);
            System.out.println("Error en apertura del archivo");
            System.exit(-1);
        }
    }

    /**
     * Devuelve el array de ciudades
     * @return
     */
    public ArrayList<Ciudad> obtenerCiudades()
    {
        return ciudades;
    }

    /**
     * Devuelve la ciudad almacenada en una posicion
     * @param indice
     * @return
     */
    public Ciudad obtenerCiudad(int indice){
        return ciudades.get(indice);
    }

    /**
     * Devuelve el numero de ciudades
     * @return
     */
    public int obtenerDimension()
    {
        return ciudades.size();
    }

    /**
     * Devuelve la ciudad mas cercana
     * @param ruta
     * @return
     */
    public Ciudad obtenerMasCercana(Ruta ruta)
    {
        return ciudades.stream()
                .filter(ciudad -> !ruta.visitada(ciudad))
                .min((c1, c2) -> {
                    if (Utilidades.calcularDistanciaEuclidea(c1, ruta.obtenerFin()) <
                            Utilidades.calcularDistanciaEuclidea(c2, ruta.obtenerFin()))
                        return -1;
                    else
                        return 1;
                }).get();
    }

    /**
     * Ofrece una cadena con la informacion del objeto
     * @return
     */
    public String toString()
    {
        String salida = "Dimensión del problema: " + ciudades.size() + "\n";
        salida += "\nCiudades:\n";
        salida += ciudades.stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));

        return salida;
    }

}
