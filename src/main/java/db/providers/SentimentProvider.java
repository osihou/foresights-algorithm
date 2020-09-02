package db.providers;

import cognitive.phrases.KeyPhrases;
import cognitive.sentiment.ConfidenceScore;
import cognitive.sentiment.Sentence;
import cognitive.sentiment.Sentiment;
import cognitive.sentiment.SentimentDocument;
import db.SqliteController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SentimentProvider {

    private final SqliteController sqliteController;

    private final String table_name_1 = "sentiment";
    private final String table_name_2 = "sentence";

    private final String query1 = "SELECT DISTINCT id_location FROM "+table_name_1;

    private final String query2 = "SELECT * FROM "+table_name_1 + " WHERE id_location = ?";
    private final String query3 = "SELECT * FROM "+table_name_2 + " WHERE id_location = ?";

    private final String query4 = "SELECT * FROM "+table_name_1 + "";
    private final String query5 = "SELECT * FROM "+table_name_2 + "";


    public SentimentProvider(SqliteController sqliteController){
        this.sqliteController = sqliteController;
    }
    public List<Sentiment> provideSentimentList() throws SQLException {
        PreparedStatement preparedStatement = sqliteController.getPreparedStatement(query4);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Sentiment> sentimentList = new ArrayList<>();

        while(resultSet.next()){
            Sentiment sentiment = new Sentiment();

            sentiment.setId(resultSet.getString("id_location"));
            sentiment.setSentiment(resultSet.getString("sent"));

            ConfidenceScore confidenceScore = new ConfidenceScore();

            confidenceScore.setNeutral(resultSet.getString("neu"));
            confidenceScore.setNegative(resultSet.getString("neg"));
            confidenceScore.setPositive(resultSet.getString("pos"));

            sentiment.setConfidenceScore(confidenceScore);

            sentimentList.add(sentiment);
        }

        return sentimentList;
    }

    public List<Sentence> provideSentenceList() throws SQLException {
        PreparedStatement preparedStatement = sqliteController.getPreparedStatement(query5);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Sentence> sentenceList = new ArrayList<>();

        while(resultSet.next()){
            Sentence sentence = new Sentence();

            sentence.setId(resultSet.getString("id_location"));
            sentence.setSentiment(resultSet.getString("sent"));
            sentence.setOffset(resultSet.getString("ost"));
            sentence.setLength(resultSet.getString("len"));
            sentence.setText(resultSet.getString("text"));

            ConfidenceScore confidenceScore = new ConfidenceScore();

            confidenceScore.setNeutral(resultSet.getString("neu"));
            confidenceScore.setNegative(resultSet.getString("neg"));
            confidenceScore.setPositive(resultSet.getString("pos"));

            sentence.setConfidenceScore(confidenceScore);

            sentenceList.add(sentence);
        }

        return sentenceList;
    }




}
