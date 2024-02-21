

package main;
import javax.swing.JFrame;
public class Main {
    public static void main(String[] args) {
        // Create the main application window using JFrame, a class used to create a top-level window with a title and a border
        JFrame window = new JFrame();

        // Ensures that the application exits when the window is closed by the user
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Prevents the user from being able to resize the window, ensuring a consistent layout
        window.setResizable(false);

        // Sets the text that appears in the title bar of the window
        window.setTitle("2D Adventure");

        // Instantiate the GamePanel class, which will handle the game's graphics and logic
        GamePanel gamePanel = new GamePanel();


        // Adds the GamePanel instance to the JFrame window, so it will be displayed
        window.add(gamePanel);

        // Adjusts the size of the window to fit the preferred size and layouts of its subcomponents (here, the GamePanel)
        window.pack();

        // Centers the window in relation to the screen
        window.setLocationRelativeTo(null);

        // Makes the window visible; until this is called, the window won't be displayed
        window.setVisible(true);

        // Initiates the game loop contained in the GamePanel class
        gamePanel.startGameThread();

    }
}