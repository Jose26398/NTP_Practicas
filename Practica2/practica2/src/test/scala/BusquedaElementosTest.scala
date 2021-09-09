import org.scalatest.funsuite.AnyFunSuite

class BusquedaElementosTest extends AnyFunSuite{

    // lista de numeros proporcionada
    val lista = List(0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610)

    // se asigna la funcion a usar, para el caso en que haya varias variantes
    val funcionBBinaria = BusquedaElementos.busquedaBinaria(lista, 55)(_>_)
    val funcionBSaltos = BusquedaElementos.busquedaASaltos(lista, 55)(_<_)

    // Prueba 1: se busca el valor 55 con busqueda binaria
    test("busquedaBinaria: valor=55") {
        assert(funcionBBinaria === 10)
    }

    // Prueba 2: se busca el valor 55 con busqueda a saltos
    test("busquedaASaltos: valor=55") {
        assert(funcionBSaltos === 10)
    }

}
