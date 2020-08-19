package cognitive.sentiment;

public class ConfidenceScore {
    private String positive;
    private String negative;
    private String neutral;

    public ConfidenceScore() {
    }

    public ConfidenceScore(
            String positive,
            String negative,
            String neutral
    ) {
        this.positive = positive;
        this.negative = negative;
        this.neutral = neutral;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getNegative() {
        return negative;
    }

    public void setNegative(String negative) {
        this.negative = negative;
    }

    public String getNeutral() {
        return neutral;
    }

    public void setNeutral(String neutral) {
        this.neutral = neutral;
    }
}
