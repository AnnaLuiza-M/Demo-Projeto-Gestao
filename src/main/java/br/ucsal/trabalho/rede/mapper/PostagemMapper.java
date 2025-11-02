package br.ucsal.trabalho.rede.mapper;

import br.ucsal.trabalho.rede.dto.PostagemCreateDto;
import br.ucsal.trabalho.rede.dto.PostagemResponseDto;
import br.ucsal.trabalho.rede.entity.Postagem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PostagemMapper {

    Postagem toEntity(PostagemCreateDto dto);

    @Mapping(source = "criador.nome", target = "criadorNome")
    PostagemResponseDto toResponseDto(Postagem postagem);

    List<PostagemResponseDto> toResponseList(List<Postagem> postagens);
}
