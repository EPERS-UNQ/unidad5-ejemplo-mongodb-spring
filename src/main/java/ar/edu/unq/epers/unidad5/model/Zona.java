package ar.edu.unq.epers.unidad5.model;

import lombok.*;

@ToString
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Zona {

    private String calle;
    private String altura;
    private String localidad;
    private String pais;

    public Zona(String calle, String altura, String localidad, String pais) {
        this.calle = calle;
        this.altura = altura;
        this.localidad = localidad;
        this.pais = pais;
    }
}
