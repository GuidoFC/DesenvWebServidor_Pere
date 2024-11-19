package principal.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// @Data obtiene los getters y setters de la clase Asteroide
@Data
@Entity
@Table(name = "Asteroide")
public class Asteroide {
// https://www.youtube.com/watch?v=7UItZlS9Vkk&ab_channel=pildorasinformaticas
    // Explicacion del ManyToOne
// https://www.youtube.com/watch?v=XPbBIpK8a9o&ab_channel=pildorasinformaticas
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera el id de forma automatica
    private Long id;
    @Column(name = "name")
    // TODO Crear en la base de datos
    private Long Id_Nasa;
    private String nombre;
    @Column(name = "absolute_magnitude")
    private double magnitud;
    @Column(name = "diameter_km_average")
    private double diameter_km_average;
    @Column(name = "is_potentially_hazardous")
    private boolean esPeligroso;
    // TODO tengo que hacer un calculo
//    private double mediaDiametro;


    @OneToMany(mappedBy = "asteroideAAA", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Aproach> listaAvistamientos ;


    public Asteroide() {
    }

    // Por si quiero yo crear un Asteroide
    public Asteroide(String nombre, double magnitud, double diameter_km_average, boolean esPeligroso) {
        this.nombre = nombre;
        this.magnitud = magnitud;
        this.diameter_km_average = diameter_km_average;
        this.esPeligroso = esPeligroso;
    }

    // Por si insertamos desde una API
    public Asteroide(Long id, String nombre, double magnitud, double diameter_km_average, boolean esPeligroso) {
        this.id = id;
        this.nombre = nombre;
        this.magnitud = magnitud;
        this.diameter_km_average = diameter_km_average;
        this.esPeligroso = esPeligroso;
    }



    @Override
    public String toString() {
        return "Asteroide{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", magnitud=" + magnitud +
                ", diameter_km_average=" + diameter_km_average +
                ", esPeligroso=" + esPeligroso +
                ", listaAvistamientos=" + listaAvistamientos +
                '}';
    }

    public void agregarAproach(Aproach aproach) {
        if (listaAvistamientos == null) {
            listaAvistamientos = new ArrayList<>();
        }
        listaAvistamientos.add(aproach);

        aproach.setAsteroideAAA(this);
    }
}
