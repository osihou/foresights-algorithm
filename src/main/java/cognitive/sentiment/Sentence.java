package cognitive.sentiment;

public class Sentence {

    private String sentiment;

    private String offset;
    private String length;
    private String text;

    private ConfidenceScore confidenceScore;

    public Sentence() {
    }

    public Sentence(
            String sentiment,
            String offset,
            String length,
            String text,
            ConfidenceScore confidenceScore
    ) {
        this.sentiment = sentiment;
        this.offset = offset;
        this.length = length;
        this.text = text;
        this.confidenceScore = confidenceScore;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ConfidenceScore getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(ConfidenceScore confidenceScore) {
        this.confidenceScore = confidenceScore;
    }
}
