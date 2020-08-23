import cognitive.phrases.KeyPhrases;
import cognitive.phrases.KeyPhrasesGenerator;
import cognitive.sentiment.SentimentDocument;
import cognitive.sentiment.SentimentDocumentGenerator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import db.KeyPhrasesCollector;
import db.SentimentCollector;
import db.SqliteController;
import utils.JSONCollector;

import java.sql.SQLException;

public class ForeSightsAlgorithm {



    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        SentimentCollector sentimentCollector = new SentimentCollector(new SqliteController("src/main/resources/scraping.db"));
        sentimentCollector.collectSentiment("./src/main/resources/test-sentiment.json");




    }
}
