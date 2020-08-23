package db;

import cognitive.sentiment.ConfidenceScore;
import cognitive.sentiment.Sentence;
import cognitive.sentiment.SentimentDocument;
import cognitive.sentiment.SentimentDocumentGenerator;
import com.google.gson.JsonObject;
import utils.JSONCollector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SentimentCollector {
    private final String table_name_1 = "sentiment";
    private final String table_name_2 = "sentence";
    private final String query1 = "INSERT INTO " +table_name_1+ "(id_location, sent, pos, neg, neu) VALUES (?,?,?,?,?)";
    private final String query2 = "INSERT INTO " +table_name_2+ "(id_location, sent, pos, neg, neu, ost, len, text) VALUES (?,?,?,?,?,?,?,?)";
    private final SqliteController sqliteController;

    public SentimentCollector(SqliteController sqliteController){
        this.sqliteController = sqliteController;
    }

    public void collectSentiment(JsonObject jsonObject) throws SQLException {
        JSONCollector jsonCollector = new JSONCollector(jsonObject);
        SentimentDocumentGenerator sentimentDocumentGenerator = new SentimentDocumentGenerator(jsonCollector.getJsonObject());
        SentimentDocument sentimentDocument = sentimentDocumentGenerator.analyseSentimentJson();

        String id = sentimentDocument.getId();

        digestSentiment(sentimentDocument);

        sentimentDocument.getSentenceList()
                .forEach(x -> {
                    try {
                        digestSentence(id, x);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void collectSentiment(String path) throws SQLException {
        JSONCollector jsonCollector = new JSONCollector(path);
        SentimentDocumentGenerator sentimentDocumentGenerator = new SentimentDocumentGenerator(jsonCollector.getJsonObject());
        SentimentDocument sentimentDocument = sentimentDocumentGenerator.analyseSentimentJson();

        String id = sentimentDocument.getId();

        digestSentiment(sentimentDocument);

        sentimentDocument.getSentenceList()
                .forEach(x -> {
                    try {
                        digestSentence(id, x);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

    }

    private void digestSentiment(SentimentDocument sentimentDocument) throws SQLException {
        PreparedStatement preparedStatement = sqliteController.getPreparedStatement(query1);


        preparedStatement.setString(1, sentimentDocument.getId());
        preparedStatement.setString(2, sentimentDocument.getSentiment());

        preparedStatement.setString(3, sentimentDocument.getConfidenceScore().getPositive());
        preparedStatement.setString(4, sentimentDocument.getConfidenceScore().getNegative());
        preparedStatement.setString(5, sentimentDocument.getConfidenceScore().getNeutral());

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    private void digestSentence(String id, Sentence x) throws SQLException {
        PreparedStatement preparedStatement = sqliteController.getPreparedStatement(query2);

        preparedStatement.setString(1, id);
        preparedStatement.setString(2, x.getSentiment());
        preparedStatement.setString(3, x.getConfidenceScore().getPositive());
        preparedStatement.setString(4, x.getConfidenceScore().getNegative());
        preparedStatement.setString(5, x.getConfidenceScore().getNeutral());

        preparedStatement.setString(6, x.getOffset());
        preparedStatement.setString(7, x.getLength());
        preparedStatement.setString(8, x.getText());

        preparedStatement.executeUpdate();

        preparedStatement.close();

    }

}
