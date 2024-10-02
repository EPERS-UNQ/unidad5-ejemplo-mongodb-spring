package ar.edu.unq.epers.unidad5;

import ar.edu.unq.epers.unidad5.model.*;
import ar.edu.unq.epers.unidad5.service.ProductoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class Unidad5EjemploMongodbSpringApplicationTests {

    @Autowired private ProductoService productoService;

    @BeforeEach
    public void setup() {
        List<Usuario> usuarios = List.of(new Usuario("Pepe"), new Usuario("Juan"), new Usuario("Cosme"));
        List<Zona> zonas = List.of(
                new Zona("Bernal", "Saens Peña", "3550", "Argentina"),
                new Zona("Quilmes", "Rivadavia", "435", "Argentina"),
                new Zona("Ciudad De Buenos Aires", "Callao", "1500", "Argentina")
        );

        List<Producto> productos = IntStream.range(0, 1000).mapToObj(i -> {
            Producto producto = new Producto(String.valueOf(i), "Producto " + i, "Marca " + i);
            asignarPreciosAUnProductoSegunZonasUsuariosEIndice(zonas, usuarios, i, producto);
            return producto;
        }).toList();

        productoService.saveAll(productos);
    }

    private void asignarPreciosAUnProductoSegunZonasUsuariosEIndice(
            List<Zona> zonas,
            List<Usuario> usuarios,
            int indice,
            Producto producto
    ) {
        zonas.forEach(z -> asignarPreciosAUnProductoSegunUsuariosZonaEIndice(usuarios, z, indice, producto));
    }

    private void asignarPreciosAUnProductoSegunUsuariosZonaEIndice(
            List<Usuario> usuarios,
            Zona zona,
            int indice,
            Producto producto
    ) {
        usuarios.forEach(u -> {
            Precio precio = new Precio(zona, u, indice + Integer.parseInt(producto.getCodigo()));
            producto.addPrecio(precio);
        });
    }

    @Test
    public void saveAndGetByCode() {
        Zona zonaUS = new Zona("Amazon St.", "1024", "Dellaware", "USA");
        Zona zonaUK = new Zona("Amazon Rd.", "1024", "London", "UK");
        Usuario user = new Usuario("claudio");
        Producto producto = new Producto("0001", "Longboard", "Santa Cruz");
        producto.addPrecio(new Precio(zonaUS, user, 78));
        producto.addPrecio(new Precio(zonaUK, user, 82));
        productoService.save(producto);
        Producto producto2 = productoService.getByCode(producto.getCodigo());
        Assertions.assertEquals("0001", producto2.getCodigo());
        Assertions.assertEquals("Longboard", producto2.getNombre());
        Assertions.assertEquals("Santa Cruz", producto2.getMarca());
        Assertions.assertEquals(2, producto2.getPrecios().size());
    }

    @Test
    public void findByMarca() {
        List<Producto> productos = productoService.findByBrand("Marca 200");
        Assertions.assertEquals(1, productos.size());
        Producto producto = productos.get(0);
        Assertions.assertEquals("200", producto.getCodigo());
        Assertions.assertEquals("Producto 200", producto.getNombre());
        Assertions.assertEquals("Marca 200", producto.getMarca());
    }

    @Test
    public void findByPrecio() {
        List<Producto> productos = productoService.findByPrice(446);
        Assertions.assertEquals(1, productos.size(), "Todos los productos deben tener algún precio igual a 446");

        productos = productoService.findByPrice(0);
        Assertions.assertEquals(1, productos.size(), "Solo el primer producto debe tener precios menores a 2");
    }

    @Test
    public void findByPriceRange() {
        List<Producto> productos = productoService.findByPriceRange(3, 5);
        Assertions.assertEquals(1, productos.size());
    }

    @Test
    public void testPrecioPromedio() {
        List<PrecioPromedio> precios = productoService.getPrecioPromedio(List.of("121", "558"));

        Assertions.assertEquals("558", precios.get(0).getCodigo());
        Assertions.assertEquals(1116.0, precios.get(0).getValue(), 0);
        Assertions.assertEquals("121", precios.get(1).getCodigo());
        Assertions.assertEquals(242.0, precios.get(1).getValue(), 0);
    }

    @AfterEach
    public void deleteAll() {
        productoService.deleteAll();
    }

}
