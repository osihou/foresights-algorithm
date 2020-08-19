package cognitive.sentiment;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import utils.JSONCollector;

public class SentimentDocumentGenerator {

    private final JSONCollector jsonCollector;

    public SentimentDocumentGenerator(JsonObject jsonObject) {
        this.jsonCollector = new JSONCollector(jsonObject);
    }

    public SentimentDocument analyseSentimentJson(){

        JsonArray jsonArray = (JsonArray)jsonCollector.readTopValue("documents");
        SentimentDocument sentimentDocument = new SentimentDocument();

        for (JsonElement jsonElement : jsonArray) {

            JsonObject jsonObject = jsonElement.getAsJsonObject();

            sentimentDocument.setId(jsonCollector.readStringValue("id", jsonObject));
            sentimentDocument.setSentiment(jsonCollector.readStringValue("sentiment", jsonObject));

            JsonObject confidenceScores = (JsonObject)jsonCollector.readTopValue("confidenceScores", jsonObject);

            ConfidenceScore confidenceScore = new ConfidenceScore();

            confidenceScore.setPositive(jsonCollector.readStringValue("positive", confidenceScores));
            confidenceScore.setNegative(jsonCollector.readStringValue("negative", confidenceScores));
            confidenceScore.setNeutral(jsonCollector.readStringValue("neutral", confidenceScores));

            sentimentDocument.setConfidenceScore(confidenceScore);

            JsonArray sentences = (JsonArray)jsonCollector.readTopValue("sentences", jsonObject);

            for(JsonElement itr : sentences){

                Sentence newSentence = new Sentence();

                JsonObject sentence = itr.getAsJsonObject();

                newSentence.setSentiment(jsonCollector.readStringValue("sentiment", sentence));

                JsonObject confidence = (JsonObject)jsonCollector.readTopValue("confidenceScores", sentence);

                ConfidenceScore newConfidenceScore = new ConfidenceScore();

                newConfidenceScore.setPositive(jsonCollector.readStringValue("positive", confidence));
                newConfidenceScore.setNegative(jsonCollector.readStringValue("negative", confidence));
                newConfidenceScore.setNeutral(jsonCollector.readStringValue("neutral", confidence));

                newSentence.setConfidenceScore(newConfidenceScore);

                newSentence.setLength(jsonCollector.readStringValue("length", sentence));
                newSentence.setOffset(jsonCollector.readStringValue("offset", sentence));
                newSentence.setText(jsonCollector.readStringValue("text", sentence));

                sentimentDocument.addToList(newSentence);
            }

        }

        return sentimentDocument;
    }



}
