package antlr4;

import java.util.ArrayList;
import java.util.List;

public class ASTNode {
    private String name;
    private List<ASTNode> children;

    public ASTNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(ASTNode child) {
        children.add(child);
    }

    public String getName() {
        return name;
    }

    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return name + children.toString();
    }
}
