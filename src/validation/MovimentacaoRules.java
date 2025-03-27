package validation;

import model.Movimentacao;

public class MovimentacaoRules {
    
    private Movimentacao mov;

    public boolean descNotNull(Movimentacao mov) {
        return (mov.getDescricao() != null && !mov.getDescricao().isBlank());
    }
    
    public boolean dataNotNull(Movimentacao mov) {
        return mov.getData() != null;
    }

    public boolean valorInvalido (Movimentacao mov) {
        return mov.getValor() > 0;
    }   

    public boolean tipoMovimentacaoInvalida (Movimentacao mov ) {
        return mov.getTipoMovimentacao() != null;
    }

    public boolean validarIdUsuarioCat(Movimentacao mov) {
        return mov.getUsuario() != null && mov.getUsuario().getId() > 0;
    }
}
