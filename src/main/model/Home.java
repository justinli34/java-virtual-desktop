package model;

import java.util.ArrayList;
import java.util.List;

// Home screen of the operating system. Contains a list of apps.
public class Home {
    private ArrayList<Application> appList;

    // creates a home screen with an empty list of apps
    public Home() {
        appList = new ArrayList<Application>();
    }

    public void addApplication(Application a) {
        appList.add(a);
    }

//    public void removeApplication(Application a) {
//        appList.remove(a);
//    }

    public ArrayList<Application> getAppList() {
        return appList;
    }
}