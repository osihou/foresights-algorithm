package cognitive.sentiment;

import java.util.ArrayList;
import java.util.List;

public class SentimentDocument extends Sentiment{

    private List<Sentence> sentenceList;

    public SentimentDocument(){
        sentenceList = new ArrayList<>();
    }

    public SentimentDocument(
            String id,
            String sentiment,
            ConfidenceScore confidenceScore,
            List<Sentence> sentenceList
    ) {
        super(id, sentiment, confidenceScore);
        this.sentenceList = sentenceList;
    }


    public void addToList(Sentence sentence){
        sentenceList.add(sentence);
    }

    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }


}
