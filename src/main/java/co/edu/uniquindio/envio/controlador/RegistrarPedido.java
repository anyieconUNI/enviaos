package co.edu.uniquindio.envio.controlador;

import co.edu.uniquindio.envio.modelo.Envios;
import co.edu.uniquindio.envio.modelo.Persona;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarPedido implements Initializable {

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
            }
            else{
                txtNombre.setText("");
                txtDireccion.setText("");
                txtCiudad.setText("");
                txtNuTele.setText("");
                txtCorreo.setText("");
            }
        });
    }
}
