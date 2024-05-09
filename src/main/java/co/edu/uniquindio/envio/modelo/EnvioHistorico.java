package co.edu.uniquindio.envio.modelo;

import java.util.List;

import co.edu.uniquindio.envio.modelo.enums.TipoEnvio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class EnvioHistorico {
    private String codigoEnvio;
    private String remitente;
    private String destinatario;
    private List<Paquete> paquetes;
    private TipoEnvio tipo;
    private Estados estados;
}
