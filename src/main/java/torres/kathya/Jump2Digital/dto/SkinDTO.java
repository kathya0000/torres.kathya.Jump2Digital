package torres.kathya.Jump2Digital.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class SkinDTO {
    private Long id;
    private String nombre;
    private String tipo;
    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser mayot que cero")
    private double precio;
    @NotBlank(message = "El color no puede estar vacio")
    private String color;
}
