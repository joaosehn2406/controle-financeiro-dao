package services;

import dao.interfaces.MovimentacaoDao;
import exceptions.MovimentacaoException;
import model.Movimentacao;

public class MovimentacaoService {
    
    private MovimentacaoDao movimentacaoDao;

    public MovimentacaoService() {
    }

    public MovimentacaoService(MovimentacaoDao movimentacaoDao) {
        this.movimentacaoDao = movimentacaoDao;
    }

    public void adicionarMovimentacao(Movimentacao movimentacao) {
        
        if (movimentacao.getValor() != 0 && movimentacao.getValor() < 0) {
            throw new MovimentacaoException("O valor da movimentação deve ser maior que zero.");
        }

        movimentacaoDao.insert(movimentacao);
    }

    public void removerMovimentacao(Movimentacao movimentacao, Integer id) {

        if (verificarExistenciaMovimentacao(movimentacao)) {
            movimentacaoDao.deleteByMovimentacaoId(id);
        }
        else {
            throw new MovimentacaoException("Não há essa movimentação.");
        }
    }

    public boolean verificarExistenciaMovimentacao(Movimentacao movimentacao) {
        return movimentacaoDao.existeMovimentacao(movimentacao);
    }

    
}
