package db.providers;

import db.controllers.DataController;
import db.entities.core.Opinion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpinionProvider extends Provider {

    private final String table_name = "opinion";

    private final String query1 = "SELECT * FROM "+table_name;
    private final String query2 = "SELECT text FROM "+table_name;

    public OpinionProvider(DataController dataController) {
        super(dataController);
    }

    public List<Opinion> provideOpinion() throws SQLException {

        ResultSet resultSet = getResultSet(query1);
        List<Opinion> opinionArrayList = new ArrayList<>();


        while(resultSet.next()){
            opinionArrayList.add(
                    new Opinion(
                            resultSet.getString("id"),
                            resultSet.getString("text"),
                            resultSet.getString("rating")
                    )
            );
        }
        return opinionArrayList;
    }

    public List<String> provideText() throws SQLException{

        ResultSet resultSet = getResultSet(query2);
        List<String> textArrayList = new ArrayList<>();

        while(resultSet.next()){
            textArrayList.add(resultSet.getString("text"));
        }
        return textArrayList;
    }


}
