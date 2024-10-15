package ar.edu.unq.epers.unidad5.service

import ar.edu.unq.epers.unidad5.model.Item
import ar.edu.unq.epers.unidad5.model.Personaje
import ar.edu.unq.epers.unidad5.model.PesoPromedio

interface PersonajeService {
    fun crear(personaje: Personaje): Personaje
    fun crearTodos(personajes: List<Personaje>)
    fun recoger(personajeId: String, item: Item)
    fun recuperarPorNombre(nombre: String): List<Personaje>
    fun recuperarPorVida(vida: Int): List<Personaje>
    fun recuperarPorRangoDeVida(min: Int, max: Int): List<Personaje>
    fun recuperarItemsDePersonaje(nombre: String): List<Item>
    fun obtenerPesoPromedio(nombres: List<String>): List<PesoPromedio>
    fun eliminarTodo()
}