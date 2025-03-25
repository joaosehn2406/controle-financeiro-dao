package dao.interfaces;

import java.util.List;
import model.Categoria;
import model.Movimentacao;
import model.Usuario;

public interface MovimentacaoDao {
    
    void insert(Movimentacao mov);
    void update(Movimentacao mov);
    void deleteByMovimentacaoId(Integer id);
    Movimentacao findByMovimentacaoId(Integer id);
    List<Movimentacao> findByUsuario(Usuario u); 
    List<Movimentacao> findByCategoria(Categoria cat);
    List<Movimentacao> findAll();
}
