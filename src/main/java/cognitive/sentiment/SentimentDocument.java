package cognitive.sentiment;

import java.util.ArrayList;
import java.util.List;

public class SentimentDocument {

    private String id;
    private String sentiment;
    private ConfidenceScore confidenceScore;
    private List<Sentence> sentenceList;

    public SentimentDocument(){
        sentenceList = new ArrayList<>();
    }

    public SentimentDocument(
            String id,
            ConfidenceScore confidenceScore,
            List<Sentence> sentenceList,
            String sentiment
    ) {
        this.id = id;
        this.confidenceScore = confidenceScore;
        this.sentenceList = sentenceList;
        this.sentiment = sentiment;
    }

    public void addToList(Sentence sentence){
        sentenceList.add(sentence);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ConfidenceScore getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(ConfidenceScore confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }
}
