package ar.edu.unq.epers.unidad5.model

import ar.edu.unq.epers.unidad5.model.exception.MuchoPesoException
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Personaje {
    @Id
    var id: String? = null
    var nombre: String = ""
    var vida: Int = 0
    var pesoMaximo: Int = 0
    var inventario: MutableSet<Item> = HashSet()

    val pesoActual: Int
        get() = inventario.sumOf { it.peso }

    constructor(nombre: String, vida: Int, pesoMaximo: Int) {
        this.nombre = nombre
        this.vida = vida
        this.pesoMaximo = pesoMaximo
    }

    fun recoger(item: Item) {
        val pesoActual = this.pesoActual
        if (pesoActual + item.peso > this.pesoMaximo) {
            throw MuchoPesoException(this, item)
        }

        this.inventario.add(item)
    }

}