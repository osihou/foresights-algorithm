package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqliteController {

    private final Connection connection;

    public SqliteController(String path) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:"+path);
    }

    public PreparedStatement getPreparedStatement(String statement) throws SQLException {
        return connection.prepareStatement(statement);
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}
