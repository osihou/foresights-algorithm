package cognitive.phrases;

import java.util.ArrayList;
import java.util.List;

public class KeyPhrases {
    private String id;
    private List<String> phrases;

    public KeyPhrases() {
        phrases = new ArrayList<>();
    }

    public KeyPhrases(
            String id,
            List<String> phrases
    ) {
        this.id = id;
        this.phrases = phrases;
    }

    public void addToList(String phrase){
        phrases.add(phrase);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<String> phrases) {
        this.phrases = phrases;
    }
}
