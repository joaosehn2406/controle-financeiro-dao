package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.interfaces.MovimentacaoDao;
import db.DB;
import exceptions.DaoException;
import model.Categoria;
import model.Movimentacao;
import model.TipoMovimentacao;
import model.Usuario;

public class MovimentacaoDaoJdbc implements MovimentacaoDao {

    private Connection conn;

    public MovimentacaoDaoJdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Movimentacao mov) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO movimentacao " +
                    "(descricao, data_mov, valor, tipo_mov, id_categoria, id_usuario) " +
                    "values (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, mov.getDescricao());
            ps.setDate(2, Date.valueOf(mov.getData()));
            ps.setDouble(3, mov.getValor());
            ps.setString(4, mov.getTipoMovimentacao().toString());
            ps.setInt(5, mov.getCategoria().getId());
            ps.setInt(6, mov.getUsuario().getId());

            int row = ps.executeUpdate();

            if (row > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    mov.setId_transacao(id);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void update(Movimentacao mov) {
        PreparedStatement ps = null;
        StringBuilder sb = new StringBuilder("UPDATE movimentacao SET ");
        List<Object> list = new ArrayList<>();

        if (mov.getDescricao() != null && !mov.getDescricao().isBlank()) {
            sb.append("descricao = ?, ");
            list.add(mov.getDescricao());
        }

        if (mov.getData() != null) {
            sb.append("data_mov = ?, ");
            list.add(mov.getData());
        }

        if (mov.getValor() > 0) {
            sb.append("valor = ?, ");
            list.add(mov.getValor());
        }

        if (mov.getTipoMovimentacao() != null) {
            sb.append("tipo_mov = ?, ");
            list.add(mov.getTipoMovimentacao().toString());
        }

        if (mov.getCategoria() != null && mov.getCategoria().getId() > 0) {
            sb.append("id_categoria = ?, ");
            list.add(mov.getCategoria().getId());
        }

        if (mov.getUsuario() != null && mov.getUsuario().getId() > 0) {
            sb.append("id_usuario = ?, ");
            list.add(mov.getUsuario().getId());
        }

        sb.setLength(sb.length() - 2);
        sb.append(" WHERE id_transacao = ?");
        list.add(mov.getId_transacao());

        try {
            ps = conn.prepareStatement(sb.toString());

            for (int i = 0; i < list.size(); i++) {
                ps.setObject(i + 1, list.get(i));
            }

            int rows = ps.executeUpdate();

            if (rows > 0) {
                throw new DaoException("Success! Update done.");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteByMovimentacaoId(Integer id) {
        PreparedStatement ps = null;

        try {

            String sql = "DELETE FROM movimentacao WHERE id_transacao = ?";

            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Success! Movimentacao deleted.");
            } else {
                System.out.println("No Movimentacao found with the provided id.");
            }
        } catch (SQLException e) {
            throw new DaoException("Error while deleting Movimentacao: " + e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public Movimentacao findByMovimentacaoId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = conn.prepareStatement(
                    "SELECT movimentacao.*, categoria.nome as CategoriaNome, usuario.nome as UsuarioNome, usuario.email, usuario.senha "
                            +
                            "FROM movimentacao " +
                            "INNER JOIN categoria ON movimentacao.id_categoria = categoria.id_categoria " +
                            "INNER JOIN usuario ON categoria.id_usuario = usuario.id_usuario " +
                            "WHERE movimentacao.id_transacao = ?");

            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {

                Usuario usuario = instantiateUsuario(rs);

                Categoria categoria = instantiateCategoria(rs, usuario);

                Movimentacao mov = instantiateMovimentacao(rs, categoria);
                return mov;
            }

            return null;
        } 
        catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } 
        finally {

            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Movimentacao> findByCategoria(Categoria cat) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                "SELECT movimentacao.*, categoria.*, usuario.nome as UsuarioNome, usuario.email, usuario.senha " +
                "FROM movimentacao " + 
                "INNER JOIN categoria ON movimentacao.id_categoria = categoria.id_categoria " +
                "INNER JOIN usuario ON categoria.id_usuario = usuario.id_usuario " +
                "WHERE movimentacao.id_categoria = ? ");

            ps.setInt(1, cat.getId());

            rs = ps.executeQuery();

            List<Movimentacao> list = new ArrayList<>();
            Map<Integer, Categoria> map = new HashMap<>();

            while (rs.next()) {
                
                Categoria c = map.get(rs.getInt("id_categoria"));

                if (c == null) {
                    c = instantiateCategoria(rs, instantiateUsuario(rs));
                    map.put(rs.getInt("id_categoria"), c);   
                }

                Movimentacao mov = instantiateMovimentacao(rs, cat);
                list.add(mov);
            }
            return list;

        }
        catch(SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Movimentacao> findByUsuario(Usuario u) {
        PreparedStatement ps = null;
        ResultSet rs = null; 

        try {
            ps = conn.prepareStatement(
                "SELECT movimentacao.*, categoria.*, usuario.nome as UsuarioNome, usuario.email, usuario.senha " +
                "FROM movimentacao " + 
                "INNER JOIN categoria ON movimentacao.id_categoria = categoria.id_categoria " +
                "INNER JOIN usuario ON categoria.id_usuario = usuario.id_usuario " +
                "WHERE usuario.id_usuario = ?");

            ps.setInt(1, u.getId());

            rs = ps.executeQuery();

            List<Movimentacao> list = new ArrayList<>();
            Map<Integer, Usuario> map = new HashMap<>();

            while (rs.next()) {
                
                Usuario usuario = map.get(rs.getInt("id_usuario"));

                if (usuario == null) {
                    usuario = instantiateUsuario(rs);
                    map.put(rs.getInt("id_usuario"), u);
                }

                Movimentacao mov = instantiateMovimentacao(rs, instantiateCategoria(rs, usuario));
                list.add(mov);
            }
            return list;

        }
        catch(SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Movimentacao> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null; 

        try {

            ps = conn.prepareStatement(
                "SELECT movimentacao.*, categoria.*, usuario.nome as UsuarioNome, usuario.email, usuario.senha " +
                "FROM movimentacao " + 
                "INNER JOIN categoria ON movimentacao.id_categoria = categoria.id_categoria " +
                "INNER JOIN usuario ON categoria.id_usuario = usuario.id_usuario " +
                "ORDER BY movimentacao.descricao");

            rs = ps.executeQuery();

            List<Movimentacao> list = new ArrayList<>();
            Map<Integer, Usuario> map1 = new HashMap<>();
            Map<Integer, Categoria> map2 = new HashMap<>();

            while (rs.next()) {
                
                Usuario usuario = map1.get(rs.getInt("id_usuario"));

                if (usuario == null) {
                    usuario = instantiateUsuario(rs);
                    map1.put(rs.getInt("id_usuario"), usuario);
                }

                Categoria cat = map2.get(rs.getInt("id_categoria"));

                if (cat == null) {
                    cat = instantiateCategoria(rs, usuario);
                    map2.put(rs.getInt("id_categoria"), cat);
                }

                Movimentacao mov = instantiateMovimentacao(rs, cat);
                mov.setUsuario(usuario);
                mov.setCategoria(cat);

                list.add(mov);
            }
            return list;

        }
        catch(SQLException e) {
            throw new DaoException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    private Usuario instantiateUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id_usuario"));
        usuario.setNome(rs.getString("UsuarioNome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenhaHash(rs.getString("senha"));
        return usuario;
    }

    private Categoria instantiateCategoria(ResultSet rs, Usuario usuario) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("id_categoria"));
        categoria.setNome(rs.getString("nome"));
        categoria.setUsuario(usuario);
        return categoria;
    }

    private Movimentacao instantiateMovimentacao(ResultSet rs, Categoria cat) throws SQLException {
        Movimentacao mov = new Movimentacao();
        mov.setId_transacao(rs.getInt("id_transacao"));
        mov.setDescricao(rs.getString("descricao"));
        mov.setData(rs.getDate("data_mov").toLocalDate());
        mov.setValor(rs.getDouble("valor"));
        mov.setTipoMovimentacao(TipoMovimentacao.valueOf(rs.getString("tipo_mov")));
        mov.setCategoria(cat);
        return mov;
    }

}
