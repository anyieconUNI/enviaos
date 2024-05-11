package co.edu.uniquindio.envio.controlador;

import co.edu.uniquindio.envio.modelo.Paquete;
import co.edu.uniquindio.envio.modelo.enums.Ciudad;
import co.edu.uniquindio.envio.modelo.enums.TipEstado;
import co.edu.uniquindio.envio.modelo.enums.TipoEnvio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import org.example.servicio.Parametrizable;

import java.time.LocalDate;


public class RegisPaquete implements Parametrizable {
    @FXML
    public TextArea txtDesPaquete;
    @FXML
    public TextField txtPeso;
    @FXML
    public Label labelValor;
    @FXML
    public ChoiceBox selectCategorys;
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
    public ChoiceBox selectCiudad;
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
        try {
            controladorPrincipal.agregarPaquete(txtDesPaquete.getText(),peso);
            System.out.println("agregado");
            descri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
            pesos.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getPeso()));
//            valores.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getValor()));
            tablaPaquete.getItems().add(new Paquete(txtDesPaquete.getText(), peso));
            txtDesPaquete.setText("");
            txtPeso.setText("");
        } catch (NumberFormatException e) {
            controladorPrincipal.mostrarAlerta("El peso debe ser un número válido.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    boolean data = false;
    public void calcular(ActionEvent actionEvent) {
        try {
            float distancia = Float.parseFloat(txtdistancias.getText());
            String tipos = (String) selectCategorys.getValue();
            TipoEnvio tipo = TipoEnvio.valueOf(tipos);
            int cantidadPaquetes = tablaPaquete.getItems().size();
            float pesoTotal = 0;
            for (Paquete paquete : tablaPaquete.getItems()) {
                pesoTotal += paquete.getPeso();
            }
            System.out.println(pesoTotal+"  "+"  "+cantidadPaquetes);
            float precio = (float) controladorPrincipal.calcularPrecio(distancia,tipo,pesoTotal,cantidadPaquetes);
            System.out.println(precio);
            data=true;
            labelValor.setText(String.valueOf(precio));
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    public void crearEnvio(ActionEvent actionEvent) {
        if (tablaPaquete.getItems().isEmpty()) {
            controladorPrincipal.mostrarAlerta("Primero se deben registrar paquetes.", Alert.AlertType.ERROR);
        }
        else{
            selectCategorys.setDisable(false);
            selectCiudad.setDisable(false);
            txtdistancias.setEditable(true);
            btncalcula.setDisable(false);
            txtDesPaquete.setEditable(false);
            txtPeso.setEditable(false);
            agregar.setDisable(true);
            if(data){
                String tipos = (String) selectCategorys.getValue();
                TipoEnvio tipo = TipoEnvio.valueOf(tipos);
                String ciudades = (String) selectCiudad.getValue();
                Ciudad ciudad = Ciudad.valueOf(ciudades);
                LocalDate fechaActual = LocalDate.now();
                String codigo =controladorPrincipal.generarCodigo(tipo);
                float distancia = Float.parseFloat(txtdistancias.getText());
                float valor = Float.parseFloat(labelValor.getText());
                    ObservableList<Paquete> listaPaquetes = tablaPaquete.getItems();
                System.out.println(listaPaquetes);
                System.out.println("ESTE ES EL VALOR: "+ valor);
                    controladorPrincipal.mostrarAlerta("Envio Creado", Alert.AlertType.CONFIRMATION);
                try {
                    controladorPrincipal.crearHistorial(codigo,idEmisor,idReceptor,listaPaquetes,tipo,ciudad,TipEstado.CREADO,fechaActual,distancia,valor);
                    System.out.println(controladorPrincipal.datos());

                }catch (Exception e) {
                    controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        }
    }

}
