package ar.edu.unq.epers.unidad5

import ar.edu.unq.epers.unidad5.dao.ProductoDAO
import ar.edu.unq.epers.unidad5.model.Precio
import ar.edu.unq.epers.unidad5.model.Producto
import ar.edu.unq.epers.unidad5.model.Usuario
import ar.edu.unq.epers.unidad5.model.Zona
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
    lateinit var productoDao: ProductoDAO

    @BeforeEach
    fun setup() {
        val usuarios =  listOf(Usuario("Pepe"), Usuario("Juan"), Usuario("Cosme"))
        val zonas = listOf(
            Zona("Bernal", "Saens Peña", "3550", "Argentina"),
            Zona("Quilmes", "Rivadavia", "435", "Argentina"),
            Zona("Ciudad De Buenos Aires", "Callao", "1500", "Argentina")
        )
        val productos = listOf(
            Producto(CODIGO_PRODUCTO_1, "Triple de chocolate", "Capitan del Espacio"),
            Producto(CODIGO_PRODUCTO_2, "Dulce de leche 500 grs", "La Serenisima"),
            Producto(CODIGO_PRODUCTO_3, "Sprite 2.25lts", "Cocacola"),
            Producto(CODIGO_PRODUCTO_4, "Express x3", "Terrabusi")
        )
        IntStream.range(0, 2).forEach { i: Int ->
            productos.forEach(Consumer { p: Producto ->
                zonas.forEach(Consumer { z: Zona? ->
                    usuarios.forEach(
                        Consumer { u: Usuario? ->
                            val precio = Precio(z, u, i + p.codigo.toInt(), p)
                            p.addPrecio(precio)
                        }
                    )
                })
            })
        }
        productoDao.saveAll(productos)
    }

    @Test
    fun findByMarca() {
        val productos = productoDao.findByBrand("Terrabusi")
        Assertions.assertEquals(1, productos.size.toLong())
        val producto = productos[0]
        Assertions.assertEquals("444", producto!!.codigo)
        Assertions.assertEquals("Express x3", producto.nombre)
        Assertions.assertEquals("Terrabusi", producto.marca)
    }

    @AfterEach
    fun deleteAll(){
        productoDao.deleteAll()
    }

    companion object {
        private const val CODIGO_PRODUCTO_1 = "111"
        private const val CODIGO_PRODUCTO_2 = "222"
        private const val CODIGO_PRODUCTO_3 = "333"
        private const val CODIGO_PRODUCTO_4 = "444"
    }

}
