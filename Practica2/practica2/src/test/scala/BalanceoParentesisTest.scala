import org.scalacheck
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object BalanceoParentesisTest extends Properties("Balanceo") {
    // Generacion de cadenas de longitud n: forma de uso strGen(10) para cadenas
    // de 10 caracteres
    val strGen =
        (n: Int) =>
            Gen.listOfN(n, Gen.oneOf('(',')',Gen.alphaChar.sample.get)).
            map(_.mkString)

    property("Balance de cadenas") = {
        forAll(strGen(Gen.choose(1,20).sample.get)) {
            cadena => {
                val condicion = BalanceoParentesis.chequearBalance(cadena.toList)
                var global = true
                for(i <- 2 until cadena.length) {
                    val substring=cadena.substring(0,i)
                    val openCount=substring.count(c => c == '(')
                    val closeCount=substring.count(c => c == ')')
                    global = global && ((openCount-closeCount) >= 0)
                    }

                // si se cumple la condicion, entonces global debe
                // ser true
                if (condicion) global
                // en caso de no cumplirse, la condicion global puede
                // ser positiva o negativa....
                else true
                }
            }
        }
}