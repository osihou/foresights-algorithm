package db.providers;

import db.entities.cognitive.phrases.KeyPhrases;
import db.controllers.DataController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyPhrasesProvider extends Provider{

    private final String table_name = "keywords";

    private final String query1 = "SELECT DISTINCT id_location FROM "+table_name;
    private final String query2 = "SELECT * FROM "+table_name+" WHERE id_location = ?";

    public KeyPhrasesProvider(DataController dataController){
        super(dataController);
    }

    public List<KeyPhrases> provideKeyPhrases() throws SQLException {

        ResultSet resultSet = getResultSet(query1);
        List<KeyPhrases> keyPhrasesList = new ArrayList<>();

        while(resultSet.next()){

            KeyPhrases keyPhrases = new KeyPhrases();
            String id = resultSet.getString("id_location");
            keyPhrases.setId(id);

            ResultSet resultSet2 = getResultSet(query2, new ArrayList<String>(Collections.singleton(id)));

            while(resultSet2.next()){
                keyPhrases.addToList(resultSet2.getString("text"));
            }
            keyPhrasesList.add(keyPhrases);
        }

        return keyPhrasesList;
    }

    public KeyPhrases provideKeyPhrases(String id) throws SQLException {

        KeyPhrases keyPhrases = new KeyPhrases();
        keyPhrases.setId(id);

        ResultSet resultSet2 = getResultSet(query2, new ArrayList<String>(Collections.singleton(id)));

        while(resultSet2.next()){
            keyPhrases.addToList(resultSet2.getString("text"));
        }

        return keyPhrases;
    }



}
