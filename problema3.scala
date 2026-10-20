import scala.annotation.tailrec

object problema3 {

  def procesarRango(
    desde: Int, 
    hasta: Int, 
    transformar: Int => Int, 
    combinar: (Int, Int) => Int, 
    valorInicial: Int
  ): Int = {
    @tailrec
    def aux(actual: Int, acumulador: Int): Int = {
      if actual > hasta then acumulador
      else {
        val valorTransformado = transformar(actual)
        val nuevoAcumulador = combinar(acumulador, valorTransformado)
        aux(actual + 1, nuevoAcumulador)
      }
    }
    aux(desde, valorInicial)
  }

  @tailrec
  def buscar(
    desde: Int, 
    hasta: Int, 
    condicion: Int => Boolean
  ): Int = {
    if desde > hasta then -1
    else if condicion(desde) then desde
    else buscar(desde + 1, hasta, condicion)
  }

  def contarDivisores(numero: Int): Int = {
    @tailrec
    def aux(divisor: Int, contador: Int): Int = {
      if divisor > numero then contador
      else if numero % divisor == 0 then aux(divisor + 1, contador + 1)
      else aux(divisor + 1, contador)
    }
    aux(1, 0)
  }

  def obtenerMaximo(a: Int, b: Int): Int = if a > b then a else b

  def maxDivisoresEnRango(desde: Int, hasta: Int): Int = {
    procesarRango(desde, hasta, contarDivisores, obtenerMaximo, 0)
  }

  def esPrimo(numero: Int): Boolean = {
    if numero < 2 then false
    else {
      @tailrec
      def aux(divisor: Int): Boolean = {
        if divisor * divisor > numero then true
        else if numero % divisor == 0 then false
        else aux(divisor + 1)
      }
      aux(2)
    }
  }

  def primerPrimoEnRango(desde: Int, hasta: Int): Int = {
    buscar(desde, hasta, esPrimo)
  }

  def main(args: Array[String]): Unit = {
    println("Max divisores (5 a 10): " + maxDivisoresEnRango(5, 10)) 
    println("Max divisores (22 a 26): " + maxDivisoresEnRango(22, 26)) 


    println("Primer primo (6 a 11): " + primerPrimoEnRango(6, 11)) 
    println("Primer primo (24 a 28): " + primerPrimoEnRango(24, 28)) 
  }
}
