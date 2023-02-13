package mx.edu.utez.repo;

import mx.edu.utez.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findOneByEmail(String email);
    boolean existsByEmail(String email);

}
