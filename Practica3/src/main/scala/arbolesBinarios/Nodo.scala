package arbolesBinarios

/**
 * Clase para definir el tipo Nodo
 * @param valor Valor numérico del nodo
 * @param hijoIzq Hijo izquierdo del nodo
 * @param hijoDer Hijo derecho del nodo
 */
class Nodo(var valor: Int, var hijoIzq: Nodo, var hijoDer: Nodo)
{
    /**
     * Constructor para un Nodo a partir de un entero
     * @param valor
     */
    def this(valor: Int) = { this(valor, null, null) }

    /**
     * Función que devuelve un booleano dependiendo si es un nodo
     * hoja o no
     * @return
     */
    def esHoja(): Boolean = { hijoDer == null && hijoIzq == null }
}
