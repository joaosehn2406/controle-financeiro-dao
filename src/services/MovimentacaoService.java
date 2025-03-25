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
        this.movimentacaoAuth = movimentacaoAuth; 
    }


    public void adicionarMovimentacao(Movimentacao movimentacao) {

        movimentacaoAuth.validarValorMovimentacao(movimentacao); 
        if (!movimentacaoAuth.validarCategoria(movimentacao)) {
            throw new MovimentacaoException("Categoria inválida.");
        }
        if (!movimentacaoAuth.validarUsuario(movimentacao)) {
            throw new MovimentacaoException("Usuário inválido.");
        }
        movimentacaoAuth.validarDataMovimentacao(movimentacao); 
        if (movimentacaoAuth.verificarDuplicidade(movimentacao)) {
            throw new MovimentacaoException("Movimentação já existe.");
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
            throw new MovimentacaoException("Movimentação inexistente.");
        }

        movimentacaoAuth.validarValorMovimentacao(movimentacao); 
        if (!movimentacaoAuth.validarCategoria(movimentacao)) {
            throw new MovimentacaoException("Categoria inválida.");
        }
        if (!movimentacaoAuth.validarUsuario(movimentacao)) {
            throw new MovimentacaoException("Usuário inválido.");
        }
        movimentacaoAuth.validarDataMovimentacao(movimentacao);
        if (movimentacaoAuth.verificarDuplicidade(movimentacao)) {
            throw new MovimentacaoException("Movimentação já existe.");
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
