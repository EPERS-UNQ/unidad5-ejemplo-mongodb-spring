package ar.edu.unq.epers.unidad5.model;

import lombok.*;

@ToString
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Precio {

    private Zona zona;
    private Usuario user;
    private int precio;

    public Precio(Zona zona, Usuario user, int precio) {
        this.zona = zona;
        this.user = user;
        this.precio = precio;
    }
}