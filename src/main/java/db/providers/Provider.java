package db.providers;
import db.controllers.DataController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Provider {

    private final DataController dataController;

    public Provider (DataController dataController){
        this.dataController = dataController;
    }

    public ResultSet getResultSet(String query) throws SQLException {
        PreparedStatement preparedStatement = dataController.getPreparedStatement(query);
        return preparedStatement.executeQuery();
    }

    public ResultSet getResultSet(String query, List<String> values) throws SQLException {
        PreparedStatement preparedStatement = dataController.getPreparedStatement(query);

        int i = 1;
        for(String value : values){
            preparedStatement.setString(i, value);
            i++;
        }
        return preparedStatement.executeQuery();
    }


}
