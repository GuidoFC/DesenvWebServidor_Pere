package principal.demo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Asteroide")
public class Asteroide {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // genera el id de forma automatica
    private Long id;
    @Column(name = "name")
    private String nombre;
    @Column(name = "absolute_magnitude")
    private double magnitud;
    @Column(name = "diameter_km_average")
    private double diameter_km_average;
    @Column(name = "is_potentially_hazardous")
    private boolean esPeligroso;
    // TODO tengo que hacer un calculo
//    private double mediaDiametro;


    public Asteroide() {
    }

    public Asteroide(Long id, String nombre, double magnitud, double diameter_km_average, boolean esPeligroso) {
        this.id = id;
        this.nombre = nombre;
        this.magnitud = magnitud;
        this.diameter_km_average = diameter_km_average;
        this.esPeligroso = esPeligroso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }

    public double getDiameter_km_average() {
        return diameter_km_average;
    }

    public void setDiameter_km_average(double diameter_km_average) {
        this.diameter_km_average = diameter_km_average;
    }

    public boolean isEsPeligroso() {
        return esPeligroso;
    }

    public void setEsPeligroso(boolean esPeligroso) {
        this.esPeligroso = esPeligroso;
    }
}
