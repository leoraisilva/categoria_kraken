package br.com.kraken.categoria.java.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "categoria")
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoriaId;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "imagem")
    private String imagem;

    public CategoriaModel(UUID categoriaId, String titulo, String descricao, String imagem) {
        this.categoriaId = categoriaId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;
    }
    public CategoriaModel (){}

    public String getTitulo() {
        return titulo;
    }

    public UUID getCategoriaId() {
        return categoriaId;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
