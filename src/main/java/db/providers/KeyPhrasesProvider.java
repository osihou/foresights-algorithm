package db.providers;

import cognitive.phrases.KeyPhrases;
import db.SqliteController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KeyPhrasesProvider {
    private final SqliteController sqliteController;
    private final String table_name = "keywords";
    private final String query1 = "SELECT DISTINCT id_location FROM "+table_name;
    private final String query2 = "SELECT * FROM "+table_name+" WHERE id_location = ?";

    public KeyPhrasesProvider(SqliteController sqliteController){
        this.sqliteController = sqliteController;
    }

    public List<KeyPhrases> provideKeyPhrases() throws SQLException {
        PreparedStatement preparedStatement = sqliteController.getPreparedStatement(query1);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<KeyPhrases> keyPhrasesList = new ArrayList<>();

        while(resultSet.next()){

            KeyPhrases keyPhrases = new KeyPhrases();
            String id = resultSet.getString("id_location");
            keyPhrases.setId(id);

            PreparedStatement preparedStatement2 = sqliteController.getPreparedStatement(query2);
            preparedStatement2.setString(1, id);
            ResultSet resultSet2 = preparedStatement2.executeQuery();

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

        PreparedStatement preparedStatement2 = sqliteController.getPreparedStatement(query2);
        preparedStatement2.setString(1, id);
        ResultSet resultSet2 = preparedStatement2.executeQuery();

        while(resultSet2.next()){
            keyPhrases.addToList(resultSet2.getString("text"));
        }

        return keyPhrases;
    }



}
