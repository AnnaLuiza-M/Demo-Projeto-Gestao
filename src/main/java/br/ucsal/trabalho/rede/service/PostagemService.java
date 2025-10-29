package br.ucsal.trabalho.rede.service;

import br.ucsal.trabalho.rede.entity.Postagem;
import br.ucsal.trabalho.rede.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostagemService {
    @Autowired
    private PostagemRepository postagemRepository;

    public List<Postagem> findAll() {
        return postagemRepository.findAll();
    }
    public List<Postagem> findByUsuarioId(Long usuarioId) {
        return postagemRepository.findByCriadorId(usuarioId);
    }
}
