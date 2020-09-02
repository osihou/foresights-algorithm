package db.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqliteController extends DataController{

    private Connection connection;

    public SqliteController(String path)  {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+path);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
    }
    @Override
    public PreparedStatement getPreparedStatement(String statement) {
        try {
            return connection.prepareStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
