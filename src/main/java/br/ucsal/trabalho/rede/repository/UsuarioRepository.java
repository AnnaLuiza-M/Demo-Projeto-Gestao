package br.ucsal.trabalho.rede.repository;

import br.ucsal.trabalho.rede.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public List<Usuario> findByEmail(String email);
}
