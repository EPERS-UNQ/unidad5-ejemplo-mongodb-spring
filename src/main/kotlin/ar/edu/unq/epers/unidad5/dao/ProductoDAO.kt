package ar.edu.unq.epers.unidad5.dao

import ar.edu.unq.epers.unidad5.model.PrecioPromedio
import ar.edu.unq.epers.unidad5.model.Producto
import org.springframework.data.mongodb.repository.Aggregation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface ProductoDAO : MongoRepository<Producto, String> {

    @Query("{marca:'?0'}")
    fun findByBrand(marca: String): List<Producto>

    @Query("{codigo:'?0'}")
    fun getByCode(code: String): Producto

    @Query("{'precios.precio':?0}")
    fun findByPrice(precio: Int): List<Producto>

    @Query("{ 'precios.precio': { \$gt: ?0, \$lt: ?1} }")
    fun findByPriceRange(min: Int, max: Int): List<Producto>

    @Aggregation(pipeline = [
        "{\$match:{codigo: {\$in: ?0 }}}",
        "{\$unwind:\$precios}",
        "{\$project: {'codigo':1, 'precios.precio': 1  }}",
        "{\$group: {_id: '\$codigo', 'value': { \$avg: '\$precios.precio' }  }}",
        "{\$sort: {'value': -1  }}",
    ])
    fun getPrecioPromedio(codigos: List<String>): List<PrecioPromedio>
}