package mx.edu.utez.repo;


import mx.edu.utez.model.Personas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonasRepository  extends JpaRepository<Personas,Integer> {

    Optional<Personas> findOneByEmail(String email);
    boolean existsByEmail(String email);
}

