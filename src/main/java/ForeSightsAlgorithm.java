import cognitive.phrases.KeyPhrases;
import cognitive.phrases.KeyPhrasesGenerator;
import cognitive.sentiment.SentimentDocument;
import cognitive.sentiment.SentimentDocumentGenerator;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import utils.JSONCollector;

public class ForeSightsAlgorithm {



    public static void main(String[] args) {
        JSONCollector jsonCollector = new JSONCollector("./src/main/resources/test-sentiment.json");
        SentimentDocumentGenerator sentimentDocumentGenerator = new SentimentDocumentGenerator(jsonCollector.getJsonObject());

        SentimentDocument sentimentDocument = sentimentDocumentGenerator.analyseSentimentJson();


        sentimentDocument.getSentenceList()
                .forEach(x -> System.out.println(x.getText()));


        JSONCollector jsonCollector1 = new JSONCollector("./src/main/resources/test-key-phrases.json");
        KeyPhrasesGenerator keyPhrases = new KeyPhrasesGenerator(jsonCollector1.getJsonObject());

        KeyPhrases keyPhrases1 = keyPhrases.analysePhrasesJson();

        keyPhrases1.getPhrases()
                .forEach(System.out::println);




    }
}
