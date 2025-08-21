package it.diamondnet.challenge;

import java.util.ArrayList;
import java.util.List;

public class AST {
    Token token;
    List<AST> children;
    public AST(Token token){ this.token = token; }
    public void addChild(AST t) {
        if (children == null) children = new ArrayList<>();
        children.add(t);
    }
}
