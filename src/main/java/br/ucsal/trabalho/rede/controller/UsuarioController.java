package br.ucsal.trabalho.rede.controller;

import br.ucsal.trabalho.rede.dto.UsuarioUpdateDto;
import br.ucsal.trabalho.rede.service.UsuarioService;
import br.ucsal.trabalho.rede.dto.UsuarioCreateDto;
import br.ucsal.trabalho.rede.entity.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(required = false) String email) {
        if (email != null) {
            Usuario usuario = usuarioService.findByEmail(email);
            if (usuario == null) {
                return ResponseEntity.status(404).body(Map.of("erro", "Usuário não encontrado"));
            }
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.ok(usuarioService.findAll());
        }
    }



    // GET /id - obter um
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable("id") int index) {
        Optional<Usuario> resultado = usuarioService.findById(index);
        return ResponseEntity.of(resultado);
    }
    // POST - criar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario add(@RequestBody @Valid UsuarioCreateDto usuarioCreateDto) {
        return usuarioService.add(usuarioCreateDto);
    }
    // PUT /id - modificar
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") int index, @RequestBody UsuarioUpdateDto dto) {
        usuarioService.update(index, dto);
    }
    // DELETE /id - apagar
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int index) {
        usuarioService.delete(index);
    }
}
