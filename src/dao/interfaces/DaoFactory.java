package dao.interfaces;


import dao.impl.CategoriaDaoJdbc;
import dao.impl.MovimentacaoDaoJdbc;
import dao.impl.UsuarioDaoJdbc;
import db.DB;

public class DaoFactory {

    public UsuarioDao createUsuarioDao(){
        return new UsuarioDaoJdbc(DB.getConnection());
    }

    public CategoriaDao creatCategoriaDao(){
        return new CategoriaDaoJdbc(DB.getConnection());
    }

    public MovimentacaoDao createMovimentacaoDao(){
        return new MovimentacaoDaoJdbc(DB.getConnection());
    }
}
