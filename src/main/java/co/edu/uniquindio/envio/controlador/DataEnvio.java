package co.edu.uniquindio.envio.controlador;

import co.edu.uniquindio.envio.modelo.EnvioHistorico;
import co.edu.uniquindio.envio.modelo.enums.TipEstado;
import co.edu.uniquindio.envio.modelo.enums.TipoEnvio;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import org.example.servicio.Parametrizable;

import java.net.URL;
import java.util.ResourceBundle;

public class DataEnvio implements Parametrizable,Initializable {
    public Label txtTipo;
    public ChoiceBox<TipEstado> selectestados;

    public Label txtDistan;
    public Label txtCiudad;
    public Label txtFecha;
    public Label txtValor;
    String code;
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();

    @Override
    public void datosPersona(Object... parametros) {
        if (parametros.length >= 1) {
            // Extraer los parámetros recibidos
          code = (String) parametros[0];
            System.out.println("ESTE ES EL CODIGO DEL ENVIO"+code);
            cargar();
        } else {
            System.out.println("No se proporcionaron suficientes parámetros");
        }
    }

    public void cargar(){
        EnvioHistorico envio = null;
        envio = controladorPrincipal.cargarEnvio(code);
        System.out.println(envio);
        System.out.println("ESTE ES EL CODIGO QUE SE RECIVE "+code);
        if(envio != null){
            txtTipo.setText(String.valueOf(envio.getTipo()));
            selectestados.setValue(envio.getEstados());
            txtCiudad.setText(String.valueOf(envio.getCiudad()));
            txtDistan.setText(String.valueOf(envio.getDistancia()));
            txtFecha.setText(String.valueOf(envio.getFecha()));
            txtValor.setText(String.valueOf(envio.getValor()));
        }
    }
    public void initialize(URL location, ResourceBundle resources) {
        selectestados.setItems(FXCollections.observableArrayList(TipEstado.values()));
    }
    public void Guardar(ActionEvent actionEvent) {
        TipEstado estado = selectestados.getValue();
        System.out.println(estado);
        try{
            controladorPrincipal.actualizarEnvio(code, estado);
            controladorPrincipal.mostrarAlerta("Actualizado", Alert.AlertType.CONFIRMATION);
            controladorPrincipal.cerrarVentana(selectestados);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
