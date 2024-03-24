package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// The game. Contains all data relating to the game. Read README for a description of the game.
public class Game {

    private boolean systemRunning;
    private Home home;
    private FiberSearch fiberSearch;
    private MusicPlayer musicPlayer;
    private FileExplorer fileExplorer;
    private SeaShell seaShell;
    private Scanner scan;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: loads game file
    //          or creates new game with new Home, Scanner, FiberSearch, and MusicPlayer.
    //          runs game
    public Game() {
        systemRunning = true;
        scan = new Scanner(System.in);
        jsonWriter = new JsonWriter("./data/Game1");
        jsonReader = new JsonReader("./data/Game1");
        System.out.println("Load previous save?");
        String input = scan.nextLine();
        if (input.equalsIgnoreCase("yes")) {
            loadGame();
        } else {
            home = new Home();

            fiberSearch = new FiberSearch();
            musicPlayer = new MusicPlayer();
            fileExplorer = new FileExplorer();
            seaShell = new SeaShell();

            home.addApplication(fiberSearch);
            home.addApplication(musicPlayer);
            home.addApplication(fileExplorer);
            home.addApplication(seaShell);
        }

        runGame();
    }

    // EFFECTS: loads previously saved game
    public void loadGame() {
        try {
            home = jsonReader.read();
            fiberSearch = (FiberSearch) home.getAppList().get(0);
            musicPlayer = (MusicPlayer) home.getAppList().get(1);
            fileExplorer = (FileExplorer) home.getAppList().get(2);
            seaShell = (SeaShell) home.getAppList().get(3);
//            seaShell = new SeaShell();
//            home.addApplication(seaShell);
            System.out.println("Loaded game from previous save");
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
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

    // EFFECTS: handles keyboard input. calls appropriate application runner method.
    public void handleInput(String input) {
        switch (input.toLowerCase()) {
            case "fiber search":
                runFiberSearch();
                break;
            case "music player":
                runMusicPlayer();
                break;
            case "file explorer":
                runFileExplorer();
                break;
            case "sea shell":
                runSeaShell();
                break;
            case "exit":
                System.out.println("Save current game?");
                if (scan.nextLine().equalsIgnoreCase("yes")) {
                    saveGame();
                }
                System.exit(0);
                break;
            default:
                System.out.println("No such application found.");
        }
    }

    // EFFECTS: saves current game
    public void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(home);
            jsonWriter.close();
            System.out.println("Game saved successfully");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save game");
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
        } else if (input.equalsIgnoreCase("Best Tech Forum")) {
            try {
                browseBestTechForum();
            } catch (IOException e) {
                System.out.println("Best Tech Forum could not be found");
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
            if (input.equals("RECIPE")) {
                fileExplorer.addFile(new File("./data/pizzarecipe.txt"));
                System.out.println("Downloaded pizzarecipe.txt");
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
                fileExplorer.addFile(new File("./data/Imagine.wav"));
                System.out.println("Downloaded Imagine.wav");
            } else if (input.equalsIgnoreCase("download billie jean")) {
                musicPlayer.addSong("BillieJean");
                fileExplorer.addFile(new File("./data/BillieJean.wav"));
                System.out.println("Downloaded BillieJean.wav");
            }
        }
    }

    // EFFECTS: prints Best Tech Forum
    public void browseBestTechForum() throws IOException {
        String input = "";
        System.out.println(fiberSearch.getWebpages().get(2).getText());

        while (!input.equalsIgnoreCase("back")) {
            input = scan.nextLine();
        }
    }

    // EFFECTS: runs music player app and handles input concerning playing songs.
    public void runMusicPlayer() {
        String input = "";

        while (!input.equalsIgnoreCase("Quit")) {
            System.out.println("Music Player");
            System.out.println("Type song name to play");
            Map<String, File> songs = musicPlayer.getSongs();

            for (var entry : songs.entrySet()) {
                System.out.println("- " + entry.getValue().getName());
            }

            input = scan.nextLine();

            try {
                musicPlayer.playSong(input);
            } catch (Exception e) {
                System.out.println("Song could not be played");
            }

//            switch (input.toLowerCase()) {
//                case "play imagine":
//                    try {
//                        musicPlayer.playSong("Imagine");
//                    } catch (Exception e) {
//                        System.out.println("Song could not be played");
//                    }
//                    break;
//                case "play unknownaudio":
//                    try {
//                        musicPlayer.playSong("unknownaudio");
//                    } catch (Exception e) {
//                        System.out.println("Song could not be played");
//                    }
//                    break;
//                default:
//                    System.out.println("Could not find song");
//            }
        }
    }

    // EFFECTS: runs file explorer and handles input concerning files
    public void runFileExplorer() {
        String input = "";

        while (!input.equalsIgnoreCase("Quit")) {
            System.out.println("File Explorer");
            Set<String> files = fileExplorer.getFiles().keySet();

            for (String s : files) {
                System.out.println(s);
            }

            if (input.equalsIgnoreCase("pizza recipe")) {
                try {
                    System.out.println(fileExplorer.openTextFile("pizzarecipe.txt"));
                } catch (Exception e) {
                    System.out.println("File could not be opened");
                }
            } else if (input.equalsIgnoreCase("bible study")) {
                try {
                    System.out.println(fileExplorer.openTextFile("BibleStudyMail"));
                } catch (Exception e) {
                    System.out.println("File could not be opened");
                }
            }

            input = scan.nextLine();
        }
    }

    // EFFECTS: runs sea shell and handles command inputs
    public void runSeaShell() {
        String input = "";

        System.out.println("Sea Shell");

        while (!input.equalsIgnoreCase("Quit")) {
            input = scan.nextLine();

            int cs = seaShell.handleInput(input);

            if (cs == 1) {
                musicPlayer.addSong("unknownaudio");
            } else if (cs == 2) {
                fileExplorer.recoverFiles();
            }
        }
    }
}