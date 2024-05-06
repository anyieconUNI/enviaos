package co.edu.uniquindio.envio.controlador;

import co.edu.uniquindio.envio.modelo.Envios;
import co.edu.uniquindio.envio.modelo.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Emisor implements Initializable {
    @FXML
    public TextField txtIdentificacion;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtDireccion;
    @FXML
    public TextField txtCiudad;
    @FXML
    public TextField txtNuTele;
    @FXML
    public TextField txtCorreo;

    private final Envios envios = Envios.getInstancia();

    Boolean dataPersona = false;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtIdentificacion.textProperty().addListener((observable, oldValue, newValue) -> {
            Persona persona = envios.obtenerPersonas(txtIdentificacion.getText());
            if(persona != null){
                System.out.println("El valor de txtIdentificacion ha cambiado a: " + persona.getCedula());
                txtNombre.setText(persona.getNombre());
                txtDireccion.setText(persona.getDireccion());
                txtCiudad.setText(persona.getCiudad());
                txtNuTele.setText(persona.getNumero());
                txtCorreo.setText(persona.getCorreo());
                dataPersona = true;
            }
            else{
                txtNombre.setText("");
                txtDireccion.setText("");
                txtCiudad.setText("");
                txtNuTele.setText("");
                txtCorreo.setText("");
                dataPersona = false;
                System.out.println("NO SE ENCUENTRA");
            }
        });
    }

    public void siguiRecep(ActionEvent actionEvent) {
        if (dataPersona){
            try {
                envios.actualizarPersona(txtIdentificacion.getText(),txtNombre.getText(),txtDireccion.getText(),txtCiudad.getText(),txtNuTele.getText(),txtCorreo.getText());
                System.out.println("se actualizó");
                navegarVentana("/regisPaquete.fxml", "Enviaos");
            }catch (Exception e){
                System.out.println("Problemas para actualizar");
            }
        }
        else{
            try {
                envios.agregarPersonas(txtIdentificacion.getText(),txtNombre.getText(),txtDireccion.getText(),txtCiudad.getText(),txtNuTele.getText(),txtCorreo.getText());
                System.out.println("Creado");
                navegarVentana("/regisPaquete.fxml", "Enviaos");
            }catch (Exception e){
                crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }
    public void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
        try {

            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void crearAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}