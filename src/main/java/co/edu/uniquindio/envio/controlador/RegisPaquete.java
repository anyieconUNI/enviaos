package co.edu.uniquindio.envio.controlador;

import co.edu.uniquindio.envio.modelo.Paquete;
import co.edu.uniquindio.envio.modelo.Persona;
import co.edu.uniquindio.envio.modelo.enums.Ciudad;
import co.edu.uniquindio.envio.modelo.enums.TipEstado;
import co.edu.uniquindio.envio.modelo.enums.TipoEnvio;
import co.edu.uniquindio.envio.utils.EnvioSms;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import org.example.servicio.Parametrizable;

import java.time.LocalDate;
import java.util.List;


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
    @FXML
    public TableView<Paquete> tablaPaquete;
    @FXML
    public TableColumn<Paquete, String> descri;
    @FXML
    public TableColumn<Paquete, String> pesos;
    @FXML
    public TableColumn<Paquete, String> valores;
    @FXML
    public Label diste;
    @FXML
    public Label type;
    @FXML
    public Label val;
    @FXML
    public Button btncalcula;
    @FXML
    public Label decri;
    @FXML
    public Label peso;
    @FXML
    public Button agregar;
    @FXML
    public ChoiceBox selectCiudad;
    public Button enviar;
    String idEmisor;
    String idReceptor;
    String numeroReceptor;

    @Override
    public void datosPersona(Object... parametros) {
        if (parametros.length >= 3) {
            // Extraer los parámetros recibidos
            idEmisor = (String) parametros[0];
            idReceptor = (String) parametros[1];
            numeroReceptor= (String) parametros[2];
            System.out.println(idEmisor+"recptor"+idReceptor);
        } else {
            System.out.println("No se proporcionaron suficientes parámetros");
       }
    }
    public void agregar(ActionEvent actionEvent) {
        String peso = txtPeso.getText();
        try {
            controladorPrincipal.agregarPaquete(txtDesPaquete.getText(),peso);
            System.out.println("agregado");
            descri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
            pesos.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getPeso()));
//            valores.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().getValor()));
//            tablaPaquete.getItems().add(new Paquete(txtDesPaquete.getText(), peso));
            List<Paquete> paquetes = controladorPrincipal.paquetesCargar();
            tablaPaquete.setItems(FXCollections.observableArrayList(paquetes));
            txtDesPaquete.setText("");
            txtPeso.setText("");
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    boolean data = false;
    public void calcular(ActionEvent actionEvent) {
        if (txtdistancias.getText() ==null || txtdistancias.getText().isBlank() || selectCategorys.getValue()==null || selectCiudad.getValue() == null){
            controladorPrincipal.mostrarAlerta("Todos los campos son obligatorios", Alert.AlertType.ERROR);

        }else {
            try {
                float distancia = Float.parseFloat(txtdistancias.getText());
                String tipos = (String) selectCategorys.getValue();
                TipoEnvio tipo = TipoEnvio.valueOf(tipos);
                int cantidadPaquetes = tablaPaquete.getItems().size();
                float pesoTotal = 0;
                for (Paquete paquete : tablaPaquete.getItems()) {
                    pesoTotal += paquete.getPeso();
                }
                System.out.println(pesoTotal + "  " + "  " + cantidadPaquetes);
                float precio = (float) controladorPrincipal.calcularPrecio(distancia, tipo, pesoTotal, cantidadPaquetes);
                System.out.println(precio);
                data = true;
                enviar.setDisable(false);
                labelValor.setText(String.valueOf(precio));
            } catch (Exception e) {
                controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    public void crearEnvio(ActionEvent actionEvent) throws Exception {
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
            enviar.setDisable(true);
            if(data) {
                if (txtdistancias.getText() == null || txtdistancias.getText().isBlank() || selectCategorys.getValue() == null || selectCiudad.getValue() == null) {
                    controladorPrincipal.mostrarAlerta("Todos los campos son obligatorios", Alert.AlertType.ERROR);
                } else {
                    String tipos = (String) selectCategorys.getValue();
                    TipoEnvio tipo = TipoEnvio.valueOf(tipos);
                    String ciudades = (String) selectCiudad.getValue();
                    Ciudad ciudad = Ciudad.valueOf(ciudades);
                    LocalDate fechaActual = LocalDate.now();
                    String codigo = controladorPrincipal.generarCodigo(tipo);
                    float distancia = Float.parseFloat(txtdistancias.getText());
                    float valor = Float.parseFloat(labelValor.getText());
                    ObservableList<Paquete> listaPaquetes = tablaPaquete.getItems();
                    System.out.println(listaPaquetes);
                    System.out.println("ESTE ES EL VALOR: " + valor);
//                    enviarMensaje(idReceptor,codigo);
                    controladorPrincipal.mostrarAlerta("Envio Creado", Alert.AlertType.CONFIRMATION);
                    try {
                        controladorPrincipal.crearHistorial(codigo, idEmisor, idReceptor, listaPaquetes, tipo, ciudad, TipEstado.CREADO, fechaActual, distancia, valor);
                        System.out.println(controladorPrincipal.datos());
                        controladorPrincipal.cerrarVentana(txtDesPaquete);
                    } catch (Exception e) {
                        controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
                    }

                }
            }
        }
    }
    private void enviarMensaje(String idPersona, String codigo) throws Exception {
        Persona persona = controladorPrincipal.obtenerPersonas(idPersona);
        if (persona != null) {
            String numero = persona.getNumero();
            System.out.println(numero);
            if (numero != null && !numero.isBlank()) {
                EnvioSms envioSms = new EnvioSms();
                envioSms.crearConexion();
                envioSms.mensaje = "Sus paquetes llegaran Pronto, codigo de seguimiento: " + codigo;
                envioSms.numero = numeroReceptor;
                envioSms.enviarNotificacion();
            } else {
                controladorPrincipal.mostrarAlerta("El número de teléfono del usuario no está disponible.", Alert.AlertType.WARNING);
            }
        } else {
            controladorPrincipal.mostrarAlerta("No se pudo encontrar la persona con el ID proporcionado.", Alert.AlertType.WARNING);
        }
    }

}
