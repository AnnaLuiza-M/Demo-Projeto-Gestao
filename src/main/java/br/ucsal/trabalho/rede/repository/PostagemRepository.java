package br.ucsal.trabalho.rede.repository;

import br.ucsal.trabalho.rede.entity.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    // 🔹 Buscar postagens por ID do criador
    List<Postagem> findByCriadorId(Long id);

    // 🔹 Buscar postagens contendo o termo no título OU no conteúdo
    @Query("SELECT p FROM Postagem p WHERE " +
            "LOWER(p.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(p.conteudo) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Postagem> buscarPorTermo(@Param("termo") String termo);
}
