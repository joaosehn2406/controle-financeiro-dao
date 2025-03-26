package dao.interfaces;

import dao.impl.CategoriaDaoJdbc;
import dao.impl.MovimentacaoDaoJdbc;
import dao.impl.UsuarioDaoJdbc;
import dao.interfaces.CategoriaDao;
import dao.interfaces.MovimentacaoDao;
import dao.interfaces.UsuarioDao;
import db.DB;

public class DaoFactory {

    // Construa a conexão quando for preciso
    public UsuarioDao createUsuarioDao() {
        return new UsuarioDaoJdbc(DB.getConnection());
    }

    public CategoriaDao createCategoriaDao() {
        return new CategoriaDaoJdbc(DB.getConnection());
    }

    public MovimentacaoDao createMovimentacaoDao() {
        return new MovimentacaoDaoJdbc(DB.getConnection());
    }
}
