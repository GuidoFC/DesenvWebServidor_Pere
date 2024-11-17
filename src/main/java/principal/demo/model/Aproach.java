package principal.demo.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Aproach")
public class Aproach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;  // Identificador único autogenerado


    private java.sql.Date approach_date;
    // @Column tiene q poner el nombre de la base de datos. He puesto aqui un ejemplo
    // pq los otros atributos coinciden con el nombre de la tabla
    @Column(name = "velocity")
    private Float velocidad;
    private Float distance;
    private String orbiting_body;

    // JoinColumn se pone el nombre de la FK que tenemos en la base de datos.
    // con el ManyToOne especificamos la BIDIRECCIONALIDAD
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "asteroid_id", nullable = false)
    private Asteroide asteroideAAA;

    // constructor
    public Aproach() {
    }

    public Aproach(Date approach_date, Float velocidad, Float distance, String orbiting_body, Asteroide asteroideAAA) {
        this.approach_date = approach_date;
        this.velocidad = velocidad;
        this.distance = distance;
        this.orbiting_body = orbiting_body;
        this.asteroideAAA = asteroideAAA;
    }

    // getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getApproach_date() {
        return approach_date;
    }

    public void setApproach_date(Date approach_date) {
        this.approach_date = approach_date;
    }

    public Float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Float velocity) {
        this.velocidad = velocity;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public String getOrbiting_body() {
        return orbiting_body;
    }

    public void setOrbiting_body(String orbiting_body) {
        this.orbiting_body = orbiting_body;
    }

    public Asteroide getAsteroideAAA() {
        return asteroideAAA;
    }

    public void setAsteroideAAA(Asteroide asteroideAAA) {
        this.asteroideAAA = asteroideAAA;
    }

    @Override
    public String toString() {
        return "Aproach{" +
                "id=" + id +
                ", approach_date=" + approach_date +
                ", velocidad=" + velocidad +
                ", distance=" + distance +
                ", orbiting_body='" + orbiting_body +
                '}';
    }
}