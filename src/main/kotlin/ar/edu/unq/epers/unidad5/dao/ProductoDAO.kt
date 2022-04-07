package ar.edu.unq.epers.unidad5.dao

import ar.edu.unq.epers.unidad5.model.Producto
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface ProductoDAO : MongoRepository<Producto, String>{

    @Query("{marca:'?0'}")
    fun findByBrand(marca: String): List<Producto>

    @Query("{codigo:'?0'}")
    fun getByCode(code: String): Producto

    @Query("{precios.precio:'?0'}")
    fun findByPrice(precio: Int): List<Producto>

    @Query("{ precios.precio: { \$gt: ?0, \$lt: ?1} }")
    fun findByPriceRange(min: Int, max: Int): List<Producto>


//    fun getPrecioPromedio(codigos: List<String>): List<PrecioPromedio> {
//        // match:   primero selecciona los items
//        // unwind:  luego convierte una lista de n productos con m precios cada uno en una lista de n*m productos con 1 precio cada uno
//        // project:  solo se queda con el codigo y el precios.precio de cada producto
//        // group:    agrupa por codigo (recordemos que despues del unwind codigo no es unico sino hay m productos con el mismo codigo
//        //                y diferentes precios).  Dentro de cada grupo calcula el promedio del campo precios.precio
//        // sort:       ordena el resultado por _id (codigo) de forma ascendiente
//
//        val match = Aggregates.match(`in`("codigo", codigos))
//        val unwind = Aggregates.unwind("\$precios")
//        val project = Aggregates.project(Projections.fields(Projections.include("codigo"), Projections.include("precios.precio")))
//        val group = Aggregates.group("\$codigo", Accumulators.avg("value", "\$precios.precio"))
//        val sort = Aggregates.sort(Indexes.descending("value"))
//
//        return aggregate(listOf(match, unwind, project, group, sort), PrecioPromedio::class.java)
//    }
}