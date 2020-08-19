package cognitive;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import utils.Documents;
import utils.KeyInitializer;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class CognitiveServicesAnalysis {

    private String path = "/text/analytics/v3.0/sentiment";
    private String subscription_key;
    private String endpoint;

    public CognitiveServicesAnalysis(String path){
        this.path = path;
        KeyInitializer keyInitializer = new KeyInitializer();
        setEndpoint(keyInitializer.getEndpoint());
        setSubscription_key(keyInitializer.getSubscription_key());
    }

    public void setSubscription_key(String subscription_key) {
        this.subscription_key = subscription_key;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getResponse (Documents documents) throws Exception {
        String text = new Gson().toJson(documents);
        byte[] encoded_text = text.getBytes("UTF-8");

        URL url = new URL(endpoint+path);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/json");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscription_key);
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.write(encoded_text, 0, encoded_text.length);
        wr.flush();
        wr.close();

        StringBuilder response = new StringBuilder ();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }

    public JsonObject jsonObjectOut(String json_text){
        JsonParser parser = new JsonParser();
        return parser.parse(json_text).getAsJsonObject();
    }
}
