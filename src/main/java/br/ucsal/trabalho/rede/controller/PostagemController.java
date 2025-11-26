package br.ucsal.trabalho.rede.controller;

import br.ucsal.trabalho.rede.dto.PostagemCreateDto;
import br.ucsal.trabalho.rede.dto.PostagemResponseDto;
import br.ucsal.trabalho.rede.service.PostagemService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    private final PostagemService postagemService;

    public PostagemController(PostagemService postagemService) {
        this.postagemService = postagemService;
    }

    @PostMapping
    public PostagemResponseDto criar(@Valid @RequestBody PostagemCreateDto dto) {
        return postagemService.criar(dto);
    }

    @GetMapping
    public List<PostagemResponseDto> listar(@RequestParam(required = false) String termo) {
        return postagemService.listar(termo);
    }

    // GET /postagens/por-criador/1
    @GetMapping("/por-criador/{criadorId}")
    public List<PostagemResponseDto> listarPorCriador(@PathVariable Long criadorId) {
        return postagemService.findByCriadorId(criadorId);
    }

}
