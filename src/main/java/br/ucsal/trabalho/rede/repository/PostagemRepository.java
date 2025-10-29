package br.ucsal.trabalho.rede.repository;

import br.ucsal.trabalho.rede.entity.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    public List<Postagem> findByCriadorId(Long id);
}
