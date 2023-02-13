package mx.edu.utez.web.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class PersonasDTO {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellidoPaterno;
    @NotBlank
    private String apellidoMaterno;
    @NotBlank
    private String email;


}