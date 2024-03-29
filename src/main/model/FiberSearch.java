package model;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

// An internet browser app called "Fiber Search". contains a list of webpages.
public class FiberSearch extends Application {
    private ArrayList<Webpage> webpages;
    private final String url = "www.machinegallery.com";

    // MODIFIES: this
    // EFFECTS: creates a FiberSearch object with 3 webpages: Tony's Pizza and John's Music Blog and Best Tech Forum
    public FiberSearch() {
        super("Fiber Search");
        webpages = new ArrayList<>();

        String sep = System.getProperty("file.separator");
        File tonysPizza = new File(System.getProperty("user.dir") + sep + "data" + sep + "TonysPizzaText");
        File johnsMusicBlog = new File(System.getProperty("user.dir") + sep + "data" + sep + "JohnsMusicBlogText");
        File bestTechForum = new File(System.getProperty("user.dir") + sep + "data" + sep + "BestTechForumText");

        this.addWebpage(new Webpage("Tony's Pizza", tonysPizza));
        this.addWebpage(new Webpage("John's Music Blog", johnsMusicBlog));
        this.addWebpage(new Webpage("Best Tech Forum", bestTechForum));
    }

    // MODIFIES: this
    // EFFECTS: adds given webpage to webpages
    public void addWebpage(Webpage p) {
        webpages.add(p);
    }

    public ArrayList<Webpage> getWebpages() {
        return webpages;
    }

    public String getUrl() {
        return url;
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
