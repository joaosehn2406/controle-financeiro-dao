package auth;

import model.Categoria;
import model.Usuario;
import dao.interfaces.CategoriaDao;

public class CategoriaAuth {

    private CategoriaDao categoriaDao;

    public CategoriaAuth(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    public void validateCategoria(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria não pode ser vazio.");
        }

        if (categoria.getUsuario() == null || categoria.getUsuario().getId() <= 0) {
            throw new IllegalArgumentException("A categoria deve estar associada a um usuário válido.");
        }

        if (categoria.getId() <= 0) {
            throw new IllegalArgumentException("ID da categoria inválido.");
        }

        if (categoriaDao.findByCategoriaId(categoria.getId()) != null) {
            throw new IllegalArgumentException("Já existe uma categoria com este ID.");
        }
    }


    public boolean canDeleteCategoria(Usuario usuario, Categoria categoria) {
        return categoria.getUsuario().getId() == usuario.getId();
    }


    public void validateCategoriaNameUniqueness(Categoria categoria) {
        Categoria existingCategoria = categoriaDao.findByCategoriaId(categoria.getId());
        if (existingCategoria != null) {
            throw new IllegalArgumentException("Já existe uma categoria com o mesmo ID.");
        }
    }

    public boolean isCategoriaNameValid(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    public boolean isCategoriaNameLengthValid(String nome) {
        return nome != null && nome.length() >= 3 && nome.length() <= 50;
    }
}
