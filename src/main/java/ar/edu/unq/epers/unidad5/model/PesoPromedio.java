package ar.edu.unq.epers.unidad5.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Document
public class PesoPromedio {
    @Id
    private String nombre;
    private double valor = 0;
}
