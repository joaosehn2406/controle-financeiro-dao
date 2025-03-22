package model;

import java.io.Serializable;

public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id_categoria;
    private String nome;

    private Usuario usuario;

    public Categoria() {}

    public Categoria(int id, String nome, Usuario usuario) {
        this.id_categoria = id_categoria;
        this.nome = nome;
        this.usuario = usuario;
    }

    public int getId() {
        return id_categoria;
    }

    public void setId(int id) {
        this.id_categoria = id_categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Categoria{id=" + id_categoria + ", nome='" + nome + "'}";
    }
}
