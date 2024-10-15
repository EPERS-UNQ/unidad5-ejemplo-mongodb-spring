package ar.edu.unq.epers.unidad5.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Item {
    @Id
    var nombre: String = ""
    var peso: Int = 0
    var obtenibleEn: MutableList<Dungeon> = mutableListOf()

    constructor(nombre: String, peso: Int, obtenibleEn: MutableList<Dungeon>) {
        this.nombre = nombre
        this.peso = peso
        this.obtenibleEn = obtenibleEn
    }

}