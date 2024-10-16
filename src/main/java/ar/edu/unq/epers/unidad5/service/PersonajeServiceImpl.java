package ar.edu.unq.epers.unidad5.service;

import ar.edu.unq.epers.unidad5.dao.PersonajeDAO;
import ar.edu.unq.epers.unidad5.model.Item;
import ar.edu.unq.epers.unidad5.model.Personaje;
import ar.edu.unq.epers.unidad5.model.PesoPromedio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    private final PersonajeDAO personajeDAO;

    public PersonajeServiceImpl(PersonajeDAO personajeDAO) {
        this.personajeDAO = personajeDAO;
    }

    @Override
    public Personaje crear(Personaje personaje) {
        return personajeDAO.save(personaje);
    }

    @Override
    public void crearTodos(List<Personaje> personajes) {
        personajeDAO.saveAll(personajes);
    }

    @Override
    public void recoger(String personajeId, Item item) {
        Personaje personaje = personajeDAO.findById(personajeId).get();
        personaje.recoger(item);
        personajeDAO.save(personaje);
    }

    @Override
    public List<Personaje> recuperarPorNombre(String nombre) {
        return personajeDAO.recuperarPorNombre(nombre);
    }

    @Override
    public List<Personaje> recuperarPorVida(int vida) {
        return personajeDAO.recuperarPorVida(vida);
    }

    @Override
    public List<Personaje> recuperarPorRangoDeVida(int min, int max) {
        return personajeDAO.recuperarPorRangoDeVida(min, max);
    }

    @Override
    public List<Item> recuperarItemsDePersonaje(String nombre) {
        return personajeDAO.recuperarItemsDePersonaje(nombre);
    }

    @Override
    public List<PesoPromedio> obtenerPesoPromedio(List<String> nombres) {
        return personajeDAO.obtenerPesoPromedio(nombres);
    }

    @Override
    public void eliminarTodo() {
        personajeDAO.deleteAll();
    }
}
