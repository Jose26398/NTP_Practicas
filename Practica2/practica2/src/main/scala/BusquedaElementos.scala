import org.scalameter.{Key, Warmer, config}

import scala.util.Random

object BusquedaElementos {
    // se define la configuracion estandar de las pruebas
    val configuracion = config(
        Key.exec.maxWarmupRuns := 10,
        Key.exec.minWarmupRuns := 5,
        Key.exec.benchRuns     := 10,
        Key.verbose            := true
    ) withWarmer(new Warmer.Default)

    /**
     * Funcion que ejecuta la busqueda binaria sobre un array de valores
     * mediante recursividad TR
     * @param coleccion
     * @param aBuscar
     * @param criterio
     * @tparam A
     * @return
     */
    def busquedaBinaria[A](coleccion: List[A], aBuscar: A)(criterio: (A,A) => Boolean): Int = {
        @annotation.tailrec
        def busquedaBinariaTR(coleccion: List[A], aBuscar: A, izq: Int, der: Int): Int = {
            // el elemento no esta
            if (izq > der) return -1

            val mitad = izq + (der - izq) / 2

            // se checkea si esta en la primera mitad del array
            // en la segunda, o es exactamente el elemento de mitad
            if (criterio(coleccion(mitad), aBuscar)) {
                busquedaBinariaTR(coleccion, aBuscar, izq, mitad - 1)
            } else if (coleccion(mitad) == aBuscar) {
                mitad
            } else {
                busquedaBinariaTR(coleccion, aBuscar, mitad + 1, der)
            }
        }

        // desencadenar recursividad
        busquedaBinariaTR(coleccion, aBuscar, 0, coleccion.length - 1)
    }

    /**
     * Funcion que ejecuta la busqueda a saltos generica sobre un array de valores
     * @param coleccion
     * @param aBuscar
     * @param criterio
     * @tparam A
     * @return
     */
    def busquedaASaltos[A](coleccion: List[A], aBuscar: A)(criterio: (A,A) => Boolean): Int = {
        // tamanio del bloque (raiz de la longitud del array)
        var bloque = Math.floor(Math.sqrt(coleccion.length)).toInt

        var prev = 0
        // se compara el elemento buscado con el final del bloque
        // salta de bloque hasta que el ultimo valor sea mayor
        while ( criterio(coleccion(Math.min(bloque,coleccion.length) - 1), aBuscar) ) {
            prev = bloque
            bloque *= 2
            if (prev >= coleccion.length) return -1
        }

        // Cuando encuentra el bloque en el que esta, hace una busqueda lineal
        while ( criterio(coleccion(prev), aBuscar) ) {
            prev += 1
            // si no lo ha encontrado
            if (prev == Math.min(bloque, coleccion.length)) return -1
        }
        // devuelve el elemento si lo encuentra
        if (coleccion(prev) == aBuscar) return prev
        -1
    }


    /**
     * Metodo main: en realidad no es necesario porque el desarrollo
     * deberia guiarse por los tests de prueba
     *
     * @param args
     */
    def main(args: Array[String]) {
        // lista de numeros proporcionada
        val lista = List(0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610)

        // genero un numero aleatorio entre el primero valor y el ultimo de la lista
        // (quizas seria mejor sacar un numero aleatorio de la lista, porque asi hay
        // muy pocas posibilidades de que salga uno que este)
        val generador = new Random
        val aleatorio = generador.nextInt(610)

        val tiempoBusquedaBinaria = configuracion measure busquedaBinaria(lista, aleatorio)(_ > _)

        val tiempoBusquedaASaltos = configuracion measure busquedaASaltos(lista, aleatorio)(_ < _)

        println("\n(BUSQUEDA BINARIA) El elemento está en la posición "
            + busquedaBinaria(lista, aleatorio)(_ > _)
            + " del vector")

        println("(BUSQUEDA A SALTOS) El elemento está en la posición "
            + busquedaASaltos(lista, aleatorio)(_ < _)
            + " del vector")

        println("\ntiempo busqueda binaria: " + tiempoBusquedaBinaria)
        println("tiempo busqueda a saltos: " + tiempoBusquedaASaltos)
    }

}
