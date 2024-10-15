package ar.edu.unq.epers.unidad5

import ar.edu.unq.epers.unidad5.model.Dungeon
import ar.edu.unq.epers.unidad5.model.Item
import ar.edu.unq.epers.unidad5.model.Personaje
import ar.edu.unq.epers.unidad5.service.PersonajeService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.function.Consumer
import java.util.stream.IntStream

@SpringBootTest
class PersonajeServiceTest {

    @Autowired
    lateinit var personajeService: PersonajeService

    @BeforeEach
    fun setup() {
        val dungeons = listOf(
            Dungeon("Palacio del infinito"),
            Dungeon("Calabozo del androide"),
            Dungeon("Via circuito")
        )
        val items = listOf(
            Item("Baculo", 25, mutableListOf(dungeons[0])),
            Item("Tunica", 30, mutableListOf(dungeons[1], dungeons[0])),
            Item("Sombrero", 15, mutableListOf(dungeons[2])),
            Item("Pocion", 4, mutableListOf(dungeons[1], dungeons[2]))
        )

        val personajes = IntStream.range(0, 1000).mapToObj { i: Int ->
            val personaje = Personaje("Maguito $i", i+1, 80)
            recogerItems(items, personaje)
            personaje
        }
        personajeService.crearTodos(personajes.toList())
    }

    private fun recogerItems(items: List<Item>, personaje: Personaje) {
        items.forEach(Consumer { i: Item ->
            personaje.recoger(i)
        })
    }

    @Test
    fun recuperarPorNombre() {
        val personajes = personajeService.recuperarPorNombre("Maguito 4")
        val personaje = personajes[0]

        Assertions.assertEquals(5, personaje.vida)
        Assertions.assertEquals(80, personaje.pesoMaximo)
        Assertions.assertEquals(74, personaje.pesoActual)
        Assertions.assertEquals(4, personaje.inventario.size)
    }

    @Test
    fun recuperarPorVida() {
        val personajes = personajeService.recuperarPorVida(10)
        val personaje = personajes[0]

        Assertions.assertEquals("Maguito 9", personaje.nombre)
        Assertions.assertEquals(80, personaje.pesoMaximo)
        Assertions.assertEquals(74, personaje.pesoActual)
        Assertions.assertEquals(4, personaje.inventario.size)
    }

    @Test
    fun recuperarPorRangoDeVida() {
        val personajes = personajeService.recuperarPorRangoDeVida(20, 30)

        Assertions.assertEquals(9, personajes.size)
    }

    @Test
    fun recuperarItemsDePersonaje() {
       val items = personajeService.recuperarItemsDePersonaje("Maguito 405")
        val tunica = items[0]
        val baculo = items[1]
        val sombrero = items[2]
        val pocion = items[3]
        Assertions.assertEquals("Tunica", tunica.nombre)
        Assertions.assertEquals(30, tunica.peso)
        Assertions.assertEquals("Baculo", baculo.nombre)
        Assertions.assertEquals(25, baculo.peso)
        Assertions.assertEquals("Sombrero", sombrero.nombre)
        Assertions.assertEquals(15, sombrero.peso)
        Assertions.assertEquals("Pocion", pocion.nombre)
        Assertions.assertEquals(4, pocion.peso)
    }

    @Test
    fun obtenerPesoPromedio() {
        val pesosPromedios = personajeService.obtenerPesoPromedio(listOf("Maguito 200", "Maguito 500"))

        Assertions.assertEquals("Maguito 500", pesosPromedios[0].nombre)
        Assertions.assertEquals(18.5, pesosPromedios[0].valor)
        Assertions.assertEquals("Maguito 200", pesosPromedios[1].nombre)
        Assertions.assertEquals(18.5, pesosPromedios[1].valor)
    }

    @AfterEach
    fun deleteAll(){
        personajeService.eliminarTodo()
    }
}