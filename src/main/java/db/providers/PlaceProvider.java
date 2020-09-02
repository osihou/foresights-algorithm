package db.providers;

import db.controllers.DataController;
import db.entities.core.Place;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceProvider extends Provider {

    private final String table_name = "place";

    private final String query1 = "SELECT * FROM "+table_name;


    public PlaceProvider(DataController dataController) {
        super(dataController);
    }

    public List<Place> providePlaces() throws SQLException {

        ResultSet resultSet = getResultSet(query1);
        List<Place> placeList = new ArrayList<>();

        while (resultSet.next()){
            placeList.add(
                    new Place(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("adress"),
                            resultSet.getString("rating")
                    )
            );
        }

        return placeList;
    }

}
