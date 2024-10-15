package ar.edu.unq.epers.unidad5.model.exception

import ar.edu.unq.epers.unidad5.model.Item
import ar.edu.unq.epers.unidad5.model.Personaje

class MuchoPesoException(private val personaje: Personaje, private val item: Item) : RuntimeException() {

    override val message: String?
        get() = "El personaje [$personaje] no puede recoger [$item] porque cagar mucho peso ya"

    companion object {

        private val serialVersionUID = 1L
    }

}