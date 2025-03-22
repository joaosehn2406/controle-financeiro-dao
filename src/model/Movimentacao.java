package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Movimentacao that = (Movimentacao) o;
        return id_transacao == that.id_transacao;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id_transacao);
    }

    @Override
    public String toString() {
        return "Transacao{id=" + id_transacao +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                ", tipo=" + tipoMovimentacao +
                '}';
    }
}
