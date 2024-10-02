package ar.edu.unq.epers.unidad5.service;

import ar.edu.unq.epers.unidad5.dao.ProductoDAO;
import ar.edu.unq.epers.unidad5.model.Producto;
import ar.edu.unq.epers.unidad5.model.PrecioPromedio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoDAO productoDAO;

    public ProductoServiceImpl(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public Producto save(Producto producto) {
        return productoDAO.save(producto);
    }

    @Override
    public void saveAll(List<Producto> productos) {
        productoDAO.saveAll(productos);
    }

    @Override
    public List<Producto> findByBrand(String marca) {
        return productoDAO.findByBrand(marca);
    }

    @Override
    public Producto getByCode(String code) {
        return productoDAO.getByCode(code);
    }

    @Override
    public List<Producto> findByPrice(int precio) {
        return productoDAO.findByPrice(precio);
    }

    @Override
    public List<Producto> findByPriceRange(int min, int max) {
        return productoDAO.findByPriceRange(min, max);
    }

    @Override
    public List<PrecioPromedio> getPrecioPromedio(List<String> codigos) {
        return productoDAO.getPrecioPromedio(codigos);
    }

    @Override
    public void deleteAll() {
        productoDAO.deleteAll();
    }
}
