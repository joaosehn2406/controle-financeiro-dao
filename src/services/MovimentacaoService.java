package services;

import auth.MovimentacaoAuth;
import dao.interfaces.MovimentacaoDao;
import exceptions.MovimentacaoException;
import model.Movimentacao;

public class MovimentacaoService {
    
    private MovimentacaoDao movimentacaoDao;
    private MovimentacaoAuth movimentacaoAuth;

    public MovimentacaoService(MovimentacaoDao movimentacaoDao, MovimentacaoAuth movimentacaoAuth) {
        this.movimentacaoDao = movimentacaoDao;
        this.movimentacaoAuth = new MovimentacaoAuth(movimentacaoDao);
    }


    public void adicionarMovimentacao(Movimentacao movimentacao) {
        if (movimentacao.getValor() <= 0) {
            throw new MovimentacaoException("O valor da movimentação deve ser maior que zero.");
        }
        movimentacaoDao.insert(movimentacao);
    }

    public void removerMovimentacao(Integer id) {
        
        if (movimentacaoAuth.verificarExistenciaMovimentacao(id)) {
            movimentacaoDao.deleteByMovimentacaoId(id);
        } else {
            throw new MovimentacaoException("Não há essa movimentação.");
        }
    }

    public void atualizarMovimentacao(Movimentacao movimentacao) {
        if (movimentacao.getId_transacao() <= 0) {
            throw new MovimentacaoException("Movimentação inexistente");
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
}
