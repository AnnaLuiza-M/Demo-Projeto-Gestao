package br.ucsal.trabalho.rede.controller;

import br.ucsal.trabalho.rede.entity.Usuario;
import br.ucsal.trabalho.rede.repository.UsuarioRepository;
import br.ucsal.trabalho.rede.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authManager, JwtService jwtService,
                          UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String senha = loginRequest.get("senha");

        try {
            // 1️⃣ Autentica o usuário
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, senha)
            );

            // 2️⃣ Busca o usuário no banco
            Usuario usuario = usuarioRepository.findByEmail(email);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("erro", "Usuário não encontrado"));
            }

            // 3️⃣ Gera token JWT
            String token = jwtService.generateToken(usuario);

            // 4️⃣ Retorna token em JSON
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "usuario", usuario.getNome(),
                    "email", usuario.getEmail()
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("erro", "Email ou senha inválidos"));
        }
    }


}
