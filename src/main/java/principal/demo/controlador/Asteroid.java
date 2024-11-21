package principal.demo.controlador;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Asteroid { // lo ha generado solo
    @Id
    @Size(max = 255)
    @Column(name = "Id", nullable = false)
    private String id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "absolute_magnitude", nullable = false)
    private Float absoluteMagnitude;

    @Column(name = "diameter_km_average")
    private Float diameterKmAverage;

    @NotNull
    @Column(name = "is_potentially_hazardous", nullable = false)
    private Boolean isPotentiallyHazardous = false;

}