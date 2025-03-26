package auth;

import model.Categoria;
import exceptions.CategoriaException;

public class CategoriaAuth {

    
    public void validateCategoria(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
            throw new CategoriaException("Nome da categoria não pode ser vazio.");
        }

        if (categoria.getUsuario() == null || categoria.getUsuario().getId() <= 0) {
            throw new CategoriaException("A categoria deve estar associada a um usuário válido.");
        }

        if (categoria.getId() <= 0) {
            throw new CategoriaException("ID da categoria inválido.");
        }


        if (!isCategoriaNameLengthValid(categoria.getNome())) {
            throw new CategoriaException("O nome da categoria deve ter entre 3 e 50 caracteres.");
        }
    }


    public boolean isCategoriaNameValid(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    public boolean isCategoriaNameLengthValid(String nome) {
        return nome != null && nome.length() >= 3 && nome.length() <= 50;
    }
}
