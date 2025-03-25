package dao.impl;

import dao.UsuarioDao;
import db.DB;
import exceptions.DaoException;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoJdbc  implements UsuarioDao {

    private Connection conn;

    public UsuarioDaoJdbc(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Usuario u) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                    "INSERT INTO usuario " +
                            "(nome, email, senha) " +
                            "VALUES " +
                            "(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getSenha());

            int row = ps.executeUpdate();

            if (row > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    u.setId(id);
                }
            }
        }
        catch(SQLException e) {
            throw new DaoException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Usuario u) {
        PreparedStatement ps = null;
        StringBuilder sql = new StringBuilder("UPDATE usuario SET ");
        List<Object> valores = new ArrayList<>();

        if (u.getNome() != null && !u.getNome().isBlank()) {
            sql.append("nome = ?, ");
            valores.add(u.getNome());
        }

        if (u.getEmail() != null && !u.getEmail().isBlank()) {
            sql.append("email = ?, ");
            valores.add(u.getEmail());
        }

        if (u.getSenha() != null && !u.getSenha().isBlank()) {
            sql.append("senha = ?, ");
            valores.add(u.getSenha());
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id_usuario = ?");
        valores.add(u.getId());

        try {
            ps = conn.prepareStatement(sql.toString());

            for(int i = 0; i < valores.size(); i++) {
                ps.setObject(i + 1, valores.get(i));
            }

            int row = ps.executeUpdate();

            System.out.println("Update successfully done!");
        }
        catch(SQLException e) {
            throw new DaoException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteByUsuarioId(Integer id) {

    }

    @Override
    public Usuario findByUsuarioId(Integer id) {
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        return List.of();
    }
}
