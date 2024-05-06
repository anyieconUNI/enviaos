package co.edu.uniquindio.envio.modelo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Factura {
    private String codigoEnvio;
    private String total;
    private String subtotal;
}
