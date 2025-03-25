package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.interfaces.CategoriaDao;
import db.DB;
import exceptions.DaoException;
import model.Categoria;
import model.Usuario;

public class CategoriaDaoJdbc implements CategoriaDao {

    private Connection conn;

    public CategoriaDaoJdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Categoria cat) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "INSERT INTO categoria "
                            + "(nome, id_usuario) "
                            + "values "
                            + "(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, cat.getNome());
            ps.setInt(2, cat.getUsuario().getId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    cat.setId(id);
                }
            } else {
                throw new DaoException("Unexpected error! No rows affected");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Categoria cat) {
        PreparedStatement ps = null;
        StringBuilder sb = new StringBuilder("UPDATE categoria SET ");
        List<Object> list = new ArrayList<>();

        if (cat.getNome() != null && !cat.getNome().isBlank()) {
            sb.append("nome = ?, ");
            list.add(cat.getNome());
        }

        if (cat.getUsuario().getId() > 0) {
            sb.append("id_usuario = ?, ");
            list.add(cat.getUsuario().getId());
        }

        sb.setLength(sb.length() - 2);
        sb.append(" WHERE id_categoria = ? ");
        list.add(cat.getId());

        try {

            ps = conn.prepareStatement(sb.toString());

            for (int i = 0; i < list.size(); i++) {
                ps.setObject(i + 1, list.get(0));
            }

            int rows = ps.executeUpdate();

            if (rows > 0) {
                throw new DaoException("Sucess! Update done.");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                    "DELETE FROM categoria " +
                            "WHERE id_categoria  = ?");

            if (id > 0 && id != null) {
                ps.setInt(1, id);
            }

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Delete successfully done!");
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        System.out.println("There as nothing to delete.");
    }

    @Override
    public Categoria findByCategoriaId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT categoria.*, usuario.nome as UsuarioNome, usuario.email, usuario.senha " +
                            "FROM categoria INNER JOIN usuario " +
                            "ON categoria.id_usuario = usuario.id_usuario " +
                            "WHERE categoria.id_categoria = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Usuario usuario = instantiateUsuario(rs);
                Categoria categoria = instantiateCategoria(rs, usuario);
                return categoria;
            }

            return null;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Categoria> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn
                    .prepareStatement("SELECT categoria.*, usuario.nome as UsuarioNome, usuario.email, usuario.senha " +
                            "FROM categoria INNER JOIN usuario " +
                            "ON categoria.id_usuario = usuario.id_usuario " +
                            "ORDER BY categoria.nome");

            rs = ps.executeQuery();

            List<Categoria> list = new ArrayList<>();
            Map<Integer, Usuario> map = new HashMap<>();

            while (rs.next()) {

                Usuario usuario = map.get(rs.getInt("id_usuario"));

                if (usuario == null) {

                    usuario = instantiateUsuario(rs);
                    map.put(rs.getInt("id_usuario"), usuario);
                }

                Categoria categoria = instantiateCategoria(rs, usuario);
                list.add(categoria);
            }

            return list;
        } 
        catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } 
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Categoria> findByUsuario(Usuario u) {
        PreparedStatement ps = null;
        ResultSet rs = null; 

        try {
            ps = conn
                    .prepareStatement("SELECT categoria.*, usuario.nome as UsuarioNome, usuario.email, usuario.senha " +
                    "FROM categoria INNER JOIN usuario " +
                    "ON categoria.id_usuario = usuario.id_usuario " +
                    "WHERE usuario.id_usuario = ? " +
                    "ORDER BY categoria.nome");

            ps.setInt(1, u.getId());

            rs = ps.executeQuery();

            List<Categoria> list = new ArrayList<>();
            Map<Integer, Usuario> map = new HashMap<>();
            
            while (rs.next()) {
                
                Usuario usuario = map.get(rs.getInt("id_usuario"));

                if (usuario == null) {
                    usuario = instantiateUsuario(rs);
                    map.put(rs.getInt("id_usuario"), u);
                }

                Categoria cat = instantiateCategoria(rs, usuario);
                list.add(cat);

            }
            return list;
        }
        catch(SQLException e) {
            throw new DaoException(e.getMessage());
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
}
