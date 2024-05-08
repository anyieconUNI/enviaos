package co.edu.uniquindio.envio.controlador;

import co.edu.uniquindio.envio.modelo.Envios;
import co.edu.uniquindio.envio.modelo.Paquete;
import co.edu.uniquindio.envio.modelo.enums.TipoEnvio;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;



public class RegisPaquete  {
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
    private final Envios envios = Envios.getInstancia();
    public TableView<Paquete> tablaPaquete;
    public TableColumn<Paquete, String> descri;
    public TableColumn<Paquete, String> pesos;
    public TableColumn<Paquete, String> valores;


    public void agregar(ActionEvent actionEvent) {
        float peso = Float.parseFloat(txtPeso.getText());
        float distancia = Float.parseFloat(txtdistancias.getText());
        float valor = Float.parseFloat(labelValor.getText());
        String tipos = (String) selectCategory.getValue();
        TipoEnvio tipo = TipoEnvio.valueOf(tipos);
        try {
            envios.agregarPaquete(txtDesPaquete.getText(),distancia,tipo,peso,valor);
            System.out.println("agregado");
            descri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
            pesos.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getPeso()));
            valores.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getValor()));
            tablaPaquete.getItems().add(new Paquete(txtDesPaquete.getText(), distancia, tipo, peso, valor));
        }catch (Exception e) {
            System.out.println("no agregado");
        }
    }

    public void crearEnvio(ActionEvent actionEvent) {
    }

    public void calcular(ActionEvent actionEvent) {
        try {
            float peso = Float.parseFloat(txtPeso.getText());
            float distancia = Float.parseFloat(txtdistancias.getText());
            String tipos = (String) selectCategory.getValue();
            TipoEnvio tipo = TipoEnvio.valueOf(tipos);
            float precio = (float) envios.calcularPrecio(distancia,tipo,peso,2);
            labelValor.setText(String.valueOf(precio));
        } catch (Exception e) {
            System.out.println("mal");
        }
    }
}
