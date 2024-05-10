package co.edu.uniquindio.envio.controlador;

import co.edu.uniquindio.envio.modelo.Envios;
import co.edu.uniquindio.envio.modelo.Paquete;
import co.edu.uniquindio.envio.modelo.enums.TipoEnvio;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.servicio.Parametrizable;

import java.net.URL;
import java.util.ResourceBundle;


public class RegisPaquete implements Parametrizable {
    @FXML
    public TextArea txtDesPaquete;
    @FXML
    public TextField txtPeso;
    @FXML
    public Label labelValor;
    @FXML
    public ChoiceBox selectCategory;
    @FXML
    public TextField txtdistancias;
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    public TableView<Paquete> tablaPaquete;
    public TableColumn<Paquete, String> descri;
    public TableColumn<Paquete, String> pesos;
    public TableColumn<Paquete, String> valores;
    @FXML
    public Label diste;
    @FXML
    public Label type;
    @FXML
    public Label val;
    public Button btncalcula;
    public Label decri;
    public Label peso;
    public Button agregar;
    String idEmisor;
    String idReceptor;

    @Override
    public void datosPersona(Object... parametros) {
        if (parametros.length >= 2) {
            // Extraer los parámetros recibidos
            idEmisor = (String) parametros[0];
            idReceptor = (String) parametros[1];
            System.out.println(idEmisor+"recptor"+idReceptor);
        } else {
            System.out.println("No se proporcionaron suficientes parámetros");
       }
    }
    public void agregar(ActionEvent actionEvent) {
        float peso = Float.parseFloat(txtPeso.getText());
        float distancia = Float.parseFloat(txtdistancias.getText());
        float valor = Float.parseFloat(labelValor.getText());
        String tipos = (String) selectCategory.getValue();
        TipoEnvio tipo = TipoEnvio.valueOf(tipos);
        try {
            controladorPrincipal.agregarPaquete(txtDesPaquete.getText(),peso);
            System.out.println("agregado");
            descri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
            pesos.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getPeso()));
//            valores.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getValor()));
            tablaPaquete.getItems().add(new Paquete(txtDesPaquete.getText(), peso));
        }catch (Exception e) {
            System.out.println("no agregado");
        }
    }

    public void crearEnvio(ActionEvent actionEvent) {
        labelValor.setVisible(true);
        diste.setVisible(true);
        type.setVisible(true);
        val.setVisible(true);
        selectCategory.setVisible(true);
        txtdistancias.setVisible(true);
        btncalcula.setVisible(true);
//        decri.setVisible(false);
//        peso.setVisible(false);
//        txtDesPaquete.setVisible(false);
//        txtPeso.setVisible(false);
//        agregar.setVisible(false);
        String tipos = (String) selectCategory.getValue();
        TipoEnvio tipo = TipoEnvio.valueOf(tipos);
        String codigo =controladorPrincipal.generarCodigo(tipo);
    }

    public void calcular(ActionEvent actionEvent) {
//        try {
//            float peso = Float.parseFloat(txtPeso.getText());
//            float distancia = Float.parseFloat(txtdistancias.getText());
//            String tipos = (String) selectCategory.getValue();
//            TipoEnvio tipo = TipoEnvio.valueOf(tipos);
//            float precio = (float) controladorPrincipal.calcularPrecio(distancia,tipo,peso,2);
//            labelValor.setText(String.valueOf(precio));
//        } catch (Exception e) {
//            System.out.println("mal");
//        }
    }


}
