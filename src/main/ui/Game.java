package ui;

import model.*;

import java.io.File;
import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private boolean systemRunning;
    private Home home;
    private FiberSearch fiberSearch;
    private MusicPlayer musicPlayer;
    private Scanner scan;

    // MODIFIES: this
    // EFFECTS: creates new game with new Home, Scanner, FiberSearch, and MusicPlayer.
    //          runs game
    public Game() {
        systemRunning = true;
        home = new Home();
        scan = new Scanner(System.in);
        fiberSearch = new FiberSearch();
        musicPlayer = new MusicPlayer();

        home.addApplication(fiberSearch);
        home.addApplication(musicPlayer);

        runGame();
    }

    // EFFECTS: while the game is running, prints list of apps and handles keyboard input
    public void runGame() {
        String input;

        greetPlayer();

        while (systemRunning) {
            printApps();
            System.out.println("Type \"exit\" to exit ArkNet");
            input = scan.nextLine();
            handleInput(input);
        }
    }

    // EFFECTS: prints greeting upon entering the game
    public void greetPlayer() {
        System.out.println("Welcome to Arch Technologies.");
    }

    // EFFECTS: prints out list of installed apps
    public void printApps() {
        System.out.println("Here are your downloaded apps. Enter the name of an app to run it.");
        for (Application a : home.getAppList()) {
            System.out.println(a.getName());
        }
    }

    // EFFECTS: handles keyboard input concerning running applications. calls appropriate application runner method.
    public void handleInput(String input) {
        switch (input.toLowerCase()) {
            case "fiber search":
                runFiberSearch();
                break;
            case "music player":
                runMusicPlayer();
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("No such application found.");
        }
    }

    // EFFECTS: runs Fiber Search app and handles inputs concerning search options by calling helper methods.
    public void runFiberSearch() {
        String input = "";

        while (!input.equalsIgnoreCase("Quit")) {
            System.out.println("Fiber Search");
            System.out.println("- Search");
            System.out.println("- Bookmarks");
            input = scan.nextLine();

            if (input.equalsIgnoreCase("Search")) {
                while (true) {
                    System.out.println("Search Fiber or type a URL");
                    input = scan.nextLine();
                    if (input.equalsIgnoreCase("back")) {
                        break;
                    }
                    System.out.println("Search failed");
                }
            } else if (input.equalsIgnoreCase("Bookmarks")) {
                for (Webpage p : fiberSearch.getWebpages()) {
                    System.out.println(p.getName());
                }
                browseWebpages();
            }
        }
    }

    // EFFECTS: handles input concerning accessing webpages. calls appropriate webpage runner methods
    public void browseWebpages() {
        String input = scan.nextLine();
        if (input.equalsIgnoreCase("Tony's Pizza")) {
            try {
                browseTonysPizza();
            } catch (IOException e) {
                System.out.println("Tony's Pizza could not be found");
            }
        } else if (input.equalsIgnoreCase("John's Music Blog")) {
            try {
                browseJohnsMusicBlog();
            } catch (IOException e) {
                System.out.println("John's Music Blog could not be found");
            }
        }
    }

    // EFFECTS: prints Tony's Pizza webpage and handles input
    public void browseTonysPizza() throws IOException {
        String input = "";
        System.out.println(fiberSearch.getWebpages().get(0).getText());
        while (!input.equalsIgnoreCase("back")) {
            input = scan.nextLine();
            if (input.equals("P1ZZ4")) {
                System.out.println("-Pizza image-");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prints John's Music Blog webpage and handles input concerning downloading songs
    public void browseJohnsMusicBlog() throws IOException {
        String input = "";
        System.out.println(fiberSearch.getWebpages().get(1).getText());
        while (!input.equalsIgnoreCase("back")) {
            input = scan.nextLine();
            if (input.equalsIgnoreCase("download imagine")) {
                musicPlayer.addSong("Imagine");
                System.out.println("Downloaded Imagine by John Lennon");
            }
        }
    }

    // EFFECTS: runs music player app and handles input concerning playing songs.
    public void runMusicPlayer() {
        String input = "";

        while (!input.equalsIgnoreCase("Quit")) {
            System.out.println("Music Player");
            System.out.println("Type play + song name");
            ArrayList<File> songs = musicPlayer.getSongs();

            for (int i = 0; i < songs.size(); i++) {
                System.out.println(String.valueOf(i + 1) + ". " + songs.get(i).getName());
            }

            input = scan.nextLine();

            switch (input.toLowerCase()) {
                case "play imagine":
                    musicPlayer.playSong(0);
                    break;
                default:
                    System.out.println("Could not find song");
            }
        }
    }
}