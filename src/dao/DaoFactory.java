package dao;


import dao.impl.UsuarioDaoJdbc;
import db.DbConnection;

public class DaoFactory {

    public UsuarioDao createUsuarioDao(){
        return new UsuarioDaoJdbc(DbConnection.getConnection());
    }
}
