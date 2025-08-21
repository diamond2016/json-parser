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
        return "JsonArrayNode{"
                + "children=" + children
                + "}";
    }

    @Override
    public String toPrettyString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        String indentString = " ".repeat(indent + 2);
        for (int i = 0; i < children.size(); i++) {
            sb.append(indentString);
            sb.append(children.get(i).toPrettyString(indent + 2));
            if (i < children.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append(" ".repeat(indent)).append("]");
        return sb.toString();
    }
}
