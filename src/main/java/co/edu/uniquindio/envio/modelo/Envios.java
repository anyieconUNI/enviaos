package co.edu.uniquindio.envio.modelo;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Envios {
    private final ArrayList<Persona> personas;
    public static Envios INSTANCIA;

    public static Envios getInstancia(){
        if(INSTANCIA == null){
            INSTANCIA = new Envios();
        }
        return INSTANCIA;
    }
    public Envios() {
        this.personas = new ArrayList<>();
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
}


















