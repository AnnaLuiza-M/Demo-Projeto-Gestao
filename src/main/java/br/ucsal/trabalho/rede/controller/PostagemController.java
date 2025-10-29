package br.ucsal.trabalho.rede.controller;

import br.ucsal.trabalho.rede.entity.Postagem;
import br.ucsal.trabalho.rede.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PostagemController {
    @Autowired
    private PostagemService postagemService;

    @GetMapping("/postagens")
    public List<Postagem> findAll() {
        return postagemService.findAll();
    }

    @GetMapping("/usuarios/{usuarioId}/postagens")
    public List<Postagem> findByUsuarioId(@PathVariable("usuarioId") long usuarioId) {
        return postagemService.findByUsuarioId(usuarioId);
    }
}
