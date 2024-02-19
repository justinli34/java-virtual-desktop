package ui;

import model.*;

import java.io.File;
import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private boolean systemRunning;
    private LaunchPad launchPad;
    private Home home;
    private FiberSearch fiberSearch;
    private MusicPlayer musicPlayer;
    private Scanner scan;

    // MODIFIES: this
    // EFFECTS: creates new game with new Launchpad, Home, Scanner, FiberSearch, and MusicPlayer.
    //          runs game
    public Game() {
        systemRunning = true;
        launchPad = new LaunchPad();
        home = new Home();
        scan = new Scanner(System.in);
        fiberSearch = new FiberSearch();
        musicPlayer = new MusicPlayer();

        home.addApplication(fiberSearch);
        home.addApplication(musicPlayer);

        runGame();
    }

    public void runGame() {
        String input;

        greetPlayer();
        while (systemRunning) {
            printApps();
            input = scan.nextLine();
            handleInput(input);
        }
    }

    public void greetPlayer() {
        System.out.println("Welcome to Arch Technologies.");
    }

    public void printApps() {
        System.out.println("Here are your downloaded apps. Enter the name of an app to run it.");
        for (Application a : home.getAppList()) {
            System.out.println(a.getName());
        }
    }

    public void handleInput(String input) {
        switch (input.toLowerCase()) {
            case "fiber search":
                runFiberSearch();
                break;
            case "music player":
                runMusicPlayer();
                break;
            default:
                System.out.println("No such application found.");
        }
    }

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