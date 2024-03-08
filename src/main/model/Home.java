package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Home screen of the operating system. Contains a list of apps.
public class Home implements Writable {
    private ArrayList<Application> appList;

    // EFFECTS: creates a home screen with an empty list of apps
    public Home() {
        appList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given application to appList
    public void addApplication(Application a) {
        appList.add(a);
    }

//    public void removeApplication(Application a) {
//        appList.remove(a);
//    }

    public ArrayList<Application> getAppList() {
        return appList;
    }

    // EFFECTS: returns this as JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Application a : appList) {
            jsonArray.put(a.toJson());
        }
        json.put("apps", jsonArray);
        return json;
    }
}