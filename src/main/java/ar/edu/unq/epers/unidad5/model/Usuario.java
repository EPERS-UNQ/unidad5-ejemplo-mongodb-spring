package ar.edu.unq.epers.unidad5.model;

import lombok.*;

@ToString
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Usuario {

    private String username;

    public Usuario(String username) {
        this.username = username;
    }
}
