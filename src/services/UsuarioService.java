package services;

import java.util.List;

import auth.UsuarioAuth;
import dao.interfaces.UsuarioDao;
import exceptions.UsuarioException;
import model.Usuario;


public class UsuarioService {
    
    private UsuarioDao usuarioDao;
    private UsuarioAuth usuarioAuth;

    public UsuarioService() {
    }

    public UsuarioService(UsuarioDao usuarioDao, UsuarioAuth usuarioAuth) {
        this.usuarioDao = usuarioDao;
        this.usuarioAuth = usuarioAuth;
    }



    public void adicionarUsuario(Usuario usuario){

        if (!usuarioAuth.validarUsuarioExistente(usuario.getId())) {
            throw new UsuarioException("Usuário já existente");
        }

        usuarioDao.insert(usuario);

    }

    public void removerUsuario(Usuario usuario) {

        if (usuarioAuth.validarUsuarioExistente(usuario.getId())) {
            usuarioDao.deleteByUsuarioId(usuario.getId());
        }
        else {
            throw new UsuarioException("Não há esse usuário");
        }

    }

    public void atualizarUsuario(Usuario usuario) {
        if (usuario.getId() <= 0) {
            throw new UsuarioException("ID de usuário inválido");
        }

        if (!usuarioAuth.verificarEmailExistente(usuario.getEmail())) {
            throw new UsuarioException("Email inválido");
        }

        if (!usuarioAuth.validarNomeUsuario(usuario.getNome())) {
            throw new UsuarioException("Nome usuário inválido.");
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

    public Usuario findByUsuarioEmail(String email) {
        Usuario u = usuarioDao.findByEmail(email);

        if (u == null) {
            throw new UsuarioException("Não encontrado o usuário.");
        }

        return u;
    }

    public List<Usuario> findAll(){

        return usuarioDao.findAll();
    }

    
}
