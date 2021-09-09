import org.scalatest.funsuite.AnyFunSuite

class SeriesTest extends AnyFunSuite {

    // se asigna la funcion a usar, para el caso en que haya varias variantes
    val funcion = Series.serie(_, _, _, _, _)

    // Prueba 1: calculo del valor tras 5 repeticiones
    test("sucesionFibonacci: l0=0, l1=1") {
        assert(funcion(5, 0, 1, 1, 1) === 8)
    }

    // Prueba 2: calculo del valor tras 5 repeticiones
    test("sucesionLucas: l0=2, l1=1\"") {
        assert(funcion(5, 2, 1, 1, 1) === 18)
    }

    // Prueba 3: calculo del valor tras 5 repeticiones
    test("sucesionPell: l0=2, l1=6\"") {
        assert(funcion(5, 2, 6, 2, 1) === 148)
    }

    // Prueba 4: calculo del valor tras 5 repeticiones
    test("sucesionPell-Lucas: l0=2, l1=2\"") {
        assert(funcion(5, 2, 2, 2, 1) === 64)
    }

    // Prueba 5: calculo del valor tras 5 repeticiones
    test("sucesionJacobsthal: l0=0 , l1=1\"") {
        assert(funcion(5, 0, 1, 1, 2) === 120)
    }
}
