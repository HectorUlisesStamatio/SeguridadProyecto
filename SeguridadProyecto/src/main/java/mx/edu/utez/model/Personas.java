package mx.edu.utez.model;


import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Personas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersona")
    private Integer id;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;

    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_act")
    private LocalDateTime fechaActualizacion;


    @PrePersist
    private void prePersist(){
        this.fechaCreacion = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        this.fechaActualizacion = LocalDateTime.now();
    }
}
