package ui;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

// Fiber Search frame. contains a FiberSearch object, MusicPlayer, and FileExplorer
public class FiberSearchFrame extends AppFrame {
    private FiberSearch fiberSearch;
    private MusicPlayer musicPlayer;
    private FileExplorer fileExplorer;
    private JTextField searchField;
    private JLabel bookmarks;
    private JLabel pizzaLink;
    private JLabel musicLink;
    private JLabel techLink;
    private JPanel pizzaPage;
    private JPanel musicPage;
    private JPanel techPage;
    private final int bookmarksX = 290;

    // MODIFIES: this
    // EFFECTS: creates AppFrame and initializes search field and bookmarks
    public FiberSearchFrame(FiberSearch fiberSearch, MusicPlayer musicPlayer, FileExplorer fileExplorer) {
        super("Fiber Search", 700, 500);
        this.setLayout(null);

        this.fiberSearch = fiberSearch;
        this.musicPlayer = musicPlayer;
        this.fileExplorer = fileExplorer;

        initSearchField();
        initBookmarks();
        addAll();
    }

    // MODIFIES: this
    // EFFECTS: initializes search field
    public void initSearchField() {
        searchField = new JTextField();
        searchField.setSize(300, 30);
        searchField.setLocation(190, 100);
        searchField.setText("Enter URL");

        InputMap im = searchField.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = searchField.getActionMap();
        im.put(KeyStroke.getKeyStroke("ENTER"), "search");
        am.put("search", new Search());
    }

    // MODIFIES: this
    // EFFECTS: initializes bookmark links
    public void initBookmarks() {
        bookmarks = new JLabel("<HTML><B>Bookmarks</B></HTML>");
        bookmarks.setSize(90, 20);
        bookmarks.setLocation(bookmarksX, 200);

        initPizzaLink();
        initMusicLink();
    }

    // MODIFIES: this
    // EFFECTS: initializes pizza link
    public void initPizzaLink() {
        pizzaLink = new JLabel("<HTML><U>Tony's Pizza</U></HTML>");
        pizzaLink.setForeground(Color.BLUE.darker());
        pizzaLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pizzaLink.addMouseListener(new MouseAdapter() {
            // EFFECTS: calls helper to open pizza page
            @Override
            public void mouseClicked(MouseEvent e) {
                openPizza();
            }
        });
        pizzaLink.setSize(90, 20);
        pizzaLink.setLocation(bookmarksX, 240);
    }

    // MODIFIES: this
    // EFFECTS: initializes music link
    public void initMusicLink() {
        musicLink = new JLabel("<HTML><U>John's Music Blog</U></HTML>");
        musicLink.setForeground(Color.BLUE.darker());
        musicLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        musicLink.addMouseListener(new MouseAdapter() {
            // EFFECTS: calls helper to open pizza page
            @Override
            public void mouseClicked(MouseEvent e) {
                openMusic();
            }
        });
        musicLink.setSize(120, 20);
        musicLink.setLocation(bookmarksX, 270);
    }

    // MODIFIES: this
    // EFFECTS: opens pizza page
    public void openPizza() {
        initPizzaPage();
        getContentPane().removeAll();
        getContentPane().add(pizzaPage);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: initializes pizza webpage
    public void initPizzaPage() {
        pizzaPage = new JPanel();
        pizzaPage.setSize(700, 500);
        pizzaPage.setLocation(0, 0);
        pizzaPage.setBackground(new Color(209, 89, 15));

        pizzaPage.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JTextArea pizzaText = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(pizzaText);
        pizzaText.setColumns(40);
        pizzaText.setLineWrap(true);
        pizzaText.setRows(15);
        pizzaText.setWrapStyleWord(true);
        pizzaText.setEditable(false);
        try {
            pizzaText.setText(fiberSearch.getWebpages().get(0).getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        pizzaPage.add(scrollPane, c);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> returnToSearch());
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        pizzaPage.add(backButton, c);

        JLabel recipeLink = new JLabel("<HTML><U>Download Recipe!</U></HTML>");
        recipeLink.setForeground(Color.BLUE.darker());
        recipeLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        recipeLink.addMouseListener(new MouseAdapter() {
            // EFFECTS: calls helper to download pizza recipe
            @Override
            public void mouseClicked(MouseEvent e) {
                downloadRecipe();
            }
        });
        c.gridx = 2;
        pizzaPage.add(recipeLink, c);

        BufferedImage logo = null;
        try {
            logo = ImageIO.read(new File("./data/pizzalogo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaledLogo = logo.getScaledInstance(150, 70, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(scaledLogo));
        c.gridx = 1;
        c.gridy = 0;
        pizzaPage.add(picLabel, c);
    }

    // MODIFES: fileExplorer
    // EFFECTS: download recipe
    public void downloadRecipe() {
        this.fileExplorer.addFile(new File("./data/pizzarecipe.txt"));
    }

    // MODIFIES: this
    // EFFECTS: opens music page
    public void openMusic() {
        initMusicPage();
        getContentPane().removeAll();
        getContentPane().add(musicPage);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: initializes music webpage
    public void initMusicPage() {
        musicPage = new JPanel();
        musicPage.setSize(700, 500);
        musicPage.setLocation(0, 0);
        musicPage.setBackground(new Color(15, 96, 209));

        musicPage.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel text = new JLabel("Welcome to John's Music Blog!");
        c.gridwidth = 3;
        c.insets = new Insets(0, 0, 10, 0);
        musicPage.add(text, c);
        text = new JLabel("Here are some songs I've been listening to recently...");
        c.gridy = 1;
        musicPage.add(text, c);

        JLabel songLink = new JLabel("<HTML><U>John Lennon - Imagine</U></HTML>");
        songLink.setForeground(Color.BLUE.darker());
        songLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        songLink.addMouseListener(new MouseAdapter() {
            // EFFECTS: calls helper to download song
            @Override
            public void mouseClicked(MouseEvent e) {
                downloadSong("Imagine");
            }
        });
        c.gridy = 2;
        musicPage.add(songLink, c);
        songLink = new JLabel("<HTML><U>Michael Jackson - Billie Jean</U></HTML>");
        songLink.setForeground(Color.BLUE.darker());
        songLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        songLink.addMouseListener(new MouseAdapter() {
            // EFFECTS: calls helper to download song
            @Override
            public void mouseClicked(MouseEvent e) {
                downloadSong("BillieJean");
            }
        });
        c.gridy = 3;
        musicPage.add(songLink, c);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> returnToSearch());
        c.gridy = 4;
        c.insets = new Insets(20, 0, 0, 0);
        musicPage.add(backButton, c);
    }

    // MODIFIES: musicPlayer
    // EFFECTS: downloads song with given name
    public void downloadSong(String name) {
        this.musicPlayer.addSong(name);
    }

    // MODIFIES: this
    // EFFECTS: returns to main search page
    public void returnToSearch() {
        getContentPane().removeAll();
        addAll();
        revalidate();
        repaint();
    }

    // class needed for search action
    class Search extends AbstractAction {
        // EFFECTS: displays machine gallery page if url is correct
        @Override
        public void actionPerformed(ActionEvent ev) {
            if (searchField.getText().equals(fiberSearch.getUrl())) {
                System.out.println("URL accessed");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: remove all initial components from frame
    public void removeHome() {
        getContentPane().remove(searchField);
        getContentPane().remove(bookmarks);
        getContentPane().remove(pizzaLink);
    }

    // MODIFIES: this
    // EFFECTS: adds all initial components to frame
    public void addAll() {
        getContentPane().add(searchField);
        getContentPane().add(bookmarks);
        getContentPane().add(pizzaLink);
        getContentPane().add(musicLink);
    }

}
