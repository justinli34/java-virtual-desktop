package model;

public class Webpage {
    private String name;
    private String body;

    public Webpage(String name, String body) {
        this.name = name;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }
}
