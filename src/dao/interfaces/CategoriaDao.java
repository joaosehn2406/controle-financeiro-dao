package dao.interfaces;

import java.util.List;

import model.Categoria;
import model.Usuario;

public interface CategoriaDao {

    void insert(Categoria cat);
    void update(Categoria cat);
    void deleteById(Integer id);
    List<Categoria> findAll();
    Categoria findByCategoriaId(Integer id);
    List<Categoria> findByUsuario(Usuario u); 
} 
