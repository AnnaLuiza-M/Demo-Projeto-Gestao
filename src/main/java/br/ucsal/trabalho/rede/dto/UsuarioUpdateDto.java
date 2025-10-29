package br.ucsal.trabalho.rede.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UsuarioUpdateDto {
//    @Size(min = 3)
    private String nome;
//    @Email
    private String email;

    public UsuarioUpdateDto() {
    }

    public UsuarioUpdateDto(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
