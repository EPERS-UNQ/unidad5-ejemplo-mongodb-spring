package ar.edu.unq.epers.unidad5.dao;

import ar.edu.unq.epers.unidad5.model.Item;
import ar.edu.unq.epers.unidad5.model.Personaje;
import ar.edu.unq.epers.unidad5.model.PesoPromedio;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PersonajeDAO extends MongoRepository<Personaje, String> {

    @Query("{nombre:'?0'}")
    List<Personaje> recuperarPorNombre(String nombre);

    @Query("{vida: ?0}")
    List<Personaje> recuperarPorVida(int vida);

    @Query("{vida: { $gt: ?0, $lt: ?1} }")
    List<Personaje> recuperarPorRangoDeVida(int min, int max);

    @Aggregation(pipeline = {
            "{ $match: { nombre: '?0' }}",
            "{ $unwind: '$inventario' }",
            "{ $replaceRoot: { newRoot: '$inventario' } }",
            "{ $sort: {'peso': -1  }}",
    })
    List<Item> recuperarItemsDePersonaje(String nombre);

    @Aggregation(pipeline = {
            "{ $match: { nombre: { $in: ?0 }}}",
            "{ $unwind: '$inventario' }",
            "{ $project: { 'nombre': 1, 'inventario.peso': 1 }}",
            "{ $group: { _id: '$nombre', 'valor': { $avg: '$inventario.peso' }}}",
            "{ $sort: { '_id': -1 }}"
    })
    List<PesoPromedio> obtenerPesoPromedio(List<String> nombres);
}
