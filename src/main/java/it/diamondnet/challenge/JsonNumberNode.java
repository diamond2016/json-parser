package it.diamondnet.challenge;

import java.math.BigDecimal;

public class JsonNumberNode implements JsonNode {
    private final BigDecimal value;

    public JsonNumberNode(String value) {
        this.value = new BigDecimal(value);
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "JsonNumberNode{" + value + "}";
    }
}
