package it.diamondnet.challenge;

public class JsonBooleanNode implements JsonNode {
    private final boolean value;

    public JsonBooleanNode(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "JsonBooleanNode{" + value + "}";
    }

    @Override
    public String toPrettyString(int indent) {
        return String.valueOf(value);
    }
}
