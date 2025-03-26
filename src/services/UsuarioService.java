package services;

import dao.interfaces.UsuarioDao;
import model.Usuario;


public class UsuarioService {
    
    private UsuarioDao usuarioDao;

    public UsuarioService() {
    }

    public UsuarioService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public void adicionarUsuario(Usuario usuario){



    }
}
