package model;

import org.json.JSONObject;

// abstract class for application within operating system
public abstract class Application {
    protected String name;

    public Application(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract JSONObject toJson();

}