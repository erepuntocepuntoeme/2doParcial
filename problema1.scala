object problema1 {
  def main(args: Array[String]): Unit = {
    println(corregirError(10.0)) 
    println(procesarTemperatura(20)) 
    println(procesarTemperatura(30)) 
    println(procesarTemperatura(10)) 
  }
  def crearTransformacion(factor: Double, desplazamiento: Double): Double => Double = {
      (x: Double) => factor * x + desplazamiento
    }
    val corregirError: Double => Double = crearTransformacion(1.0, -1.5)
    val calibrarSensor: Double => Double = crearTransformacion(1.04, 0.0)
    val convertirFahrenheit: Double => Double = crearTransformacion(1.8, 32.0)
    val procesarTemperatura: Double => Double = 
      corregirError.andThen(calibrarSensor).andThen(convertirFahrenheit)
}

