package org.example.servicio;

import co.edu.uniquindio.envio.modelo.EnvioHistorico;
import co.edu.uniquindio.envio.modelo.Estados;
import co.edu.uniquindio.envio.modelo.Factura;
import co.edu.uniquindio.envio.modelo.Paquete;
import co.edu.uniquindio.envio.modelo.Persona;
import co.edu.uniquindio.envio.modelo.enums.TipoEnvio;

import java.time.LocalDate;

/**
 * Interfaz que define los servicios que se pueden realizar en el hotel
 */
public interface EnvioServicio {
    Persona agregarPersonas(String cedula,String nombre,String direccion,String ciudad,String numero,String correo)throws Exception;
    Persona obtenerPersonas(String cedula)throws Exception;
    void actualizarPersona(String cedula,String nombre,String direccion,String ciudad,String numero,String correo)throws Exception;
    Paquete agregarPaquete(String descripcion,float peso) throws Exception;

    String generarCodigo(TipoEnvio tipo);

    double calcularPrecio(float distancia, TipoEnvio tipo, float peso, int cantidadPaquetes);
//    double calcularPrecio(float distancia, TipoEnvio tipo, float peso, int cantidadPaquetes);

//  recibir sms
//    public interface Notificacion {
//        void enviarNotificacion();
//    }

}