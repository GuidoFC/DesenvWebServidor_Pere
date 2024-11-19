package JOANGALMES.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

// @Data obtiene los getters y setters de la clase Tipus
@Entity
@Data
@Table(name = "tipus")
public class Tipus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String nom;

    // set: no permite elementos duplicados
    @OneToMany(mappedBy = "tipusAnimalAA")
    private Set<Animal> animals;


}
