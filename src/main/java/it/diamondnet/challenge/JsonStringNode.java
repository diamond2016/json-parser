package it.diamondnet.challenge;

public class JsonStringNode implements JsonNode {
    private final String value;

    public JsonStringNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "JsonStringNode{" + value + "}";
    }
}
