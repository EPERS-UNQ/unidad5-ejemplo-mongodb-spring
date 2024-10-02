package ar.edu.unq.epers.unidad5.dao;

import ar.edu.unq.epers.unidad5.model.Producto;
import ar.edu.unq.epers.unidad5.model.PrecioPromedio;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface ProductoDAO extends MongoRepository<Producto, String> {

    @Query("{marca:'?0'}")
    List<Producto> findByBrand(String marca);

    @Query("{codigo:'?0'}")
    Producto getByCode(String code);

    @Query("{'precios.precio':?0}")
    List<Producto> findByPrice(int precio);

    @Query("{ 'precios.precio': { $gt: ?0, $lt: ?1} }")
    List<Producto> findByPriceRange(int min, int max);

    @Aggregation(pipeline = {
            "{ $match: { codigo: { $in: ?0 }}}",
            "{ $unwind: '$precios' }",
            "{ $project: { 'codigo': 1, 'precios.precio': 1 }}",
            "{ $group: { _id: '$codigo', 'value': { $avg: '$precios.precio' }}}",
            "{ $sort: { 'value': -1 }}"
    })
    List<PrecioPromedio> getPrecioPromedio(List<String> codigos);
}
