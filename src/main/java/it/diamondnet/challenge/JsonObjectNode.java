package it.diamondnet.challenge;

import java.util.Map;
import java.util.HashMap;

public class JsonObjectNode implements JsonNode {
    private final Map<String, JsonNode> children = new HashMap<>();

    public void put(String key, JsonNode value) {
        children.put(key, value);
    }

    public JsonNode get(String key) {
        return children.get(key);
    }

    public Map<String, JsonNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "JsonObjectNode{" +
                "children=" + children +
                '}';
    }
}
