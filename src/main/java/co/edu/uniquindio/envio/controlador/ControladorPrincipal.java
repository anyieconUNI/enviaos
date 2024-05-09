package co.edu.uniquindio.envio.controlador;
import co.edu.uniquindio.envio.modelo.EnvioHistorico;
import co.edu.uniquindio.envio.modelo.Envios;
import co.edu.uniquindio.envio.modelo.Estados;
import co.edu.uniquindio.envio.modelo.Factura;
import co.edu.uniquindio.envio.modelo.Paquete;
import co.edu.uniquindio.envio.modelo.Persona;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;
import org.example.servicio.EnvioServicio;


/**
 * Clase que representa el controlador principal de la aplicación
 */

public class ControladorPrincipal implements EnvioServicio {

    @Getter
    private final Envios envios;
    public static ControladorPrincipal INSTANCIA;

    private ControladorPrincipal(){
        envios = new envios();
    }

    public static ControladorPrincipal getInstancia(){
        if(INSTANCIA == null){
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }


    public void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta - Hotel");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public FXMLLoader navegar(String nombreVista, String titulo){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreVista));
            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(titulo);

            // Mostrar la nueva ventana
            stage.show();

            return loader;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

}
