package principal.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Aproach")
public class Aproach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único autogenerado

    private java.sql.Date approach_date;  // Fecha del avistamiento
    private Float velocity;               // Velocidad en km/s
    private Float distance;               // Distancia en unidades astronómicas
    private String orbiting_body;         // Cuerpo celeste alrededor del cual orbita


}