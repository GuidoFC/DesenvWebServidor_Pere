package PildoraInformatica;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO para ignorar un campo del Json que no queremos agregar
// tenemos la edad en el json pero no aqui en el Modelo
@JsonIgnoreProperties(ignoreUnknown = true)
public class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private boolean activo;
    private String[] idiomas;

    // todo Tenemos que coger informacion del Json Anidado
    private Datos_Empleado datos_registro;


    public Empleado() {}

    public Datos_Empleado getDatos_registro() {
        return datos_registro;
    }

    public void setDatos_registro(Datos_Empleado datos_registro) {
        this.datos_registro = datos_registro;
    }

    public String[] getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String[] idiomas) {
        this.idiomas = idiomas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
