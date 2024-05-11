package org.example.servicio;

import co.edu.uniquindio.envio.modelo.EnvioHistorico;
import co.edu.uniquindio.envio.modelo.Estados;
import co.edu.uniquindio.envio.modelo.Factura;
import co.edu.uniquindio.envio.modelo.Paquete;
import co.edu.uniquindio.envio.modelo.Persona;
import co.edu.uniquindio.envio.modelo.enums.Ciudad;
import co.edu.uniquindio.envio.modelo.enums.TipEstado;
import co.edu.uniquindio.envio.modelo.enums.TipoEnvio;

import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz que define los servicios que se pueden realizar en el hotel
 */
public interface EnvioServicio {
    Persona agregarPersonas(String cedula,String nombre,String direccion,String ciudad,String numero,String correo)throws Exception;
    Persona obtenerPersonas(String cedula)throws Exception;
    void actualizarPersona(String cedula,String nombre,String direccion,String ciudad,String numero,String correo)throws Exception;
    Paquete agregarPaquete(String descripcion,float peso) throws Exception;

    String generarCodigo(TipoEnvio tipo);

    double calcularPrecio(float distancia, TipoEnvio tipo, float peso, int cantidadPaquetes) throws Exception;
    EnvioHistorico crearHistorial(String codigoEnvio, String remitente, String destinatario, List<Paquete> paquetes, TipoEnvio tipo, Ciudad ciudad, TipEstado estados, LocalDate fecha, float distancia, float valor)throws Exception;
    public List<EnvioHistorico> filtrarDatos(LocalDate fecha, TipoEnvio tipo, TipEstado estado);
    public List<EnvioHistorico> datos();
    public EnvioHistorico cargarEnvio(String codigo);
    public void actualizarEnvio(String codigo, TipEstado estado)throws Exception;
}