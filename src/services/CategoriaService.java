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
        categoriaAuth.validarCategoria(categoria);


        if (categoriaAuth.verificarCategoriaUnica(categoria)) {
            throw new CategoriaException("Categoria já existe.");
        }


        categoriaDao.insert(categoria);
    }


    public void removerCategoria(Integer id) {

        if (!categoriaAuth.verificarExistenciaCategoria(id)) {
            throw new CategoriaException("Categoria não encontrada.");
        }

        Categoria categoria = categoriaDao.findByCategoriaId(id);
        categoriaAuth.validarCategoriaParaDelecao(categoria);

        categoriaDao.deleteById(id);
    }


    public void atualizarCategoria(Categoria categoria) {

        if (categoria.getId() <= 0) {
            throw new CategoriaException("ID da categoria inválido para atualização.");
        }

        categoriaAuth.validarCategoriaParaAtualizacao(categoria);

        if (categoriaAuth.verificarCategoriaUnica(categoria)) {
            throw new CategoriaException("Categoria já existe.");
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
