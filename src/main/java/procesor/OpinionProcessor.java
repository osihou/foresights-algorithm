package procesor;

import cognitive.azure.CognitiveServicesAnalysis;
import db.controllers.DataController;
import db.controllers.SqliteController;
import db.collectors.KeyPhrasesCollector;
import db.collectors.SentimentCollector;
import db.entities.documents.Documents;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OpinionProcessor {

    private final DataController dataController;
    private final SentimentCollector sentimentCollector;
    private final KeyPhrasesCollector keyPhrasesCollector;

    private final CognitiveServicesAnalysis keyPhrasesAnalysis;
    private final CognitiveServicesAnalysis sentimentAnalysis;

    private final String query1 = "SELECT DISTINCT id FROM opinion";
    private final String query2 = "SELECT * FROM opinion WHERE id = ?";

    private final String path = "src/main/resources/database/";

    private final String keyPath = "text/analytics/v3.0/keyPhrases";
    private final String sentimentPath = "text/analytics/v3.0/sentiment" ;

    public OpinionProcessor(String name) {
        dataController = new SqliteController(path + name );

        keyPhrasesCollector = new KeyPhrasesCollector(dataController);
        sentimentCollector = new SentimentCollector(dataController);

        keyPhrasesAnalysis = new CognitiveServicesAnalysis(keyPath);
        sentimentAnalysis = new CognitiveServicesAnalysis(sentimentPath);
    }

    public void processOpinion() throws Exception {
        ResultSet resultSet1 = dataController.getPreparedStatement(query1).executeQuery();

        while (resultSet1.next()) {

            String id = resultSet1.getString("id");
            PreparedStatement preparedStatement = dataController.getPreparedStatement(query2);
            preparedStatement.setString(1, id);
            ResultSet resultSet2 = preparedStatement.executeQuery();



            while (resultSet2.next()){
                Documents documents = new Documents ();
                documents.add(id,"en", resultSet2.getString("text") );

                try {
                    processKeyPhrases(documents);
                    processSentiment(documents);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

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
