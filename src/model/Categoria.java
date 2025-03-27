package model;

import java.io.Serializable;
import java.util.Objects;

public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id_categoria;
    private String nome;

    private Usuario usuario;

    public Categoria() {}

    public Categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(Usuario usuario, String nome, int id_categoria) {
        this.usuario = usuario;
        this.nome = nome;
        this.id_categoria = id_categoria;
    }

    public int getId() {
        return id_categoria;
    }

    public void setId(int id_categoria) {
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id_categoria == categoria.id_categoria;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id_categoria);
    }

    @Override
    public String toString() {
        return "Categoria{id=" + id_categoria + ", nome='" + nome + "'}";
    }
}
