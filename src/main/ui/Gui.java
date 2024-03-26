package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

// GUI of the game. "Virtual desktop". contains all model data
public class Gui extends JFrame implements ActionListener {
    private JDesktopPane desktop;
    private Home home;
    private FiberSearch fiberSearch;
    private MusicPlayer musicPlayer;
    private FileExplorer fileExplorer;
    private SeaShell seaShell;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: creates an empty desktop pane with a menu bar. initializes all model objects
    public Gui() {
        super("ArkNet");

        setupModel();

        JFrame.setDefaultLookAndFeelDecorated(true);
        setBounds(0, 0, 1920, 1080);

        desktop = new JDesktopPane();
        desktop.setBackground(new Color(168, 204, 255));
        setContentPane(desktop);
        setJMenuBar(createMenuBar());

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                quit();
            }
        });

        openFiberSearch(fiberSearch);

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes apps
    protected void setupModel() {
        jsonWriter = new JsonWriter("./data/Game1");
        jsonReader = new JsonReader("./data/Game1");

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

    // EFFECTS: returns a menu bar with apps as options
    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu appMenu = new JMenu("Apps");
        menuBar.add(appMenu);

        JMenuItem menuItem = new JMenuItem("Fiber Search");
        menuItem.addActionListener(e -> openFiberSearch(fiberSearch));
        appMenu.add(menuItem);

        menuItem = new JMenuItem("Music Player");
        menuItem.addActionListener(e -> openMusicPlayer());
        appMenu.add(menuItem);
//
//        menuItem = new JMenuItem("File Explorer");
//        menuItem.addActionListener(e -> openFileExplorer());
//        appMenu.add(menuItem);
//
//        menuItem = new JMenuItem("Sea Shell");
//        menuItem.addActionListener(e -> openSeaShell());
//        appMenu.add(menuItem);

        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // unused
    }

    // MODIFIES: this
    // EFFECTS: creates a fiber search frame
    protected void openFiberSearch(FiberSearch fiberSearch) {
        AppFrame frame = new FiberSearchFrame(fiberSearch, musicPlayer, fileExplorer);
        frame.setVisible(true);
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a music player frame
    protected void openMusicPlayer() {
        AppFrame frame = new MusicPlayerFrame(musicPlayer);
        frame.setVisible(true);
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a fiber search frame
//    protected void openFileExplorer() {
//        AppFrame frame = new FiberSearchFrame();
//        frame.setVisible(true);
//        desktop.add(frame);
//        try {
//            frame.setSelected(true);
//        } catch (java.beans.PropertyVetoException e) {
//            //
//        }
//    }
//
    // MODIFIES: this
    // EFFECTS: creates a fiber search frame
//    protected void openSeaShell() {
//        AppFrame frame = new FiberSearchFrame();
//        frame.setVisible(true);
//        desktop.add(frame);
//        try {
//            frame.setSelected(true);
//        } catch (java.beans.PropertyVetoException e) {
//            //
//        }
//    }

    // EFFECTS: presents options to quit and save the application
    protected void quit() {
        int n = JOptionPane.showConfirmDialog(this, "Save current progress?", "Quit", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            System.out.println("Game saved");
            System.exit(0);
        } else if (n == JOptionPane.NO_OPTION) {
            System.out.println("Game not saved");
            System.exit(0);
        } else {
            System.out.println("Game resumed");
        }
    }

}
