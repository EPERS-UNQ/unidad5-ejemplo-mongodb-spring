package ar.edu.unq.epers.unidad5.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@ToString
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Item {
    private String nombre;
    private int peso;
    private List<Dungeon> obtenibleEn = new ArrayList<>();

    public Item(String nombre, int peso, List<Dungeon> obtenibleEn) {
        this.nombre = nombre;
        this.peso = peso;
        this.obtenibleEn = obtenibleEn;
    }
}
