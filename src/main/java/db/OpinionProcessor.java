package db;

import cognitive.CognitiveServicesAnalysis;
import db.collectors.KeyPhrasesCollector;
import db.collectors.SentimentCollector;
import utils.Documents;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OpinionProcessor {

    private final SqliteController sqliteController;
    private final SentimentCollector sentimentCollector;
    private final KeyPhrasesCollector keyPhrasesCollector;

    private final CognitiveServicesAnalysis keyPhrasesAnalysis;
    private final CognitiveServicesAnalysis sentimentAnalysis;

    private final String query1 = "SELECT DISTINCT id FROM opinion LIMIT 5000";
    private final String query2 = "SELECT * FROM opinion WHERE id = ? LIMIT 5";

    private final String path = "src/main/resources/scraping.db";

    private final String keyPath = "text/analytics/v3.0/keyPhrases";
    private final String sentimentPath = "text/analytics/v3.0/sentiment" ;

    public OpinionProcessor() {
        sqliteController = new SqliteController(path);

        keyPhrasesCollector = new KeyPhrasesCollector(sqliteController);
        sentimentCollector = new SentimentCollector(sqliteController);

        keyPhrasesAnalysis = new CognitiveServicesAnalysis(keyPath);
        sentimentAnalysis = new CognitiveServicesAnalysis(sentimentPath);
    }

    public void processOpinion() throws Exception {
        ResultSet resultSet1 = sqliteController.getPreparedStatement(query1).executeQuery();

        for (;resultSet1.next(); ) {



            String id = resultSet1.getString("id");
            PreparedStatement preparedStatement = sqliteController.getPreparedStatement(query2);
            preparedStatement.setString(1, id);
            ResultSet resultSet2 = preparedStatement.executeQuery();

            Documents documents = new Documents ();

            while (resultSet2.next()){
                documents.add(id,"en", resultSet2.getString("text") );
            }

            processKeyPhrases(documents);
            processSentiment(documents);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            System.out.println("Done "+id+ " "+formatter.format(date)  );

        }
    }

    private void processKeyPhrases(Documents documents) throws Exception {
        keyPhrasesCollector.collectKeyPhrases(
                keyPhrasesAnalysis.jsonObjectOut(
                        keyPhrasesAnalysis.getResponse(
                                documents
                        )
                )
        );
    }

    private void processSentiment(Documents documents) throws Exception {
        sentimentCollector.collectSentiment(
                sentimentAnalysis.jsonObjectOut(
                        sentimentAnalysis.getResponse(
                                documents
                        )
                )
        );
    }




}
