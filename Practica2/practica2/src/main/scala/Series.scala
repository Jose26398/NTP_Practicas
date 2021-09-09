object Series {

    /**
     * Funcion serie tail recursive con los valores iniciales y dos
     * variables que los multiplican, respectivamente
     * @param index
     * @param l_0
     * @param l_1
     * @param x
     * @param y
     * @return
     */
    def serie(index: Int, l_0: Int, l_1: Int, x: Int, y: Int): Int = {
        @annotation.tailrec
        def serieTR(index: Int, l0: Int, l1: Int): Int = {
            // imprimimos los dos elementos y la suma de ellos
            println("%d + %d = %d".format(l0, l1, l0+l1))

            // calculamos recursivamente los siguientes
            if (index <= 0) {
                l1
            } else {
                serieTR(index - 1, l0 = x*l1, l1 = y*(l0+l1))
            }
        }

        // desencadenar recursividad
        serieTR(index, l0 = l_0, l1 = l_1)
    }

    /**
     * Metodo main: en realidad no es necesario porque el desarrollo
     * deberia guiarse por los tests de prueba
     *
     * @param args
     */
    def main(args: Array[String]) {
        println("................... Sucesión de Fibonacci ...................")
        serie(5, 0, 1, 1, 1)

        println("................... Sucesión de Lucas ...................")
        serie(5, 2, 1, 1, 1)

        println("................... Sucesión de Pell ...................")
        serie(5, 2, 6, 2, 1)

        println("................... Sucesión de Pell-Lucas ...................")
        serie(5, 2, 2, 2, 1)

        println("................... Sucesión de Jacobsthal ...................")
        serie(5, 0, 1, 1, 2)
    }
}
