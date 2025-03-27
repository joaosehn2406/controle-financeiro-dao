package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Movimentacao implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id_transacao;
    private String descricao;
    private LocalDate data;
    private double valor;

    private TipoMovimentacao tipoMovimentacao;
    private Categoria categoria;
    private Usuario usuario;

    public Movimentacao(){};

    

    public Movimentacao(double valor, Categoria categoria, Usuario usuario) {
        this.valor = valor;
        this.categoria = categoria;
        this.usuario = usuario;
    }



    public Movimentacao(int id_transacao, String descricao, LocalDate data, double valor, TipoMovimentacao tipoMovimentacao, Categoria categoria, Usuario usuario) {
        this.id_transacao = id_transacao;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.tipoMovimentacao = tipoMovimentacao;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public int getId_transacao() {
        return id_transacao;
    }

    public void setId_transacao(int id_transacao) {
        this.id_transacao = id_transacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_transacao;
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        long temp;
        temp = Double.doubleToLongBits(valor);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((tipoMovimentacao == null) ? 0 : tipoMovimentacao.hashCode());
        result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movimentacao other = (Movimentacao) obj;
        if (id_transacao != other.id_transacao)
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
            return false;
        if (tipoMovimentacao != other.tipoMovimentacao)
            return false;
        if (categoria == null) {
            if (other.categoria != null)
                return false;
        } else if (!categoria.equals(other.categoria))
            return false;
        if (usuario == null) {
            if (other.usuario != null)
                return false;
        } else if (!usuario.equals(other.usuario))
            return false;
        return true;
    }



    @Override
    public String toString() {
        return "Movimentacao [id_transacao=" + id_transacao + ", descricao=" + descricao + ", data=" + data + ", valor="
                + valor + ", tipoMovimentacao=" + tipoMovimentacao + ", categoria=" + categoria + ", usuario=" + usuario
                + "]";
    }

    
}
