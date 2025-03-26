package auth;

import java.time.LocalDate;

import dao.interfaces.CategoriaDao;
import dao.interfaces.MovimentacaoDao;
import dao.interfaces.UsuarioDao;
import model.Categoria;
import model.Movimentacao;
import model.Usuario;
import exceptions.MovimentacaoException;

public class MovimentacaoAuth {

    private MovimentacaoDao movimentacaoDao;
    private CategoriaDao categoriaDao;
    private UsuarioDao usuarioDao;


    public MovimentacaoAuth() {
    }

    public MovimentacaoAuth(MovimentacaoDao movimentacaoDao) {
        this.movimentacaoDao = movimentacaoDao;
    }

    public MovimentacaoAuth(MovimentacaoDao movimentacaoDao, CategoriaDao categoriaDao, UsuarioDao usuarioDao) {
        this.movimentacaoDao = movimentacaoDao;
        this.categoriaDao = categoriaDao;
        this.usuarioDao = usuarioDao;
        
    }

    public boolean verificarExistenciaMovimentacao(Integer id) {

        Movimentacao movimentacao = movimentacaoDao.findByMovimentacaoId(id);
        return movimentacao != null;
    }

    public boolean verificarExistenciaMovimentacaoPorDescricaoData(Movimentacao movimentacao) {
        return movimentacaoDao.existeMovimentacao(movimentacao);
    }

    public boolean validarValorMovimentacao(Movimentacao movimentacao) {
        if (movimentacao.getValor() <= 0) {
            System.out.println("O valor da movimentação deve ser maior que zero.");
            return false;
        }
        return true;
    }


    public boolean validarCategoria(Movimentacao movimentacao) {
        Categoria categoria = movimentacao.getCategoria();
        return categoria != null && categoria.getId() > 0 && categoriaDao.findByCategoriaId(categoria.getId()) != null;
    }


    public boolean validarUsuario(Movimentacao movimentacao) {
        Usuario usuario = movimentacao.getUsuario();
        return usuario != null && usuario.getId() > 0 && usuarioDao.findByUsuarioId(usuario.getId()) != null;
    }


    public void validarDataMovimentacao(Movimentacao movimentacao) {
        if (movimentacao.getData().isAfter(LocalDate.now())) {
            throw new MovimentacaoException("A data da movimentação não pode ser no futuro.");
        }
    }

    public boolean verificarDuplicidade(Movimentacao movimentacao) {
        return movimentacaoDao.existeMovimentacao(movimentacao);
    }


    public boolean validarPeriodoMovimentacao(Movimentacao movimentacao, LocalDate inicioPeriodo, LocalDate fimPeriodo) {
        LocalDate dataMovimentacao = movimentacao.getData();
        return !dataMovimentacao.isBefore(inicioPeriodo) && !dataMovimentacao.isAfter(fimPeriodo);
    }
}
