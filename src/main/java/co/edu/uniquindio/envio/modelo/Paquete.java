package co.edu.uniquindio.envio.modelo;

import co.edu.uniquindio.envio.modelo.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Paquete {
    private String descripcion;
    private  float peso;
//    private  String valor;
}
