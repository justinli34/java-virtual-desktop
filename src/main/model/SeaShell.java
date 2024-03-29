package model;

import org.json.JSONObject;

// a command prompt app with two accepted commands
public class SeaShell extends Application {
    private final String code = "5243817791";

    public SeaShell() {
        super("Sea Shell");
    }

    // EFFECTS: returns 1 for code input
    //          returns 2 for file recovery
    //          returns 0 for nothing
    public int handleInput(String input) {
        if (input.equals("ss " + code)) {
            return 1;
        } else if (input.equals("ss recover")) {
            return 2;
        }
        return 0;
    }

    // EFFECTS: returns this as JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}
