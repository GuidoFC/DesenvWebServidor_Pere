package JOANGALMES.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    @Column
    private String numRegistre;
    @ManyToOne
    private Tipus tipusAnimalAA;

}
