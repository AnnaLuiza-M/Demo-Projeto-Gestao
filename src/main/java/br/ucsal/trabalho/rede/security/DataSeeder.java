package br.ucsal.trabalho.rede.security;

import br.ucsal.trabalho.rede.entity.Usuario;
import br.ucsal.trabalho.rede.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            criarUsuarioSeNaoExistir(usuarioRepository, passwordEncoder,
                    "Anna Luíza", "anna@teste.com", "123456");

            criarUsuarioSeNaoExistir(usuarioRepository, passwordEncoder,
                    "Diego Gabriel", "diego@teste.com", "123456");

            criarUsuarioSeNaoExistir(usuarioRepository, passwordEncoder,
                    "Pedro Lucas", "pedro@teste.com", "123456");

            criarUsuarioSeNaoExistir(usuarioRepository, passwordEncoder,
                    "Andressa Galvão", "andressa@teste.com", "123456");

            System.out.println("✅ Usuários padrão verificados/criados com sucesso!");
        };
    }

    private void criarUsuarioSeNaoExistir(UsuarioRepository repo, PasswordEncoder encoder,
                                          String nome, String email, String senha) {

        if (repo.findByEmail(email) == null) { // ✅ correção
            Usuario user = new Usuario();
            user.setNome(nome);
            user.setEmail(email);
            user.setSenha(encoder.encode(senha));
            repo.save(user);
            System.out.println("🟣 Usuário criado: " + nome + " (" + email + ")");
        } else {
            System.out.println("🔵 Usuário já existe: " + email);
        }
    }

}
