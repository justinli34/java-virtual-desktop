package model;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

// An internet browser app called "Fiber Search". contains a list of webpages.
public class FiberSearch extends Application {
    private ArrayList<Webpage> webpages;

    // MODIFIES: this
    // EFFECTS: creates a FiberSearch object with 2 webpages: Tony's Pizza and John's Music Blog
    public FiberSearch() {
        super("Fiber Search");
        webpages = new ArrayList<>();

        String sep = System.getProperty("file.separator");
        File tonysPizza = new File(System.getProperty("user.dir") + sep + "data" + sep + "TonysPizzaText");
        File johnsMusicBlog = new File(System.getProperty("user.dir") + sep + "data" + sep + "JohnsMusicBlogText");

        this.addWebpage(new Webpage("Tony's Pizza", tonysPizza));
        this.addWebpage(new Webpage("John's Music Blog", johnsMusicBlog));
    }

    // MODIFIES: this
    // EFFECTS: adds given webpage to webpages
    public void addWebpage(Webpage p) {
        webpages.add(p);
    }

    // EFFECTS: returns webpages with numVisits <= 12. removes webpages with more than 12 visits.
    public ArrayList<Webpage> getWebpages() {
        for (Webpage w : webpages) {
            if (w.getNumVisits() > 12) {
                webpages.remove(w);
            }
        }
        return webpages;
    }

    // EFFECTS: returns this as JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("webpages", webpages);
        return json;
    }
}
