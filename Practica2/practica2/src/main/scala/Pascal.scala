object Pascal {

    /**
     * Funcion para calcular un valor del Triangulo de Pascal
     * @param fila
     * @param columna
     * @return
     */
    def calcularValorTrianguloPascal(fila: Int, columna: Int): Int = {
        if (fila == 0 || columna == 0 || columna == fila) 1
        else
            calcularValorTrianguloPascal(fila - 1, columna - 1) + calcularValorTrianguloPascal(fila - 1, columna)
    }

    /**
     * Metodo main: en realidad no es necesario porque el desarrollo
     * deberia guiarse por los tests de prueba
     *
     * @param args
     */
    def main(args: Array[String]) {
        println("................... Triangulo de Pascal ...................")
        // Se muestran 10 filas del trinagulo de Pascal
        for (row <- 0 to 10) {
            // Se muestran 10 y 10 filas
            for (col <- 0 to row)
                print(calcularValorTrianguloPascal(row, col) + " ")

            // Salto de linea final para mejorar la presentacion
            println()
        }

        // Se muestra el valor que debe ocupar la fila 10 y columna 5
        print(calcularValorTrianguloPascal(10, 5))
    }

}

