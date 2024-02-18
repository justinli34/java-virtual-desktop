package model;

import java.util.ArrayList;
import java.util.List;

// Home screen of the operating system
public class Home {
    private ArrayList<Application> appList;

    public Home() {
        appList = new ArrayList<Application>();
    }

    public void addApplication(Application a) {
        appList.add(a);
    }

    public void removeApplication(Application a) {
        appList.remove(a);
    }

    public ArrayList<Application> getAppList() {
        return appList;
    }
}
