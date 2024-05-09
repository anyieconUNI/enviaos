package co.edu.uniquindio.envio.modelo;

import co.edu.uniquindio.envio.modelo.enums.Estado;
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
