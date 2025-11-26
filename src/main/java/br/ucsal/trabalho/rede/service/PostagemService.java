package br.ucsal.trabalho.rede.service;

import br.ucsal.trabalho.rede.dto.PostagemCreateDto;
import br.ucsal.trabalho.rede.dto.PostagemResponseDto;
import br.ucsal.trabalho.rede.entity.Postagem;
import br.ucsal.trabalho.rede.entity.Usuario;
import br.ucsal.trabalho.rede.exception.NotFoundException;
import br.ucsal.trabalho.rede.mapper.PostagemMapper;
import br.ucsal.trabalho.rede.repository.PostagemRepository;
import br.ucsal.trabalho.rede.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostagemService {

    private final PostagemRepository postagemRepository;
    private final UsuarioRepository usuarioRepository;
    private final PostagemMapper postagemMapper;

    public PostagemService(PostagemRepository postagemRepository, UsuarioRepository usuarioRepository, PostagemMapper postagemMapper) {
        this.postagemRepository = postagemRepository;
        this.usuarioRepository = usuarioRepository;
        this.postagemMapper = postagemMapper;
    }

    public PostagemResponseDto criar(PostagemCreateDto dto) {
        Usuario criador = usuarioRepository.findById(dto.getCriadorId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        Postagem postagem = postagemMapper.toEntity(dto);
        postagem.setCriador(criador);
        postagem.setDataPublicacao(LocalDate.now());

        postagemRepository.save(postagem);
        return postagemMapper.toResponseDto(postagem);
    }

    public List<PostagemResponseDto> listar(String termo) {
        List<Postagem> postagens = (termo == null || termo.isEmpty())
                ? postagemRepository.findAll()
                : postagemRepository.buscarPorTermo(termo);
        return postagemMapper.toResponseList(postagens);
    }

    public List<PostagemResponseDto> findByCriadorId(Long criadorId) {
        List<Postagem> postagens = postagemRepository.findByCriadorId(criadorId);
        return postagemMapper.toResponseList(postagens);
    }


}
