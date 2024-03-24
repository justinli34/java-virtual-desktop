package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

public class Gui extends JFrame implements ActionListener {
    private JDesktopPane desktop;
    private Home home;
    private FiberSearch fiberSearch;
    private MusicPlayer musicPlayer;
    private FileExplorer fileExplorer;
    private SeaShell seaShell;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public Gui() {
        super("ArkNet");

        setupModel();

        JFrame.setDefaultLookAndFeelDecorated(true);
        setBounds(0, 0, 1920, 1080);

        desktop = new JDesktopPane();
        setContentPane(desktop);
        setJMenuBar(createMenuBar());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

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

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //Set up the lone menu.
        JMenu menu = new JMenu("Apps");
//        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        //Set up the first menu item.
        JMenuItem menuItem = new JMenuItem("New");
        menuItem.setActionCommand("new");
        menuItem.addActionListener(e -> createFrame());
        menu.add(menuItem);

        //Set up the second menu item.
        menuItem = new JMenuItem("Quit");
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(e -> quit());
        menu.add(menuItem);

        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    // unused
    }

    //Create a new internal frame.
    protected void createFrame() {
        AppFrame frame = new AppFrame();
        frame.setVisible(true);
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            //
        }
    }

    //Quit the application.
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
