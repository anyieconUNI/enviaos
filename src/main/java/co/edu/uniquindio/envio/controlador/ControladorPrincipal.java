package co.edu.uniquindio.envio.controlador;
import co.edu.uniquindio.envio.modelo.Envios;
import co.edu.uniquindio.envio.modelo.Paquete;
import co.edu.uniquindio.envio.modelo.Persona;
import co.edu.uniquindio.envio.modelo.enums.TipoEnvio;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;
import org.example.servicio.EnvioServicio;
import org.example.servicio.Parametrizable;


/**
 * Clase que representa el controlador principal de la aplicación
 */

@Getter
public class ControladorPrincipal implements EnvioServicio {

    private final Envios envios;
    public static ControladorPrincipal INSTANCIA;

    private ControladorPrincipal(){
        envios = new Envios();
    }

    public static ControladorPrincipal getInstancia(){
        if(INSTANCIA == null){
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }

    @Override
    public Persona agregarPersonas(String cedula, String nombre, String direccion, String ciudad, String numero, String correo) {
        return null;
    }

    @Override
    public Persona obtenerPersonas(String cedula) {
        return envios.obtenerPersonas(cedula);
    }

    @Override
    public Persona actualizarPersona(String cedula, String nombre, String direccion, String ciudad, String numero, String correo) {
        return null;
    }

    @Override
    public Paquete agregarPaquete(String descripcion, float peso) {
        return null;
    }

    @Override
    public String generarCodigo(TipoEnvio tipo) {
        try {
            return envios.generarCodigo(tipo);
        } catch (Exception e) {
            // Manejar la excepción aquí, como mostrar un mensaje de error
            e.printStackTrace();
            return null;
        }
    }

    public void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta - Hotel");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    public FXMLLoader navegar(String nombreVista, String titulo, Object... parametros){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreVista));
            Parent root = loader.load();

            Object controller = loader.getController();

//            recibe los parametros para enviar entre controladores
            if (controller instanceof Parametrizable) {
                ((Parametrizable) controller).datosPersona(parametros);
            }

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