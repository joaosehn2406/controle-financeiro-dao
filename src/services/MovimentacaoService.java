package services;

import java.util.List;

import dao.interfaces.MovimentacaoDao;
import exceptions.MovimentacaoException;
import model.Movimentacao;
import model.Usuario;

public class MovimentacaoService {
    
    private MovimentacaoDao movimentacaoDao;


    
    public MovimentacaoService() {
    }

    public MovimentacaoService(MovimentacaoDao movimentacaoDao) {
        this.movimentacaoDao = movimentacaoDao;
    }


    public void adicionarMovimentacao(Movimentacao movimentacao) {

        movimentacaoDao.insert(movimentacao);
    }


    public void removerMovimentacao(Integer id) {

        movimentacaoDao.deleteByMovimentacaoId(id);
    }


    public void atualizarMovimentacao(Movimentacao movimentacao) {
        if (movimentacao.getId_transacao() <= 0) {
            throw new MovimentacaoException("Movimentação inexistente.");
        }

        movimentacaoDao.update(movimentacao);
    }


    public Movimentacao buscarMovimentacaoPorId(int idTransacao) {
        Movimentacao movimentacao = movimentacaoDao.findByMovimentacaoId(idTransacao);
        if (movimentacao == null) {
            throw new MovimentacaoException("Movimentação não encontrada.");
        }
        return movimentacao;
    }

    public List<Movimentacao> buscarMovimentacaoPorUsuario(Usuario u) {
        List<Movimentacao> mov = movimentacaoDao.findByUsuario(u);
        if (mov == null) {
            throw new MovimentacaoException("Não há movimentação com esse usuário!");
        }
        return mov;
    }
}
