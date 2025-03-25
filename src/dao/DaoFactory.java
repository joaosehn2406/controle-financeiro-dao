package dao;


import dao.impl.UsuarioDaoJdbc;
import db.DB;

public class DaoFactory {

    public UsuarioDao createUsuarioDao(){
        return new UsuarioDaoJdbc(DB.getConnection());
    }
}
