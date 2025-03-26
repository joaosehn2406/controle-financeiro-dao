package auth;

import dao.interfaces.UsuarioDao;
import model.Usuario;

public class UsuarioAuth {
    
    private UsuarioDao usuarioDao;

    public UsuarioAuth(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public boolean validarUsuarioExistente(Integer id) {

        Usuario usuario = usuarioDao.findByUsuarioId(id);
        return usuario != null;
    }

    public boolean verificarEmailExistente(String email) {
        Usuario usuario = usuarioDao.findByEmail(email);
        return usuario != null; 
    }


    
}
