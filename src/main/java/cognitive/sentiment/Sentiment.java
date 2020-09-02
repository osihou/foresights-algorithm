package cognitive.sentiment;

public class Sentiment {
    private String id;
    private String sentiment;
    private ConfidenceScore confidenceScore;

    public Sentiment() {
    }

    public Sentiment(
            String id,
            String sentiment,
            ConfidenceScore confidenceScore
    ) {
        this.id = id;
        this.sentiment = sentiment;
        this.confidenceScore = confidenceScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public ConfidenceScore getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(ConfidenceScore confidenceScore) {
        this.confidenceScore = confidenceScore;
    }
}
