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
}
