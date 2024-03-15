package main;
import javax.swing.JFrame;
public class Main {
    public static void main(String[] args) {

        // Create the main application window using JFrame, a class used to create a top-level window with a title and a border
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("MY game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        gamePanel.startGameThread();

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}
