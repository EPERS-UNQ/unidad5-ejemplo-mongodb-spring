package ar.edu.unq.epers.unidad5.service

import ar.edu.unq.epers.unidad5.dao.ProductoDAO
import ar.edu.unq.epers.unidad5.model.PrecioPromedio
import ar.edu.unq.epers.unidad5.model.Producto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductoServiceImpl: ProductoService {

    @Autowired
    lateinit var productoDAO: ProductoDAO

    override fun save(producto: Producto): Producto {
        return productoDAO.save(producto)
    }

    override fun saveAll(productos: List<Producto>) {
        productoDAO.saveAll(productos)
    }

    override fun findByBrand(marca: String): List<Producto> {
        return productoDAO.findByBrand(marca)
    }

    override fun getByCode(code: String): Producto {
        return productoDAO.getByCode(code)
    }

    override fun findByPrice(precio: Int): List<Producto> {
        return productoDAO.findByPrice(precio)
    }

    override fun findByPriceRange(min: Int, max: Int): List<Producto> {
        return productoDAO.findByPriceRange(min, max)
    }

    override fun getPrecioPromedio(codigos: List<String>): List<PrecioPromedio> {
        return productoDAO.getPrecioPromedio(codigos)
    }

    override fun deleteAll() {
        productoDAO.deleteAll()
    }

}