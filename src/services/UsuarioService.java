package services;

import java.util.List;

import dao.interfaces.UsuarioDao;
import exceptions.UsuarioException;
import model.Usuario;


public class UsuarioService {
    
    private UsuarioDao usuarioDao;
    private Usuario usuario;

    public UsuarioService() {
    }

    public UsuarioService(Usuario usuario) {
        this.usuario = usuario;
    }


    public UsuarioService(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public void adicionarUsuario(Usuario usuario){


        usuarioDao.insert(usuario);

    }

    public void removerUsuario(Integer id) {

   
        usuarioDao.deleteByUsuarioId(id);
    }

    public void atualizarUsuario(Usuario usuario) {
        if (usuario.getId() <= 0) {
            throw new UsuarioException("ID de usuário inválido");
        }

        usuarioDao.update(usuario);
    }

    public Usuario findByUsuarioId(Integer id) {
        Usuario u = usuarioDao.findByUsuarioId(id);

        if (u == null) {
            throw new UsuarioException("Não encontrado o usuário.");
        }
        return u;
    }

    public List<Usuario> findByUsuarioEmail(String email) {
        List<Usuario> list = usuarioDao.findByEmail(email);

        if (list == null) {
            throw new UsuarioException("Não encontrado o usuário.");
        }

        return list;
    }

    public List<Usuario> findAll(){

        return usuarioDao.findAll();
    }

    
}
