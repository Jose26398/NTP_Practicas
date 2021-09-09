import arbolesBinarios.{ArbolBinario, Nodo}
import org.scalatest.funsuite.AnyFunSuite

class ArbolBinarioTest extends AnyFunSuite{

    /** Creamos un árbol binario con el que realizar pruebas
     *  A continuación mostramos representación visual de nuestro árbol:
     *
     *            6
     *           / \
     *         /    \
     *        4      8
     *      /  \    / \
     *     3    5  7   9
     */

    val raiz = new Nodo(6)
    val arbol = new ArbolBinario(raiz)

    arbol.aniadirNodo(4)
    arbol.aniadirNodo(8)
    arbol.aniadirNodo(3)
    arbol.aniadirNodo(5)
    arbol.aniadirNodo(7)
    arbol.aniadirNodo(9)

    // Prueba 1: cálculo del tamaño del árbol
    test("Tamaño del árbol") {
        printf("Tamaño del árbol: %d", arbol.tamanio)
        assert(arbol.tamanio === 7)
    }

    // Prueba 2: cálculo del número de hojas
    test("Número de hojas") {
        printf("Número de hojas: %d\n", arbol.numeroHojas())
        assert(arbol.numeroHojas() === 4)
    }

    // Prueba 3: suma de los valores almacenados en las hojas
    test("Suma de las hojas") {
        printf("Suma de las hojas: %d\n", arbol.sumarHojas())
        assert(arbol.sumarHojas() === 24)
    }

    // Prueba 4: comprueba si el valor está en el árbol
    test("Valor contenido") {
        printf("El 5 se encuentra en el árbol: %b\n", arbol.contiene(5))
        assert(arbol.contiene(5) === true)
    }

}
