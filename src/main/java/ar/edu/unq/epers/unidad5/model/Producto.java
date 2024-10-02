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

@Document("Producto")
public class Producto {

    @Id
    private String id;
    private String codigo;
    private String nombre;
    private String marca;
    private List<Precio> precios = new ArrayList<>();

    public Producto(String codigo, String nombre, String marca) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
    }

    public void addPrecio(Precio precio) {
        this.precios.add(precio);
    }
}