package co.edu.uniquindio.envio.modelo;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paquete {
    private String descripcion;
    private  float peso;
//    private  String valor;
}
