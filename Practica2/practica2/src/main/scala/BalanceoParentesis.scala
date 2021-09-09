object BalanceoParentesis {

    /**
     * Funcion para chequear que los parentesis estan balanceados en
     * una cadena de texto
     * @param cadena
     * @return
     */
    def chequearBalance(cadena: List[Char]): Boolean = {
        @annotation.tailrec
        def chequearBalanceTR(cadena: List[Char], contador: Int): Boolean = {

            // si la cadena esta vacia
            if (cadena.isEmpty) contador == 0
            else {
                // cuando se abre un parentesis se checkean los siguientes
                if (cadena.head == '(') chequearBalanceTR(cadena.tail, contador+1)
                else {
                    // checkear cuando se cierran y su contador
                    if (cadena.head == ')') contador > 0 && chequearBalanceTR(cadena.tail, contador-1)
                    else chequearBalanceTR(cadena.tail, contador)
                }
            }
        }

        // desencadenar recursividad
        chequearBalanceTR(cadena,0)
    }

    /**
     * Metodo main: en realidad no es necesario porque el desarrollo
     * deberia guiarse por los tests de prueba
     *
     * @param args
     */
    def main(args: Array[String]): Unit = {
        print( chequearBalance("(if (a > b) (b/a) else (a/(b*b)))".toList) )
    }

}
