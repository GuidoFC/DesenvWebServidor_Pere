package principal.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
// @Data obtiene los getters y setters de la clase Asteroide
@Data
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


    public Aproach(Long id, Date approach_date, Float velocidad, Float distance, String orbiting_body) {
        this.id = id;
        this.approach_date = approach_date;
        this.velocidad = velocidad;
        this.distance = distance;
        this.orbiting_body = orbiting_body;

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