package co.edu.uniquindio.envio.modelo;
import co.edu.uniquindio.envio.modelo.enums.TipoEnvio;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Random;

@Getter
public class Envios {
    private final ArrayList<Persona> personas;
    private final ArrayList<Paquete> paquetes;
    private final  ArrayList<EnvioHistorico> envioHistory;

    public Envios() {
        this.personas = new ArrayList<>();
        this.paquetes = new ArrayList<>();
        this.envioHistory = new ArrayList<>();
        llenarDatosPrueba();
    }

    private void llenarDatosPrueba(){
        try{
            agregarPersonas("1003534487","anye","Mz R","Armenia","3107316994","anyiedcondiza@gmail.com");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void agregarPersonas(String cedula,String nombre,String direccion,String ciudad,String numero,String correo)throws Exception{
        if(cedula == null || cedula.isBlank()){
            throw new Exception("El número de identificación es obligatorio");
        }

        if(nombre == null || nombre.isBlank()){
            throw new Exception("El nombre es obligatorio");
        }

        if(direccion == null || direccion.isBlank()){
            throw new Exception("La dirección es obligatoria");
        }

        if(ciudad == null || ciudad.isBlank()){
            throw new Exception("La ciudad es obligatoria");
        }

        Persona persona = Persona.builder()
                .cedula(cedula)
                .nombre(nombre)
                .direccion(direccion)
                .ciudad(ciudad)
                .numero(numero)
                .correo(correo)
                .build();

        personas.add(persona);
    }
    public Persona obtenerPersonas(String cedula){
        for(int perso = 0; perso < personas.size(); perso ++){
            if(personas.get(perso).getCedula().equals(cedula)){
                return personas.get(perso);
            }
        }
        return null;
    }
    public void actualizarPersona(String cedula,String nombre,String direccion,String ciudad,String numero,String correo)throws Exception{
        if(cedula == null || cedula.isBlank()){
            throw new Exception("El número de identificación es obligatorio");
        }

        if(nombre == null || nombre.isBlank()){
            throw new Exception("El nombre es obligatorio");
        }

        if(direccion == null || direccion.isBlank()){
            throw new Exception("La dirección es obligatoria");
        }

        if(ciudad == null || ciudad.isBlank()){
            throw new Exception("La ciudad es obligatoria");
        }
        for (int perso = 0; perso < personas.size(); perso ++){
            if(personas.get(perso).getCedula().equals(cedula)){
                Persona persona = new Persona(cedula, nombre, direccion, ciudad, numero, correo);
                personas.set(perso, persona);
                break;
            }
        }
    }

    public void agregarPaquete(String descripcion,float peso)throws Exception{
        if(descripcion == null || descripcion.isBlank()){
            throw new Exception("La descripción es obligatorio");
        }
//        if(peso == null || peso.isBlank()){
//            throw new Exception("El peso es obligatorio");
//        }
        Paquete paquete = Paquete.builder()
                .descripcion(descripcion)
                .peso(peso)
                .build();
        paquetes.add(paquete);
    }

    private static final double PRECIO_BASE_EXPRESS = 10.00;
    private static final double PRECIO_BASE_ESTANDAR = 7.00;

    // Tarifas adicionales
    private static final double TARIFA_ADICIONAL_ESTANDAR = 2.00;
    private static final double TARIFA_ADICIONAL_EXPRESS = 3.00;
    private static final double TARIFA_DISTANCIA_ESTANDAR = 2.00;
    private static final double TARIFA_DISTANCIA_EXPRESS = 4.00;

    // Descuentos y promociones
    private static final double DESCUENTO_VOLUMEN_ESTANDAR = 0.10;
    private static final double DESCUENTO_VOLUMEN_EXPRESS = 0.15;

    // Impuestos y tasas
    private static final double IVA = 0.07;

    public double calcularPrecio(float distancia, TipoEnvio tipo, float peso, int cantidadPaquetes){
        double precioBase;
        double tarifaAdicional;
        double descuentoVolumen;

        // Seleccionar el precio base según el tipo de envío
        if (tipo == TipoEnvio.EXPRESS) {
            precioBase = PRECIO_BASE_EXPRESS;
            tarifaAdicional = TARIFA_ADICIONAL_EXPRESS;
            descuentoVolumen = DESCUENTO_VOLUMEN_EXPRESS;
        } else {
            precioBase = PRECIO_BASE_ESTANDAR;
            tarifaAdicional = TARIFA_ADICIONAL_ESTANDAR;
            descuentoVolumen = DESCUENTO_VOLUMEN_ESTANDAR;
        }

        // Calcular tarifa adicional por peso
        double pesoAdicional = Math.max(0, peso - (tipo == TipoEnvio.EXPRESS ? 2.0 : 2.5));
        double tarifaPesoAdicional = pesoAdicional * tarifaAdicional;

        // Calcular tarifa adicional por distancia
        double tarifaDistancia = (distancia > 500) ?
                (tipo == TipoEnvio.EXPRESS ? TARIFA_DISTANCIA_EXPRESS : TARIFA_DISTANCIA_ESTANDAR) : 0.0;

        // Calcular descuento por volumen
        double descuento = (cantidadPaquetes > 10) ? descuentoVolumen * precioBase : 0.0;

        // Aplicar descuento
        double precioSubtotal = precioBase + tarifaPesoAdicional + tarifaDistancia - descuento;

        // Calcular impuestos
        double impuestos = precioSubtotal * IVA;

        // Calcular precio total
        double precioTotal = precioSubtotal + impuestos;

        return precioTotal;
    }

    public String generarCodigo(TipoEnvio tipo){
        String pref = null;
        String numeroAleatorio = generarNumeroAleatorio();
        if(tipo == TipoEnvio.EXPRESS){
            pref ="31";
        } else if (tipo == TipoEnvio.ESTÁNDAR) {
            pref ="21";
        }
        String code = pref+numeroAleatorio;
        String obtenerCode = obtenerCodigo(code);
        if(obtenerCode != null){
            numeroAleatorio = generarNumeroAleatorio();
            code = pref+numeroAleatorio;
        }
        return code;
    }
    private String generarNumeroAleatorio() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(1000000);
        return String.format("%06d", numeroAleatorio);
    }
    public String obtenerCodigo(String codigo){
        String codigoEnv = null;
        for(int enviHist = 0; enviHist < envioHistory.size(); enviHist ++){
            if(envioHistory.get(enviHist).getCodigoEnvio().equals(codigo)){
                codigoEnv = envioHistory.get(enviHist).getCodigoEnvio();
            }
        }
        return codigoEnv;
    }
}


















