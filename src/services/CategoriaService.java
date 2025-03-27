package services;

import java.util.List;

import dao.interfaces.CategoriaDao;
import model.Categoria;
import model.Usuario;
import exceptions.CategoriaException;

public class CategoriaService {

    private CategoriaDao categoriaDao;

    public CategoriaService(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }


    public void adicionarCategoria(Categoria categoria) {

        if (categoriaDao.findByCategoriaId(categoria.getId()) != null) {
            throw new CategoriaException("Categoria já existe.");
        }

        categoriaDao.insert(categoria);
    }

    public void removerCategoria(Integer id) {
        Categoria categoria = categoriaDao.findByCategoriaId(id);
        if (categoria == null) {
            throw new CategoriaException("Categoria não encontrada.");
        }

        categoriaDao.deleteById(id);
    }

    public void atualizarCategoria(Categoria categoria) {
        if (categoria.getId() <= 0) {
            throw new CategoriaException("ID da categoria inválido para atualização.");
        }

        if (categoriaDao.findByCategoriaId(categoria.getId()) == null) {
            throw new CategoriaException("Categoria não encontrada.");
        }

        categoriaDao.update(categoria);
    }

    public Categoria buscarCategoriaPorId(int id) {
        Categoria categoria = categoriaDao.findByCategoriaId(id);
        if (categoria == null) {
            throw new CategoriaException("Categoria não encontrada.");
        }
        return categoria;
    }

    public List<Categoria> findByUsuario(Usuario usuario) {
        List<Categoria> list = categoriaDao.findByUsuario(usuario);

        if (list == null) {
            throw new CategoriaException("Não encontrada a categoria pelo usuário.");
        }

        return list;
    }

    public List<Categoria> findAll(){
        List<Categoria> list = categoriaDao.findAll();

        if (list == null) {
            throw new CategoriaException("Nenhuma categoria cadastrada.");
        }

        return list;
    }
}
