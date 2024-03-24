package ui;

// Main method. Run upon opening the game.
public class Main {
    //EFFECTS: creates a new instance of the game
    public static void main(String[] args) {
        //new Game();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gui();
            }
        });
    }
}
