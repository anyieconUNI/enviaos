package co.edu.uniquindio.envio.modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import co.edu.uniquindio.envio.modelo.enums.Ciudad;
import co.edu.uniquindio.envio.modelo.enums.TipEstado;
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
    private Ciudad ciudad;
    private TipEstado estados;
    private LocalDate fecha;
    private  float distancia;
    private float valor;

}
