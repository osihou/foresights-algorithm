package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thoughtworks.qdox.model.expression.ParenExpression;

import java.io.FileReader;
import java.io.IOException;

public class JSONCollector {

    private JsonObject jsonObject;

    public JSONCollector(String fileName) {
        JsonParser jsonParser = new JsonParser();
        try {
            this.jsonObject = (JsonObject) jsonParser.parse(new FileReader(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONCollector(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Object readValue(String getText){
        return this.jsonObject.get(getText);
    }

    public Object readValue(String getText , JsonObject jsonObject){
        return jsonObject.get(getText);
    }

    public String readStringValue (String getText){
        return this.jsonObject.get(getText).toString().replace("\"", "");
    }

    public String readStringValue (String getText, JsonObject jsonObject){
        return jsonObject.get(getText).toString().replace("\"", "");
    }

    public Object readTopValue(String getText){
        return this.jsonObject.get(getText);
    }

    public Object readTopValue(String getText, JsonObject jsonObject){
        return jsonObject.get(getText);
    }
}

