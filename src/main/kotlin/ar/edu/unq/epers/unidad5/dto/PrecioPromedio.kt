package ar.edu.unq.epers.unidad5.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class PrecioPromedio() {
    @Id
    var codigo: String? = null
    var value:Double = 0.toDouble()
}