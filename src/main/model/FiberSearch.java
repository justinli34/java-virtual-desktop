package model;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FiberSearch extends Application {
    private ArrayList<Webpage> webpages;

    // MODIFIES: this
    // EFFECTS: creates a FiberSearch object with 2 webpages: Tony's Pizza and John's Music Blog
    public FiberSearch() {
        super("Fiber Search");
        webpages = new ArrayList<>();

        String sep = System.getProperty("file.separator");
        File tonysPizza = new File(System.getProperty("user.dir") + sep + "src" + sep + "main"
                + sep + "resources" + sep + "TonysPizzaText");
        File johnsMusicBlog = new File(System.getProperty("user.dir") + sep + "src" + sep + "main"
                + sep + "resources" + sep + "JohnsMusicBlogText");

        this.addWebpage(new Webpage("Tony's Pizza", tonysPizza));
        this.addWebpage(new Webpage("John's Music Blog", johnsMusicBlog));
    }

    public void addWebpage(Webpage p) {
        webpages.add(p);
    }

    public ArrayList<Webpage> getWebpages() {
        return webpages;
    }
}
