package ar.edu.unq.epers.unidad5.service;

import ar.edu.unq.epers.unidad5.model.Item;
import ar.edu.unq.epers.unidad5.model.Personaje;
import ar.edu.unq.epers.unidad5.model.PesoPromedio;

import java.util.List;

public interface PersonajeService {
    Personaje crear(Personaje personaje);
    void crearTodos(List<Personaje> personajes);
    void recoger(String personajeId, Item item);
    List<Personaje> recuperarPorNombre(String nombre);
    List<Personaje> recuperarPorVida(int vida);
    List<Personaje> recuperarPorRangoDeVida(int min, int max);
    List<Item> recuperarItemsDePersonaje(String nombre);
    List<PesoPromedio> obtenerPesoPromedio(List<String> nombres);
    void eliminarTodo();
}
