package ar.edu.unq.epers.unidad5;

import ar.edu.unq.epers.unidad5.model.Dungeon;
import ar.edu.unq.epers.unidad5.model.Item;
import ar.edu.unq.epers.unidad5.model.Personaje;
import ar.edu.unq.epers.unidad5.model.PesoPromedio;
import ar.edu.unq.epers.unidad5.service.PersonajeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class PersonajeServiceTest {

    @Autowired private PersonajeService personajeService;

    @BeforeEach
    public void setup() {
        List<Dungeon> dungeons = List.of(
                new Dungeon("Palacio del infinito"),
                new Dungeon("Calabozo del androide"),
                new Dungeon("Via circuito")
        );
        List<Item> items = List.of(
                new Item("Baculo", 25, List.of(dungeons.get(0))),
                new Item("Tunica", 30, List.of(dungeons.get(1), dungeons.get(0))),
                new Item("Sombrero", 15, List.of(dungeons.get(2))),
                new Item("Pocion", 4, List.of(dungeons.get(1), dungeons.get(2)))
        );

        List<Personaje> personajes = IntStream.range(0, 1000).mapToObj(i -> {
            Personaje personaje = new Personaje("Maguito " + i, i + 1, 80);
            recogerItems(items, personaje);
            return personaje;
        }).toList();

        personajeService.crearTodos(personajes);
    }

    private void recogerItems(List<Item> items, Personaje personaje) {
        items.forEach(personaje::recoger);
    }

    @Test
    public void recuperarPorNombre() {
        List<Personaje> personajes = personajeService.recuperarPorNombre("Maguito 4");
        Personaje personaje = personajes.get(0);

        Assertions.assertEquals(5, personaje.getVida());
        Assertions.assertEquals(80, personaje.getPesoMaximo());
        Assertions.assertEquals(74, personaje.getPesoActual());
        Assertions.assertEquals(4, personaje.getInventario().size());
    }

    @Test
    public void recuperarPorVida() {
        List<Personaje> personajes = personajeService.recuperarPorVida(10);
        Personaje personaje = personajes.get(0);

        Assertions.assertEquals("Maguito 9", personaje.getNombre());
        Assertions.assertEquals(80, personaje.getPesoMaximo());
        Assertions.assertEquals(74, personaje.getPesoActual());
        Assertions.assertEquals(4, personaje.getInventario().size());
    }

    @Test
    public void recuperarPorRangoDeVida() {
        List<Personaje> personajes = personajeService.recuperarPorRangoDeVida(20, 30);
        Assertions.assertEquals(9, personajes.size());
    }

    @Test
    public void recuperarItemsDePersonaje() {
        List<Item> items = personajeService.recuperarItemsDePersonaje("Maguito 405");
        Item tunica = items.get(0);
        Item baculo = items.get(1);
        Item sombrero = items.get(2);
        Item pocion = items.get(3);

        Assertions.assertEquals("Tunica", tunica.getNombre());
        Assertions.assertEquals(30, tunica.getPeso());
        Assertions.assertEquals("Baculo", baculo.getNombre());
        Assertions.assertEquals(25, baculo.getPeso());
        Assertions.assertEquals("Sombrero", sombrero.getNombre());
        Assertions.assertEquals(15, sombrero.getPeso());
        Assertions.assertEquals("Pocion", pocion.getNombre());
        Assertions.assertEquals(4, pocion.getPeso());
    }

    @Test
    public void obtenerPesoPromedio() {
        List<PesoPromedio> pesosPromedios = personajeService.obtenerPesoPromedio(List.of("Maguito 200", "Maguito 500"));

        Assertions.assertEquals("Maguito 500", pesosPromedios.get(0).getNombre());
        Assertions.assertEquals(18.5, pesosPromedios.get(0).getValor());
        Assertions.assertEquals("Maguito 200", pesosPromedios.get(1).getNombre());
        Assertions.assertEquals(18.5, pesosPromedios.get(1).getValor());
    }

    @AfterEach
    public void deleteAll() {
        personajeService.eliminarTodo();
    }
}