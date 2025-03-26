package services;

import auth.CategoriaAuth;
import dao.interfaces.CategoriaDao;
import model.Categoria;
import exceptions.CategoriaException;

public class CategoriaService {

    private CategoriaDao categoriaDao;
    private CategoriaAuth categoriaAuth;

    public CategoriaService() {}

    public CategoriaService(CategoriaDao categoriaDao, CategoriaAuth categoriaAuth) {
        this.categoriaDao = categoriaDao;
        this.categoriaAuth = categoriaAuth;
    }


    public void adicionarCategoria(Categoria categoria) {
        categoriaAuth.validateCategoria(categoria);  


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

        categoriaAuth.validateCategoria(categoria);

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
}
