package co.edu.uniquindio.envio.modelo;

import co.edu.uniquindio.envio.modelo.enums.Estado;
import co.edu.uniquindio.envio.modelo.enums.TipoEnvio;
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
    private float distancia;
    private TipoEnvio tipo;
    private  float peso;
    private  float valor;
}
