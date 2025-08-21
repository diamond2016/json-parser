package it.diamondnet.challenge;

public class JsonNullNode implements JsonNode {
    private static final JsonNullNode INSTANCE = new JsonNullNode();

    private JsonNullNode() {
    }

    public static JsonNullNode getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "JsonNullNode{}";
    }
}
