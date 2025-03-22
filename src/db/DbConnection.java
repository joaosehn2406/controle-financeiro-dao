package db;

import exceptions.DbConnectionException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbConnection {

    private static Connection conn = null;

    private static Properties loadProperties(){

        try(FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch(IOException e) {
            throw new DbConnectionException(e.getMessage());
        }
    }

    public static Connection getConnection(){

        try {
            if (conn == null) {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
        }
        catch(SQLException e) {
            throw new DbConnectionException(e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbConnectionException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbConnectionException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbConnectionException(e.getMessage());
            }
        }
    }
}
