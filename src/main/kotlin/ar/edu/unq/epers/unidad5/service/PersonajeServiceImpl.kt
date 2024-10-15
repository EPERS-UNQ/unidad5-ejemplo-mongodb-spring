package ar.edu.unq.epers.unidad5.service

import ar.edu.unq.epers.unidad5.dao.PersonajeDAO
import ar.edu.unq.epers.unidad5.model.Item
import ar.edu.unq.epers.unidad5.model.Personaje
import ar.edu.unq.epers.unidad5.model.PesoPromedio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonajeServiceImpl : PersonajeService {

    @Autowired
    lateinit var personajeDAO: PersonajeDAO

    override fun crear(personaje: Personaje): Personaje {
        return personajeDAO.save(personaje)
    }

    override fun crearTodos(personajes: List<Personaje>) {
        personajeDAO.saveAll(personajes)
    }

    override fun recoger(personajeId: String, item: Item) {
        val personaje = personajeDAO.findByIdOrNull(personajeId)
        personaje?.recoger(item)
        personajeDAO.save(personaje!!)
    }

    override fun recuperarPorNombre(nombre: String): List<Personaje> {
        return personajeDAO.recuperarPorNombre(nombre)
    }

    override fun recuperarPorVida(vida: Int): List<Personaje> {
        return personajeDAO.recuperarPorVida(vida)
    }

    override fun recuperarPorRangoDeVida(min: Int, max: Int): List<Personaje> {
        return personajeDAO.recuperarPorRangoDeVida(min, max)
    }

    override fun recuperarItemsDePersonaje(nombre: String): List<Item> {
        return personajeDAO.recuperarItemsDePersonaje(nombre)
    }

    override fun obtenerPesoPromedio(nombres: List<String>): List<PesoPromedio> {
        return personajeDAO.obtenerPesoPromedio(nombres)
    }

    override fun eliminarTodo() {
        personajeDAO.deleteAll()
    }
}