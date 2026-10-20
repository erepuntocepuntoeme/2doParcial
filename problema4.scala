
trait BebidasIlimitadas {
  def mostrarBebidas(): String = "Las bebidas son ilimitadas"
}

abstract class Servicio(
    val categoria: String,
    val fotografia: String,
    val sonido: Boolean,
    val decoracion: String,
    val presupuesto: Double,
    val tipoDeMusica: String, 
    val disfraz: Boolean
) {
    def fotografiaAlInicio(): String = 
        s"Tiraremo foto al inicio. Temática: $fotografia"
    
    def altaMusica(): String = 
        s"Música encendida. Elegiste: $tipoDeMusica"
    
   
    def incendiarLaCasa(): String
    def queMeHaceEspecial(): String

    
    def + (otro: Servicio): Servicio = {
        new MegaEvento(
            categoria = "Fusión: " + this.categoria + " y " + otro.categoria,
            fotografia = "GO Sesión doble",
            sonido = this.sonido || otro.sonido,
            decoracion = this.decoracion + " + " + otro.decoracion,
            presupuesto = this.presupuesto + otro.presupuesto,
            tipoDeMusica = this.tipoDeMusica + " / " + otro.tipoDeMusica,
            disfraz = this.disfraz || otro.disfraz
        )
    }
}


class Gala(
    val tipodeChampagne: String,
    categoria: String,
    fotografia: String,
    sonido: Boolean,
    decoracion: String,
    presupuesto: Double,
    tipoDeMusica: String
) extends Servicio(categoria, fotografia, sonido, decoracion, presupuesto, tipoDeMusica, false) with BebidasIlimitadas {
    
    def incendiarLaCasa(): String = "Esta vez seremos personas decentes"
    def queMeHaceEspecial(): String = s"Gala elegante con champagne $tipodeChampagne."
}

class FiestaHalloween(
    categoria: String,
    fotografia: String,
    sonido: Boolean,
    decoracion: String,
    presupuesto: Double,
    tipoDeMusica: String,
    val nivelDeDestruccion: Int
) extends Servicio(categoria, fotografia, sonido, decoracion, presupuesto, tipoDeMusica, true) with BebidasIlimitadas {
    
    def incendiarLaCasa(): String = "Vamos a incendiar la casa entera, la calle, la zona"
    def queMeHaceEspecial(): String = "La fiesta sera destructiva a otro nivel."
}

class MegaEvento(
    categoria: String, fotografia: String, sonido: Boolean, decoracion: String, presupuesto: Double, tipoDeMusica: String, disfraz: Boolean
) extends Servicio(categoria, fotografia, sonido, decoracion, presupuesto, tipoDeMusica, disfraz) {
    def incendiarLaCasa(): String = "Depende de la temática..."
    def queMeHaceEspecial(): String = "Es la combinación definitiva de eventos."
}


object Gala {
    def crear(tipodeChampagne: String, presupuesto: Double): Gala = {
        if presupuesto < 5000 then {
            println(" Alerta: Una gala con menos de 5000 de presupuesto no es válida. Ajustando a 5000.")
            new Gala(tipodeChampagne, "Gala", "Estudio", true, "Elegante", 5000, "Clásica")
        } else {
            new Gala(tipodeChampagne, "Gala", "Estudio", true, "Elegante", presupuesto, "Clásica")
        }
    }
}

object FiestaHalloween {
    def crear(presupuesto: Double, nivelDeDestruccion: Int): FiestaHalloween = {
        if nivelDeDestruccion > 10 then {
            println("OJO OJO OJO : Nivel de destrucción exagerado. Reduciendo a 10.")
            new FiestaHalloween("Halloween", "Terror", true, "Spooky", presupuesto, "Rock", 10)
        } else {
            new FiestaHalloween("Halloween", "Terror", true, "Spooky", presupuesto, "regueton champange", nivelDeDestruccion)
        }
    }
}

object SistemaEventos {
    def main(args: Array[String]): Unit = {
        
        val miGala = Gala.crear("Moet", 2000) 
        val miHalloween = FiestaHalloween.crear(3000, 15)

        println(s"Categoría: ${miGala.categoria}, Presupuesto: ${miGala.presupuesto}")
        println(s"Categoría: ${miHalloween.categoria}, Nivel Destrucción: ${miHalloween.nivelDeDestruccion}")

        println(miGala.mostrarBebidas())
        println(miHalloween.mostrarBebidas())

        val megaEvento = miGala + miHalloween
        
        println(s"Nuevo Evento: ${megaEvento.categoria}")
        println(s"Presupuesto total combinado: ${megaEvento.presupuesto}")
        println(s"Música: ${megaEvento.tipoDeMusica}")
        
        println(s"El presupuesto original de la Gala sigue siendo: ${miGala.presupuesto}")
    }
}