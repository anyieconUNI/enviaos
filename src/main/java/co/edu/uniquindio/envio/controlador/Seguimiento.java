package co.edu.uniquindio.envio.controlador;

import co.edu.uniquindio.envio.modelo.EnvioHistorico;
import co.edu.uniquindio.envio.modelo.Paquete;
import co.edu.uniquindio.envio.modelo.enums.TipEstado;
import co.edu.uniquindio.envio.modelo.enums.TipoEnvio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class Seguimiento implements Initializable {
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();
    public TableView<EnvioHistorico> tablaSegui;
    public TableColumn<EnvioHistorico, String> code;
    public TableColumn<EnvioHistorico, String> fecha;
    public TableColumn<EnvioHistorico, String> estado;
    public TableColumn<EnvioHistorico, String> tipo;
    public TableColumn<EnvioHistorico, String> ciudad;
    public TableColumn<EnvioHistorico, String> valor;
    public DatePicker dataFecha;
    public ChoiceBox selectestados;
    public ChoiceBox selecttipo;

    @Override
    public void initialize(URL location, ResourceBundle resources)
        {
            // Configurar el PropertyValueFactory para cada columna de la tabla
            code.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigoEnvio()));
            fecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
            estado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstados().toString()));
            tipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipo().toString()));
            ciudad.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getCiudad()));
            valor.setCellValueFactory(cellData -> new SimpleStringProperty(Float.toString(cellData.getValue().getValor())));
            tablaSegui.setItems(FXCollections.observableArrayList(controladorPrincipal.obtenerHistorico()));


        }

    public void filtar(ActionEvent actionEvent) {
        String tipos = (String) selecttipo.getValue();
        TipoEnvio tipo = TipoEnvio.valueOf(tipos);
        String estados = (String) selectestados.getValue();
        TipEstado estado = TipEstado.valueOf(estados);
        LocalDate date = dataFecha.getValue();

        List<EnvioHistorico> enviosFiltrados = controladorPrincipal.filtrarDatos(date,tipo,estado);
        tablaSegui.setItems(FXCollections.observableArrayList(enviosFiltrados));
    }

}

