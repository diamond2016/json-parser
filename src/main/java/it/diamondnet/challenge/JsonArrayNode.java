package it.diamondnet.challenge;

import java.util.List;
import java.util.ArrayList;

public class JsonArrayNode implements JsonNode {
    private final List<JsonNode> children = new ArrayList<>();

    public void add(JsonNode node) {
        children.add(node);
    }

    public List<JsonNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "JsonArrayNode{" +
                "children=" + children +
                '}';
    }
}
