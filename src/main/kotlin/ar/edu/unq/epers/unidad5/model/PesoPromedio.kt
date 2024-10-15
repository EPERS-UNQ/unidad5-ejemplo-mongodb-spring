package ar.edu.unq.epers.unidad5.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class PesoPromedio {
    @Id
    var nombre: String? = null
    var valor:Double = 0.toDouble()
}