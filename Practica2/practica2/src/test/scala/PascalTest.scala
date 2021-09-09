import org.scalatest.funsuite.AnyFunSuite

class PascalTest extends AnyFunSuite {

    // se asigna la funcion a usar, para el caso en que haya varias variantes
    val funcion = Pascal.calcularValorTrianguloPascal(_, _)

    // Prueba 1: se calcula el valor de la columna 0 y fila 2
    test("trianguloPascal: fila=2, columna=0") {
        assert(funcion(2,0) === 1)
    }

    // Prueba 2: calculo del valor de columna 1 y fila 2
    test("trianguloPascal: fila=2, columna=1") {
        assert(funcion(2,1) === 2)
    }

    // Prueba 3: calculo de valor de columna 1, fila 3
    test("trianguloPascal: fila=3, columna=1") {
        assert(funcion(3,1) === 3)
    }

    // Prueba 4: calculo de valor de columna 5, fila 10
    test("trianguloPascal: fila=10, columna=5") {
        assert(funcion(10,5) === 252)
    }

    // Prueba 5: calculo del valor de columna 10 y fila 15
    test("trianguloPascal: fila=15, columna=10") {
        assert(funcion(15, 10) === 3003)
    }

    // Prueba 5: calculo del valor de columna 0 y fila 0
    test("trianguloPascal: fila=0, columna=0") {
        assert(funcion(0,0) === 1)
    }

}

