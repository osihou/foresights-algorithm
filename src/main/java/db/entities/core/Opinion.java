package db.entities.core;

public class Opinion {

    private String id;
    private String text;
    private String rating;

    public Opinion() {
    }

    public Opinion(
            String id,
            String text,
            String rating
    ) {
        this.id = id;
        this.text = text;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
