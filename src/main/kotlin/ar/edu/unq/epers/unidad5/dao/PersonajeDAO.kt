package ar.edu.unq.epers.unidad5.dao

import ar.edu.unq.epers.unidad5.model.Item
import ar.edu.unq.epers.unidad5.model.Personaje
import ar.edu.unq.epers.unidad5.model.PesoPromedio
import org.springframework.data.mongodb.repository.Aggregation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface PersonajeDAO : MongoRepository<Personaje, String> {

    @Query("{nombre:'?0'}")
    fun recuperarPorNombre(nombre: String): List<Personaje>

    @Query("{vida: ?0}")
    fun recuperarPorVida(vida: Int): List<Personaje>

    @Query("{vida: { \$gt: ?0, \$lt: ?1} }")
    fun recuperarPorRangoDeVida(min: Int, max: Int): List<Personaje>

    @Aggregation(pipeline = [
        "{\$match: {nombre:'?0'}}",
        "{\$unwind:\$inventario}",
        "{\$replaceRoot: { newRoot: '\$inventario' } }",
        "{\$sort: {'peso': -1  }}",
    ])
    fun recuperarItemsDePersonaje(nombre: String): List<Item>

    @Aggregation(pipeline = [
        "{\$match:{nombre: {\$in: ?0 }}}",
        "{\$unwind:\$inventario}",
        "{\$project: {'nombre': 1, 'inventario.peso': 1  }}",
        "{\$group: {_id: '\$nombre', 'valor': { \$avg: '\$inventario.peso' }  }}",
        "{\$sort: {'_id': -1  }}",
    ])
    fun obtenerPesoPromedio(nombres: List<String>): List<PesoPromedio>
}