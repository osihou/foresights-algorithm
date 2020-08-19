package utils;

public class KeyInitializer {

    private String subscription_key;
    private String endpoint;

    public KeyInitializer(){
        JSONCollector jsonCollector = new JSONCollector("./src/main/resources/keys.json");
        setEndpoint(jsonCollector.readStringValue("endpoint"));
        setSubscription_key(jsonCollector.readStringValue("subscription_key"));
    }

    public String getSubscription_key() {
        return subscription_key;
    }

    public void setSubscription_key(String subscription_key) {
        this.subscription_key = subscription_key;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
