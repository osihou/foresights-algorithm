package db.collectors;

import cognitive.phrases.KeyPhrases;
import cognitive.phrases.KeyPhrasesGenerator;
import com.google.gson.JsonObject;
import db.SqliteController;
import utils.JSONCollector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KeyPhrasesCollector {
    private final String table_name = "keywords";
    private final String query1 = "INSERT INTO "+table_name+ "(id_location, text) VALUES (?,?)";
    private final SqliteController sqliteController;

    public KeyPhrasesCollector(SqliteController sqliteController){
        this.sqliteController = sqliteController;
    }

    public void collectKeyPhrases(JsonObject jsonObject){
        JSONCollector jsonCollector1 = new JSONCollector(jsonObject);
        KeyPhrasesGenerator keyPhrases = new KeyPhrasesGenerator(jsonCollector1.getJsonObject());
        KeyPhrases keyPhrases1 = keyPhrases.analysePhrasesJson();


        String id = keyPhrases1.getId();

        keyPhrases1.getPhrases()
                .forEach(x -> {
                    try {
                        digestPhrase(id, x);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void collectKeyPhrases(String path)  {
        JSONCollector jsonCollector1 = new JSONCollector(path);
        KeyPhrasesGenerator keyPhrases = new KeyPhrasesGenerator(jsonCollector1.getJsonObject());
        KeyPhrases keyPhrases1 = keyPhrases.analysePhrasesJson();


        String id = keyPhrases1.getId();

        keyPhrases1.getPhrases()
                .forEach(x -> {
                    try {
                        digestPhrase(id, x);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

    }

    private void digestPhrase( String loc , String text) throws SQLException {
        PreparedStatement preparedStatement = sqliteController.getPreparedStatement(query1);

        preparedStatement.setString(1,loc);
        preparedStatement.setString(2, text);

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }




}
