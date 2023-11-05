package torres.kathya.Jump2Digital.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

@Entity
@Table(name="skin")
@Getter
@Setter
public class SkinModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 3, max = 50, message = "el nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El tipo no puede estar vacio")
    @Size(min = 3, max = 50, message = "El tipo debe tener entre 3 y 50 caracteres")
    private String tipo;

    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = " El precio debe ser mayot uqe cero ")
    @Digits(integer = 6, fraction = 2, message = "El precio no es valido")
    private double precio;

    @NotBlank(message = "El color no puede estar vacio")
    @Size(min = 3, max = 30, message = "El color debe tener entre 3 y 30 caracteres")
    private String color;
}
