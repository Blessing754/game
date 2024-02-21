package main;
import entity.Player;
import tiles.TileManger;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel   implements Runnable {
    // starting with the game screen settings

    final int originalTileSize = 16; // The base size of each tile in the game, in pixels (16x16)
    final int scale = 3; // Scaling factor to enlarge the tiles, enhancing visibility
    public final int tileSize = originalTileSize * scale; // Final size of a tile after applying the scale factor
    public final int maxScreenCol = 16; // Maximum number of tiles that fit horizontally on the screen
    public final int maxScreenRow = 12; // Maximum number of tiles that fit vertically on the screen
    public final int screenWidth = tileSize * maxScreenCol; // Total pixel width of the game screen
    public final int screenHeight = tileSize * maxScreenRow; // Total pixel height of the game screen

    // World map settings

    public final int maxWorldCol = 50; // Maximum number of columns in the world map
    public final int maxWorldRow = 50; // Maximum number of rows in the world map
    public final int worldWidth = maxWorldCol * tileSize; // Total width of the world map in pixels
    public final int worldHeight = maxWorldRow * tileSize; // Total height of the world map in pixels

    int FPS = 60; // Target frames per second for the game

    TileManger tileM= new TileManger(this);// Instance of TileManager to handle tile operations
    KeyHandler keyH = new KeyHandler();// Instance of KeyHandler to listen for keyboard inputs

    Thread gameThread; // Thread for running the game loop at a consistent rate
    public Player player = new Player(this, keyH); // Instance of the Player class, linked to this panel and the key handler

    public GamePanel() {
        // Constructor for setting up the panel properties

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set preferred size of the panel
        this.setBackground(Color.black); // Set background color of the panel
        this.setDoubleBuffered(true); // Enable double buffering for smoother graphics rendering

        this.addKeyListener(keyH); // Add the key listener to the panel for keyboard input handling
        this.setFocusable(true); // Ensure the panel can gain focus to receive keyboard input
    }

    public void startGameThread() {
        // Method to start the game loop thread

        gameThread = new Thread(this); // Initialize the thread with this GamePanel as the Runnable target
        gameThread.start(); // Start the game loop thread
    }

    public void run() {
        // The game loop, responsible for updating and rendering the game

        double drawInterval = 1000000000 / FPS; // Time between draws in nanoseconds
        double delta = 0;
        long lastTime = System.nanoTime(); // Stores the last time the loop was run
        long currentTime; // Stores the current time for each loop iteration
        long timer = 0; // Timer for tracking FPS
        int drawCount = 0; // Counter for the number of frames drawn per second

        while (gameThread != null) { // Continue the loop as long as the gameThread is active
            currentTime = System.nanoTime(); // Capture current time
            delta += (currentTime - lastTime) / drawInterval; // Calculate elapsed time since last update
            timer += (currentTime - lastTime); // Update the timer
            lastTime = currentTime; // Set lastTime for the next loop iteration

            if (delta >= 1) { // Check if it's time to update and repaint
                update(); // Update game state
                repaint(); // Redraw the game
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) { // Check if one second has passed
                System.out.println("FPS: " + drawCount); // Output FPS to the console
                drawCount = 0; // Reset draw count for the next second
                timer = 0; // Reset timer
            }
        }
    }

    public void update() {
        player.update(); // Update player state (movement, interactions, etc.)
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call superclass method for standard painting

        Graphics2D g2 = (Graphics2D) g; // Cast Graphics to Graphics2D for advanced features

        tileM.draw(g2); // Draw tiles on the screen
        player.draw(g2); // Draw the player

        g2.dispose(); // Dispose of the graphics context to release system resources
    }
}