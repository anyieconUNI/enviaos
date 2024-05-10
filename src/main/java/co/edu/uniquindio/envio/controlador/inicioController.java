package co.edu.uniquindio.envio.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class inicioController {
    private final ControladorPrincipal controladorPrincipal = ControladorPrincipal.getInstancia();


    public void realizarPedido(ActionEvent actionEvent) {
        controladorPrincipal.navegar("/emisor.fxml", "Enviaos");
    }

    public void paquetes(ActionEvent actionEvent) {
        controladorPrincipal.navegar("/seguimiento.fxml","Paquetes");
    }
}
