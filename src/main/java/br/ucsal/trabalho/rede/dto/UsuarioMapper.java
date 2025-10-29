package br.ucsal.trabalho.rede.dto;

import br.ucsal.trabalho.rede.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioCreateDto usuarioToUsuarioCreteDto(Usuario usuario);
    UsuarioUpdateDto usuarioToUsuarioUpdateDto(Usuario usuario);

    Usuario usuarioCreateDtoToUsuario(UsuarioCreateDto dto);
    Usuario usuarioUpdateDtoToUsuario(UsuarioUpdateDto dto);

    @Mapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, target = "email")
    @Mapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, target = "nome")
    void updateEntityFromDto(UsuarioUpdateDto dto, @MappingTarget Usuario entity);
}

