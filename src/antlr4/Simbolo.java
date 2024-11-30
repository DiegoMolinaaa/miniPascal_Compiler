package antlr4;

public class Simbolo {
    String name;
    String type;
    String scope;
    Object value;
    String category;

    public Simbolo(){}
    public Simbolo(String name, String type, String scope, Object value, String category) {
        this.name = name;
        this.type = type;
        this.scope = scope;
        this.value = value;
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Type: %s, Scope: %s, Value: %s, Category: %s",
                name, type, scope, value, category);
    }
}
