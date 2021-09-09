package arbolesBinarios

object pruebaArboles extends App
{
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

    println("\nRecorrido en profundidad:")
    arbol.recorridoProfundidad()

    println("\n\nRecorrido en anchura:")
    arbol.recorridoAnchura()

    arbol.eliminarNodo(8)

    println("\n\nRecorrido en anchura tras eliminar el 8:")
    arbol.recorridoAnchura()


//    val raiz2 = new Nodo(3)
//    val arbol2 = new ArbolBinario(raiz2)
//
//    arbol2.aniadirNodo(2)
//    arbol2.aniadirNodo(5)
//    arbol2.aniadirNodo(7)
//    arbol2.aniadirNodo(1)
//
//    println("\n\n\nRecorrido en anchura:")
//    arbol2.recorridoAnchura()
//
//    arbol.unirArboles(arbol2)
//    println("\nRecorrido en anchura:")
//    arbol.recorridoAnchura()
}
