package principal.demo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Asteroide {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // genera el id de forma automatica
    private String id;
    private String nombre;
    private String magnitud;
    private String diametro;
    private boolean esPeligroso;
    private double mediaDiametro;


    public Asteroide() {

    }

    public Asteroide(String id, String nombre, String magnitud, String diametro, boolean esPeligroso, double mediaDiametro) {
        this.id = id;
        this.nombre = nombre;
        this.magnitud = magnitud;
        this.diametro = diametro;
        this.esPeligroso = esPeligroso;
        this.mediaDiametro = mediaDiametro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }

    public String getDiametro() {
        return diametro;
    }

    public void setDiametro(String diametro) {
        this.diametro = diametro;
    }

    public boolean isEsPeligroso() {
        return esPeligroso;
    }

    public void setEsPeligroso(boolean esPeligroso) {
        this.esPeligroso = esPeligroso;
    }

    public double getMediaDiametro() {
        return mediaDiametro;
    }

    public void setMediaDiametro(double mediaDiametro) {
        this.mediaDiametro = mediaDiametro;
    }
}
