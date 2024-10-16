package ar.edu.unq.epers.unidad5.model;

import ar.edu.unq.epers.unidad5.model.exception.MuchoPesoException;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@ToString
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Document("Personaje")
public class Personaje {

    @Id
    private String id;
    private String nombre;
    private int vida;
    private int pesoMaximo;
    private Set<Item> inventario = new HashSet<>();

    public Personaje(String nombre, int vida, int pesoMaximo) {
        this.nombre = nombre;
        this.vida = vida;
        this.pesoMaximo = pesoMaximo;
    }

    public int getPesoActual() {
        return inventario.stream().mapToInt(Item::getPeso).sum();
    }

    public void recoger(Item item) {
        int pesoActual = getPesoActual();
        if (pesoActual + item.getPeso() > this.pesoMaximo) {
            throw new MuchoPesoException(this, item);
        }
        this.inventario.add(item);
    }
}
