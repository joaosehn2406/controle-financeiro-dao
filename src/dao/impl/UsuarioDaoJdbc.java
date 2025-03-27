package dao.impl;

import dao.interfaces.UsuarioDao;
import db.DB;
import exceptions.DaoException;
import model.Usuario;
import validation.UsuarioRules;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoJdbc  implements UsuarioDao {

    private Connection conn;

    public UsuarioDaoJdbc() {
    }


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

        if (UsuarioRules.validarNomeUsuario(u)) {
            sql.append("nome = ?, ");
            valores.add(u.getNome());
        }

        if (UsuarioRules.validarEmailUsuario(u)) {
            sql.append("email = ?, ");
            valores.add(u.getEmail());
        }

        if (UsuarioRules.validarSenhaUsuario(u)) {
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

            if (row > 0) {
                System.out.println("Update successfully done!");    
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
    public void deleteByUsuarioId(Integer id) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                "DELETE FROM usuario " +
                "WHERE id_usuario  = ?");

            if (id > 0 && id != null) {
                ps.setInt(1, id);
            }

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Delete successfully done!");
            }
            
        }
        catch(SQLException e) {
            throw new DaoException(e.getMessage());
        }
        System.out.println("There as nothing to delete.");
    }

    @Override
    public Usuario findByUsuarioId(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                "SELECT * FROM " +
                "usuario WHERE id_usuario = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id_usuario"));
                usu.setEmail("email");
                usu.setNome("nome");
                usu.setSenhaHash("senha");
                return usu;
            }
            return null;
        }
        catch(SQLException e) {
            throw new DaoException(e.getMessage());
        }

    }

    @Override
    public Usuario findByEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenhaHash(rs.getString("senha"));
                return usuario;
            }
            return null;
        } 
        catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário por email", e);
        }
    }

    @Override
    public List<Usuario> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                "SELECT * FROM usuario order by id_usuario ASC");
            
            rs = ps.executeQuery();

            List<Usuario> list = new ArrayList<>();

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id_usuario"));
                usu.setEmail("email");
                usu.setNome("nome");
                usu.setSenhaHash("senha");
                list.add(usu);
            }
            return null;
        }
        catch(SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
