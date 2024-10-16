package ar.edu.unq.epers.unidad5.model.exception;

import ar.edu.unq.epers.unidad5.model.Item;
import ar.edu.unq.epers.unidad5.model.Personaje;

final public class MuchoPesoException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final Personaje personaje;
    private final Item item;

    public MuchoPesoException(Personaje personaje, Item item) {
        this.personaje = personaje;
        this.item = item;
    }

    @Override
    public String getMessage() {
        return "El personaje [" + personaje.getNombre() + "] no puede recoger [" + item.getNombre() + "] porque carga mucho peso ya";
    }
}
