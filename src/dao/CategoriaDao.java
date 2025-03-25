package dao;

import java.util.List;

import model.Categoria;
import model.Usuario;

public interface CategoriaDao {

    void insert(Categoria cat);
    void update(Categoria cat);
    void deleteById(Integer id);
    Categoria findByCategoriaId(Integer id);
    List<Categoria> findAll();
    List<Categoria> findByUsuario(Usuario u); 
} 
