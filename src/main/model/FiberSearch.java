package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FiberSearch extends Application {
    private ArrayList<Webpage> webpages;

    public FiberSearch() {
        super("Fiber Search");
        webpages = new ArrayList<>();
    }

    public void addWebpage(Webpage p) {
        webpages.add(p);
    }

    public ArrayList<Webpage> getWebpages() {
        return webpages;
    }
}
