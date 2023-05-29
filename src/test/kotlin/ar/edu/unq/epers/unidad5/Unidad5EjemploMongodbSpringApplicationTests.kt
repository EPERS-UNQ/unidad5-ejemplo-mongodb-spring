package ar.edu.unq.epers.unidad5

import ar.edu.unq.epers.unidad5.dto.PrecioPromedio
import ar.edu.unq.epers.unidad5.model.Precio
import ar.edu.unq.epers.unidad5.model.Producto
import ar.edu.unq.epers.unidad5.model.Usuario
import ar.edu.unq.epers.unidad5.model.Zona
import ar.edu.unq.epers.unidad5.service.ProductoService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.function.Consumer
import java.util.stream.IntStream

@SpringBootTest
class Unidad5EjemploMongodbSpringApplicationTests {

    @Autowired
    lateinit var productoService: ProductoService

    @BeforeEach
    fun setup() {
        val usuarios =  listOf(Usuario("Pepe"), Usuario("Juan"), Usuario("Cosme"))
        val zonas = listOf(
            Zona("Bernal", "Saens Peña", "3550", "Argentina"),
            Zona("Quilmes", "Rivadavia", "435", "Argentina"),
            Zona("Ciudad De Buenos Aires", "Callao", "1500", "Argentina")
        )

        val productos = IntStream.range(0, 1000).mapToObj { i: Int ->
            val producto = Producto(i.toString(), "Producto $i", "Marca $i")
            zonas.forEach(Consumer { z: Zona? ->
                usuarios.forEach(
                    Consumer { u: Usuario? ->
                        val precio = Precio(z, u, i + producto.codigo.toInt(), producto)
                        producto.addPrecio(precio)
                    }
                )
            })
            producto
        }
        productoService.saveAll(productos.toList())
    }

    @Test
    fun saveAndGetByCode() {
        val zonaUS = Zona("Amazon St.", "1024", "Dellaware", "USA")
        val zonaUK = Zona("Amazon Rd.", "1024", "London", "UK")
        val user = Usuario("claudio")
        val producto = Producto("0001", "Longboard", "Santa Cruz")
        producto.addPrecio(Precio(zonaUS, user, 78, producto))
        producto.addPrecio(Precio(zonaUK, user, 82, producto))
        productoService.save(producto)
        val producto2 = productoService.getByCode(producto.codigo)
        Assertions.assertEquals("0001", producto2.codigo)
        Assertions.assertEquals("Longboard", producto2.nombre)
        Assertions.assertEquals("Santa Cruz", producto2.marca)
        Assertions.assertEquals(2, producto2.precios.size.toLong())
    }

    @Test
    fun findByMarca() {
        val productos = productoService.findByBrand("Marca 200")
        Assertions.assertEquals(1, productos.size.toLong())
        val producto = productos[0]
        Assertions.assertEquals("200", producto.codigo)
        Assertions.assertEquals("Producto 200", producto.nombre)
        Assertions.assertEquals("Marca 200", producto.marca)
    }

    @Test
    fun findByPrecio() {
        var productos = productoService.findByPrice(446)
        Assertions.assertEquals(
            1,
            productos.size.toLong(),
            "Todos los productos deben tener algún precio igual a 446",
        )
        productos = productoService.findByPrice(0)
        Assertions.assertEquals(
            1,
            productos.size.toLong(),
            "Solo el primer producto debe tener precios menores a 222",
        )
    }

    @Test
    fun findByPriceRange() {
        val productos = productoService.findByPriceRange(3, 5)
        Assertions.assertEquals(1, productos.size)
    }

    @Test
    fun testPrecioPromedio() {
        val precios: List<PrecioPromedio> = productoService.getPrecioPromedio(listOf("121", "558"))

        Assertions.assertEquals("558", precios[0].codigo)
        Assertions.assertEquals(1116.0, precios[0].value, 0.toDouble())
        Assertions.assertEquals("121", precios[1].codigo)
        Assertions.assertEquals(242.0, precios[1].value, 0.toDouble())
    }


    @AfterEach
    fun deleteAll(){
        productoService.deleteAll()
    }

}
