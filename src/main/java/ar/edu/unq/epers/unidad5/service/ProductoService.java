package ar.edu.unq.epers.unidad5.service;

import ar.edu.unq.epers.unidad5.model.Producto;
import ar.edu.unq.epers.unidad5.model.PrecioPromedio;
import java.util.List;

public interface ProductoService {
    Producto save(Producto producto);
    void saveAll(List<Producto> productos);
    List<Producto> findByBrand(String marca);
    Producto getByCode(String code);
    List<Producto> findByPrice(int precio);
    List<Producto> findByPriceRange(int min, int max);
    List<PrecioPromedio> getPrecioPromedio(List<String> codigos);
    void deleteAll();
}
