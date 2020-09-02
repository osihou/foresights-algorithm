package cognitive.azure.generators;

import db.entities.cognitive.phrases.KeyPhrases;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import utils.JSONCollector;

public class KeyPhrasesGenerator {

    private final JSONCollector jsonCollector;

    public KeyPhrasesGenerator(JsonObject jsonObject) {
        this.jsonCollector = new JSONCollector(jsonObject);
    }

    public KeyPhrases analysePhrasesJson() {
        JsonArray jsonArray = (JsonArray)jsonCollector.readTopValue("documents");
        KeyPhrases keyPhrases = new KeyPhrases();

        for (JsonElement jsonElement : jsonArray) {

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            keyPhrases.setId(jsonCollector.readStringValue("id", jsonObject));

            JsonArray sentences = (JsonArray)jsonCollector.readTopValue("keyPhrases", jsonObject);

            for(JsonElement itr : sentences) {

                keyPhrases.addToList(itr.toString());
            }

        }


    return keyPhrases;
    }


}
