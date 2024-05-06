package co.edu.uniquindio.envio.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RegisPaquete {
    @FXML
    public TextArea txtDesPaquete;
    @FXML
    public TextField txtPeso;
    @FXML
    public Label labelValor;
    @FXML
    public ChoiceBox selectCategory;

    public void crear(ActionEvent actionEvent) {

    }
}
