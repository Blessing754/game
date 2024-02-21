package main;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // Create the main application window using JFrame, a class used to create a top-level window with a title and a border
        JFrame window = new JFrame();

        // Ensures that the application exits when the window is closed by the user
        // This is important as it stops the program from running in the background after the window is closed
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Prevents the user from being able to resize the window, ensuring a consistent layout
        // This is often used in games to maintain a fixed display area
        window.setResizable(false);

        // Sets the text that appears in the title bar of the window
        // This is typically the name of the application or game
        window.setTitle("2D Adventure");

        // Instantiate the GamePanel class, which will handle the game's graphics and logic
        // This class is assumed to extend a component like JPanel and contain game-related methods
        GamePanel gamePanel = new GamePanel();

        // Adds the GamePanel instance to the JFrame window, so it will be displayed
        // The GamePanel is where the game's graphics and interactions are likely handled
        window.add(gamePanel);

        // Adjusts the size of the window to fit the preferred size and layouts of its subcomponents (here, the GamePanel)
        // This method is important to ensure all components of the GamePanel are displayed correctly
        window.pack();

        // Centers the window in relation to the screen
        // This is for user convenience, so the window appears in the middle of the screen when the application starts
        window.setLocationRelativeTo(null);

        // Makes the window visible; until this is called, the window won't be displayed
        // This is typically the last method called on the JFrame after all components have been added and set up
        window.setVisible(true);

        // Initiates the game loop contained in the GamePanel class
        // This method would be responsible for starting the main game mechanics, such as rendering and updating game states
        gamePanel.startGameThread();
    }
}
