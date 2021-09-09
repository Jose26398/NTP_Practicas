object CambioMonedas {

    /**
     * Funcion que imprime una lista de todas combinaciones
     * que se pueden hacer con las monedas disponibles
     * @param cantidad
     * @param monedas
     * @param resultado
     */
    def listarCambiosPosibles(cantidad: Int, monedas: List[Int], resultado: String): Unit = {
        if (cantidad == 0) {
            System.out.println(resultado + " ")
            return
        }
        if (cantidad < 0) return
        for (i <- monedas.indices) {
            if (monedas(i) <= cantidad)
                listarCambiosPosibles(cantidad - monedas(i), monedas, monedas(i) + " " + resultado)
        }
    }


    /**
     * Metodo main: en realidad no es necesario porque el desarrollo
     * deberia guiarse por los tests de prueba
     *
     * @param args
     */
    def main(args: Array[String]) {
        print(listarCambiosPosibles(4, List(1,2), ""))
    }

}
