package dao.interfaces;

import model.Usuario;

import java.util.List;

public interface UsuarioDao {

    void insert(Usuario u);
    void update(Usuario u);
    void deleteByUsuarioId(Integer id);
    Usuario findByUsuarioId(Integer id);
    List<Usuario> findByEmail(String email);
    List<Usuario> findAll();

}
