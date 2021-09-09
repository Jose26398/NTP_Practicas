package arbolesBinarios

/**
 * Clase Árbol Binario
 * @param raiz Inicio del árbol
 * @param tamanio Tamaño del árbol
 */
class ArbolBinario(var raiz: Nodo, var tamanio: Int)
{
    /**
     * Contructor del árbol a partir de un nodo raíz
     * @param raiz Inicio del árbol
     */
    def this(raiz: Nodo) = { this(raiz, 1) }


    /**
     * Función para añadir un nuevo Nodo al árbol a partir de
     * un valor numérico
     * @param valor del nuevo nodo a añadir
     */
    def aniadirNodo(valor: Int): Unit =
    {
        def aniadirNodoRecursivo(nodoActual: Nodo, valor: Int): Nodo =
        {
            // Si no existe un hijo ni por izquierda ni por derecha, lo crea
            // y aumentamos el tamaño del árbol
            if (nodoActual == null)
            {
                tamanio += 1
                return new Nodo(valor)
            }

            // Si el valor a insertar es menor que el nodo actual se va por la
            // izquierda del árbol y actualiza el nodo que estamos analizando
            if (valor < nodoActual.valor)
                nodoActual.hijoIzq = aniadirNodoRecursivo(nodoActual.hijoIzq, valor)

            // Si el valor a insertar es mayor que el nodo actual se va por la
            // derecha del árbol y actualiza el nodo que estamos analizando
            if (valor > nodoActual.valor)
                nodoActual.hijoDer = aniadirNodoRecursivo(nodoActual.hijoDer, valor)

            // El valor ya existe
            nodoActual
        }

        // Desencadenar recursividad
        raiz = aniadirNodoRecursivo(raiz, valor)
    }

    /**
     * Función para eliminar un Nodo del árbol a partir de
     * su valor numérico
     * @param valor del nodo a eliminar
     */
    def eliminarNodo(valor: Int): Unit =
    {
        def eliminarNodoRecursivo(nodoInicio: Nodo, valor: Int): Nodo =
        {
            // El nodo a eliminar no existe
            if (nodoInicio == null) return null

            // Ha encontrado el nodo a eliminar
            if (valor == nodoInicio.valor)
            {
                // El nodo no tiene hijos
                if (nodoInicio.hijoIzq == null && nodoInicio.hijoDer == null) return null

                // Si un nodo tiene exactamente un hijo; en el nodo padre,
                // reemplazamos este nodo con su único hijo.
                if (nodoInicio.hijoDer == null) return nodoInicio.hijoIzq
                if (nodoInicio.hijoIzq == null) return nodoInicio.hijoDer

                /**
                 * Función para encontrar el valor más pequeño del árbol
                 * @param nodoRec Nodo que explora el árbol
                 * @return
                 */
                @annotation.tailrec
                def encontrarMasPequenio(nodoRec: Nodo): Int =
                {
                    // Explora los nodos de la izquierda hasta encontrar
                    // el que no tenga hijo izquierdo
                    if (nodoRec.hijoIzq == null) nodoRec.valor
                    else encontrarMasPequenio(nodoRec.hijoIzq)
                }

                // Asignamos el valor más pequeño al nodo a eliminar, y después de eso,
                // lo eliminaremos del subárbol derecho
                val valorMasPequenio = encontrarMasPequenio(nodoInicio.hijoDer)
                nodoInicio.valor = valorMasPequenio
                nodoInicio.hijoDer = eliminarNodoRecursivo(nodoInicio.hijoDer, valorMasPequenio)
            }

            // No ha encontrado el nodo a eliminar y baja por la parte izquierda del
            // árbol (el valor a eliminar es menor que el padre)
            if (valor < nodoInicio.valor)
                nodoInicio.hijoIzq = eliminarNodoRecursivo(nodoInicio.hijoIzq, valor)
            // No ha encontrado el nodo a eliminar y baja por la parte derecha del
            // árbol (el valor a eliminar es mayor que el padre)
            else
                nodoInicio.hijoDer = eliminarNodoRecursivo(nodoInicio.hijoDer, valor)
            nodoInicio
        }
        // Desencadenamos la recursividad
        raiz = eliminarNodoRecursivo(raiz, valor)
    }

    /**
     * Función que cuenta el número de hojas que tiene el árbol
     * @return
     */
    def numeroHojas(): Int =
    {
        def numeroHojasRec(nodoInicio: Nodo): Int =
        {
            // Si no existe el hijo izquierdo/derecho se devuelve 0
            if (nodoInicio == null) return 0

            // Si es un nodo hoja se devuelve 1
            if (nodoInicio.esHoja()) 1
            // Si no es un nodo hoja se devuelve se suman sus hijos
            // (+1 si es hoja y +0 si no existe ese hijo)
            else numeroHojasRec(nodoInicio.hijoIzq) + numeroHojasRec(nodoInicio.hijoDer)
        }
        // Desencadenamos la recursividad
        numeroHojasRec(raiz)
    }

    /**
     * Función que suma todos los valores de las hojas del árbol
     * @return
     */
    def sumarHojas(): Int =
    {
        def sumarHojasRec(nodoInicio: Nodo): Int =
        {
            // // Si no existe el hijo izquierdo/derecho se devuelve 0
            if (nodoInicio == null) return 0

            // Si es un nodo hoja se devuelve en valor de la hoja
            if (nodoInicio.esHoja()) nodoInicio.valor
            // Si no es un nodo hoja se devuelve se suman sus hijos
            // (+valor si es hoja y +0 si no existe ese hijo)
            else sumarHojasRec(nodoInicio.hijoIzq) + sumarHojasRec(nodoInicio.hijoDer)
        }
        // Desencadenamos la recursividad
        sumarHojasRec(raiz)
    }

    /**
     * Función que devuelve un booleano dependiendo de si el valor buscado
     * está en el árbol o no
     * @param aBuscar Valor buscado en el árbol
     * @return
     */
    def contiene(aBuscar: Int): Boolean =
    {
        @annotation.tailrec
        def contieneRecursive(nodoInicio: Nodo, aBuscar: Int): Boolean =
        {
            // Si el valor del nodo que estamos explorando es el mismo
            // que el valor buscado, devulvemos true
            if (aBuscar == nodoInicio.valor) return true
            // Si no lo hemos encontrado devolvemos false
            if (nodoInicio == null) return false

            // Si el valor es menor que el nodo que estamos explorando
            // bajamos por la izquierda del árbol
            if (aBuscar < nodoInicio.valor)
                contieneRecursive(nodoInicio.hijoIzq, aBuscar)
            // Si el valor es mayor que el nodo que estamos explorando
            // bajamos por la derecha del árbol
            else
                contieneRecursive(nodoInicio.hijoDer, aBuscar)
        }
        // Desencadenamos la recursividad
        contieneRecursive(raiz, aBuscar)
    }

    /**
     * Función que realiza un recorrido en profundidad sobre el árbol
     * e imprime la respuesta
     */
    def recorridoProfundidad(): Unit =
    {
        def recorridoProfundidadRec(nodoInicio:Nodo): Unit =
        {
            // Cuando no llega al final de la rama
            if (nodoInicio != null)
            {
                //Empezamos explorando primero los de la izquierda
                recorridoProfundidadRec(nodoInicio.hijoIzq)
                // Cuando llegamos al final imprimimos
                print(" " + nodoInicio.valor)
                // Pasamos a la derecha
                recorridoProfundidadRec(nodoInicio.hijoDer)
            }
        }
        // Desencadenamos la recursividad
        recorridoProfundidadRec(raiz)
    }

    /**
     * Función que realiza un recorrido en anchura sobre el árbol
     * e imprime la respuesta
     */
    def recorridoAnchura(): Unit =
    {
        // Usaremos una lista para mantener los nodos de cada nivel
        // en orden
        var nodos = List(raiz)

        // Mientras que la lista no esté vacía
        while (nodos.nonEmpty)
        {
            // Extraemos cada nodo de la lista
            val node = nodos.head
            nodos = nodos.drop(1)
            // lo imprimimos
            print(" " + node.valor)

            // y agregamos sus hijos a la cola
            if (node.hijoIzq != null)
                nodos = nodos ::: List(node.hijoIzq)
            if (node.hijoDer != null)
                nodos = nodos ::: List(node.hijoDer)
        }
    }

    /**
     * Función que genera un nuevo árbol añadiendo al actual
     * otro árbol distinto
     * @param arbol2 Árbol a añadir
     */
    def unirArboles(arbol2: ArbolBinario): Unit =
    {
        def recorrerArbol(nodoInicio: Nodo): Unit =
        {
            // Si no hemos llegado al final de la rama
            if (nodoInicio != null) {
                // Añadimos el nodo al árbol utilizando la función
                // ya implementada
                aniadirNodo(nodoInicio.valor)

                // Pasamos al siguiente nodo
                recorrerArbol(nodoInicio.hijoIzq)
                recorrerArbol(nodoInicio.hijoDer)
            }
        }
        // Desencadenamos la recursividad
        recorrerArbol(arbol2.raiz)
    }


}
