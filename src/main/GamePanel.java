package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel   implements Runnable {
    // starting with the game screen settings

    final int originalTileSize = 16; // The base size of a tile in the game (16x16 pixels)
    final int scale = 3; // Scaling factor to enlarge the tiles for better visibility
     public final int tileSize = originalTileSize * scale; // Final size of a tile after scaling
    final int maxScreenCol = 16; // Number of tiles that can fit horizontally on the screen
    final int maxScreenRow = 12; // Number of tiles that can fit vertically on the screen
    final int screenWidth = tileSize * maxScreenCol; // Total width of the game screen in pixels
    final int screenHeight = tileSize * maxScreenRow; // Total height of the game screen in pixels

    int FPS=60; // Target frames per second

    // Instance of KeyHandler to listen for keyboard inputs
    KeyHandler keyH = new KeyHandler();

    Thread gameThread; // we can use it to create 60 frames per second, Thread for running the game loop
    Player player=new Player(this,keyH);

    // set player default position, Player's initial position and speed
    int playerX = 100;  // Player's starting X-coordinate
    int playerY=100;    // Player's starting Y-coordinate
    int playerSpeed=4;  // Number of pixels the player moves per update


    public GamePanel() {
        // Set the size, background color, and double-buffering (for smooth rendering) of the panel

       this.setPreferredSize(new Dimension(new Dimension(screenWidth, screenHeight)));
       this.setBackground(Color.black);
       this.setDoubleBuffered(true);

        // Add the key listener to the panel and make sure it can gain focus for keyboard input
       this.addKeyListener(keyH);

       this.setFocusable(true);

    }

    public void startGameThread() {
        // Create and start the game loop thread
        gameThread = new Thread(this);

        gameThread.start();
    }



    public void run() {
        // Game loop for updating and rendering the game at a consistent rate
        double drawInterval = (double) 1000000000 / FPS; // Time between draws in nanoseconds

        double delta = 0;

        long lastTime = System.nanoTime(); // Stores the last time the loop was run

        long currentTime; // Current time for each loop iteration

        long timer = 0; // Timer for tracking FPS

        int drawCount = 0; // Counter for the number of draws (frames) in a second


        while (gameThread != null) { // The game loop checks if the gameThread object is not null to continue running the loop

            // Capture the current system time in nanoseconds for this loop iteration
            currentTime = System.nanoTime();


            // This is used to maintain a consistent update rate regardless of processing speed, Calculate the amount of time that has passed since the last iteration ('delta').
            delta += (currentTime - lastTime) / drawInterval;

            // Accumulate the total time passed since the last FPS update
            timer += (currentTime - lastTime);

            // Update 'lastTime' to the current time for the next iteration
            lastTime = currentTime;

            // Check if 'delta' has accumulated enough to signify that it's time for an update
            if (delta >= 1) {
                // Update the game state (e.g., player position, game logic)
                update();

                // Redraw the game visuals on the screen
                repaint();

                // Decrement 'delta' to indicate that an update and repaint have been completed
                delta--;

                // Increment the frame draw counter, used to track how many times the game updates per second
                drawCount++;
            }

            if (timer >= 1000000000) { // Check if one second has passed to update the FPS counter

                // Output the number of frames that were drawn in the last second (FPS) to the console
                System.out.println("FPS: " + drawCount);

                // Reset the draw count and timer for the next second
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        player.update(); // we are calling this method form the Player File.java to make it more readable
    }
    public void paintComponent(Graphics g) {// Call the superclass's paintComponent method to ensure

        // proper rendering of the JPanel
        super.paintComponent(g);

        // Cast the Graphics object to Graphics2D for more advanced features
        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2); // here we are calling the draw function from the player class

        // Dispose of the graphics context to release system resources
        g2.dispose();
    }
}
