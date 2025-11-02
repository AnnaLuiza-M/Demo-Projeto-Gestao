package br.ucsal.trabalho.rede.service;

import br.ucsal.trabalho.rede.dto.UsuarioUpdateDto;
import br.ucsal.trabalho.rede.exception.NotFoundException;
import br.ucsal.trabalho.rede.mapper.UsuarioMapper;
import br.ucsal.trabalho.rede.repository.UsuarioRepository;
import br.ucsal.trabalho.rede.dto.UsuarioCreateDto;
import br.ucsal.trabalho.rede.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario add(UsuarioCreateDto dto) {
        Usuario usuario = UsuarioMapper.INSTANCE.usuarioCreateDtoToUsuario(dto);
//        Usuario usuario = new Usuario();
//        usuario.setEmail(dto.getEmail());
//        usuario.setNome(dto.getNome());
        return usuarioRepository.save(usuario);
    }

    public void update(long id, UsuarioUpdateDto dto) {
        Usuario usuario = findById(id).orElseThrow(() -> new NotFoundException("Não existe usuário com id = " + id));
        UsuarioMapper.INSTANCE.updateEntityFromDto(dto, usuario);
        usuario.setId(id);

        System.out.println("USUARIO");
        System.out.println(usuario);
        usuarioRepository.save(usuario);
//        if (!exists(index)) {
//            throw new NotFoundException("Não existe usuário com id = " + index);
//        }
//        usuarios.set(index, usuario);
    }

    public void delete(long id) {
        usuarioRepository.deleteById(id);
    }
}
