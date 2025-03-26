package model;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id_usuario;
    private String nome;
    private String email;
    private String senha;


    public Usuario() {
    }

    


    public Usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }




    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }




    public Usuario(int id_usuario, String nome) {
        this.id_usuario = id_usuario;
        this.nome = nome;
    }




    public Usuario(int id_usuario, String nome, String email, String senha) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }


    public int getId() {
        return id_usuario;
    }

    public void setId(int id_usuario) {
        this.id_usuario = id_usuario;
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

    public String getSenha() {
        return senha;
    }

    public void setSenhaHash(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id_usuario == usuario.id_usuario;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id_usuario);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id_usuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
