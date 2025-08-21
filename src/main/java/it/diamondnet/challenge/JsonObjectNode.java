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
        return "JsonObjectNode{"
                + "children=" + children
                + "}";
    }

    @Override
    public String toPrettyString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        String indentString = " ".repeat(indent + 2);
        int i = 0;
        for (Map.Entry<String, JsonNode> entry : children.entrySet()) {
            sb.append(indentString);
            sb.append("\"").append(entry.getKey()).append("\": ");
            sb.append(entry.getValue().toPrettyString(indent + 2));
            if (i < children.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
            i++;
        }
        sb.append(" ".repeat(indent)).append("}");
        return sb.toString();
    }
}
