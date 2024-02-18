package ui;

import model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Game {

    private boolean systemRunning;
    private LaunchPad launchPad;
    private Home home;
    private FiberSearch fiberSearch;
    private Scanner scan;

    public Game() {
        systemRunning = true;
        launchPad = new LaunchPad();
        home = new Home();
        scan = new Scanner(System.in);

        try {
            initializeFiberSearch();
        } catch (IOException e) {
            System.out.println("Fiber Search could not be initialized.");
        }
        runGame();
    }

    public void runGame() {
        String input;

        greetPlayer();
        while (systemRunning) {
            printOptions();
            input = scan.nextLine();
            handleInput(input);
        }

    }

    public void greetPlayer() {
        System.out.println("Welcome to Arch Technologies.");
    }

    public void printOptions() {
        System.out.println("Here are your downloaded apps. Enter the name of an app to run it.");
        for (Application a : home.getAppList()) {
            System.out.println(a.getName());
        }
    }

    public void handleInput(String input) {
        switch (input) {
            case "Fiber Search":
                runFiberSearch();
                break;
            default:
                System.out.println("No such application found.");
        }
    }

    public void runFiberSearch() {
        String input;

        System.out.println("Fiber Search");
        System.out.println("Search");
        System.out.println("Bookmarks");
        input = scan.nextLine();

        if (input.equals("Search")) {
            System.out.println("Search Fiber or type a URL");
            input = scan.nextLine();
            System.out.println("Search failed");
        } else if (input.equals("Bookmarks")) {
            for (Webpage p : fiberSearch.getWebpages()) {
                System.out.println(p.getName());
            }
            input = scan.nextLine();
            if (input.equals("Tony's Pizza")) {
                System.out.println(fiberSearch.getWebpages().get(0).getBody());
            }
        }
    }

    public void initializeFiberSearch() throws IOException {
        fiberSearch = new FiberSearch();
        home.addApplication(fiberSearch);

        fiberSearch.addWebpage(new Webpage("Tony's Pizza",
                Files.readString(Path.of("/Users/justinli/IdeaProjects/210project/src/main/ui/TonysPizzaText"))));
    }
}
