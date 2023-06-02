package ar.edu.unq.epers.unidad5.service

import ar.edu.unq.epers.unidad5.model.PrecioPromedio
import ar.edu.unq.epers.unidad5.model.Producto

interface ProductoService {
    fun save(producto: Producto): Producto
    fun saveAll(productos: List<Producto>)
    fun findByBrand(marca: String): List<Producto>
    fun getByCode(code: String): Producto
    fun findByPrice(precio: Int): List<Producto>
    fun findByPriceRange(min: Int, max: Int): List<Producto>
    fun getPrecioPromedio(codigos: List<String>): List<PrecioPromedio>
    fun deleteAll()
}