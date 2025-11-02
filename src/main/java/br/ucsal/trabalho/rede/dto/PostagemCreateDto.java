package br.ucsal.trabalho.rede.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostagemCreateDto {

    @NotNull
    @Size(min = 3)
    private String titulo;

    @NotNull
    @Size(min = 10)
    private String conteudo;

    @NotNull
    private Long criadorId; // id do usuário autor

    public PostagemCreateDto() {}

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    public Long getCriadorId() { return criadorId; }
    public void setCriadorId(Long criadorId) { this.criadorId = criadorId; }
}
