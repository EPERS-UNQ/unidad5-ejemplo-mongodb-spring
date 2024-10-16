package ar.edu.unq.epers.unidad5.model;

import lombok.*;

@ToString
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Dungeon {
    private String nombre;

    public Dungeon(String nombre) {
        this.nombre = nombre;
    }
}
