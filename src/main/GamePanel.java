package main;

import PlayerEntity.Player;
import PlayerEntity.Player2;
import object.SuperObject;
import tile.TileManager;
import PlayerEntity.BattleSystem;
import object.Weapon;


import java.awt.*;
import javax.swing.*;
import PlayerEntity.PlayerEntity;
import object.OBJ_House;

public class GamePanel extends JPanel implements Runnable {

    // screen settings

    // Screen settings
    final int OriginalTileSize = 32; // Original size of tiles (32x32 pixels)
    final int scale = 2; // Scaling factor for the tiles

    // Calculating scaled tile size and screen dimensions
    public final int tileSize = OriginalTileSize * scale;
    public final int maxScreenCol = 14;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol; // 896 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 896 pixels
    public main.TurnManager turnManager;

    // World settings
    public final int maxWorldCol = 14;
    public final int maxWorldRow = 14;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;


    // Game object instances
    TileManager tileM = new TileManager(this); // Manages the tiles in the game
    KeyHandler keyH = new KeyHandler(); // Handles keyboard input
    Thread gameThread; // Thread for the game loop
    public CollisionChecker cChecker = new CollisionChecker(this); // Checks for collisions
    public AssetSetter aSetter = new AssetSetter(this); // Sets up game assets
    Player player = new Player(this, keyH, 100, 500, 50); // Player entity
    Player2 player2 = new Player2(this, keyH, 100, 300, 12); // Second player entity
    public SuperObject obj[] = new SuperObject[50]; // Array for storing objects in the game
    BattleSystem battleSystem = new BattleSystem(); // Handles battle mechanics


    // Player default position and speed
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 5;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set preferred size of the panel
        this.setBackground(Color.black); // Set the background color
        this.setDoubleBuffered(true); // Enable double buffering
        this.addKeyListener(keyH); // Add the key handler
        this.setFocusable(true); // Set focusable to true to receive keyboard inputs
        PlayerEntity[] playerEntities = {player, player2};
        turnManager = new main.TurnManager(playerEntities);
        startGameThread(); // Start the game thread

    }

    public void setupGame() {
        aSetter.setObject(); // Initialize game objects
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // Start the game thread
    }

    @Override
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

            delta += (currentTime - lastTime) / drawInterval;

            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if (delta >= 1) {
                // Update the game state (e.g., player position, game logic)
                update();

                repaint();

                delta--;

                drawCount++;
            }

            if (timer >= 1000000000) {
                //System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update(); // Update player 1
        player2.update(); // Update player 2
        checkCollision(); // Check for collisions between the player and huts
        refreshGamePanel(); // Refresh the display after processing updates
        // Check for battles
            if (player.getWorldX() == player2.getWorldX() && player.getWorldY() == player2.getWorldY()) {
                battleSystem.engageBattle(player, player2); // Engage in battle
            }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        player.draw(g2);
        player2.draw(g2);
        drawGrid(g);
        g2.dispose();
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.black);
        // Draw the vertical lines
        for (int x = 0; x <= screenWidth; x += tileSize) {
            g.drawLine(x, 0, x, screenHeight);
        }
        // Draw the horizontal lines
        for (int y = 0; y <= screenHeight; y += tileSize) {
            g.drawLine(0, y, screenWidth, y);
        }
    }

    public boolean isPlayerCloseToHouse(Player player, OBJ_House house) {
        // Calculate the distance between Player and the house
        double distance = Math.sqrt(Math.pow(player.worldX - house.worldX, 2) + Math.pow(player.worldY - house.worldY, 2));
        final int interactionRadius = 50; // Interaction radius in pixels
        return distance <= interactionRadius; // Check if within interaction radius
    }

    // Overloaded method for Player2
    public boolean isPlayerCloseToHouse(Player2 player2, OBJ_House house) {
        // Calculate the distance between Player2 and the house
        double distance = Math.sqrt(Math.pow(player2.worldX - house.worldX, 2) + Math.pow(player2.worldY - house.worldY, 2));
        final int interactionRadius = 50; // Interaction radius in pixels
        return distance <= interactionRadius; // Check if within interaction radius
    }

    public void checkCollision() {
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] instanceof OBJ_House) {
                OBJ_House house = (OBJ_House) obj[i];
                if (isPlayerCloseToHouse(player, house)) {
                    int moneyCollected = house.collectItems();
                    if (moneyCollected > 0) {
                        player.addMoney(moneyCollected);
                        obj[i] = null;  // Remove the house after interaction
                    }
                } else if (isPlayerCloseToHouse(player2, house)) {
                    int moneyCollected = house.collectItems();
                    if (moneyCollected > 0) {
                        player2.addMoney(moneyCollected);
                        obj[i] = null;  // Remove the house after interaction
                    }
                }
            }

            if (obj[i] instanceof Weapon) {
                if (isPlayerCloseToWeapon(player, obj[i])) {
                    handleWeaponInteraction(player, obj[i], i);
                } else if (isPlayerCloseToWeapon(player2, obj[i])) {
                    handleWeaponInteraction(player2, obj[i], i);
                }
            }
        }
    }

    // New method within GamePanel to manage weapon interactions
    private void handleWeaponInteraction(PlayerEntity player, SuperObject weapon, int index) {
        if (weapon instanceof Weapon && ((Weapon) weapon).isInteractable()) {
            Weapon actualWeapon = (Weapon) weapon;
            if (!actualWeapon.hasBeenInteracted()) {
                boolean purchased = false;
                if (player instanceof Player) {
                    Player p = (Player) player;
                    purchased = p.purchaseWeapon(actualWeapon);
                    if (purchased) {
                        p.equipWeapon(actualWeapon);  // Equip the weapon
                    }
                } else if (player instanceof Player2) {
                    Player2 p2 = (Player2) player;
                    purchased = p2.purchaseWeapon(actualWeapon);
                    if (purchased) {
                        p2.equipWeapon(actualWeapon);  // Equip the weapon
                    }
                }

                if (purchased) {
                    actualWeapon.setHasBeenInteracted(true);
                    obj[index] = null;  // Remove the weapon from the game
                }
            }
        }
    }

    public boolean isPlayerCloseToWeapon(PlayerEntity player, SuperObject weapon) {
        // Determine if player is close enough to interact with the weapon
        double distance = Math.sqrt(Math.pow(player.worldX - weapon.worldX, 2) + Math.pow(player.worldY - weapon.worldY, 2));
        final int interactionRadius = 50;  // Define an interaction radius
        return distance <= interactionRadius;
    }

    public void refreshGamePanel() {
        this.repaint();  // Calls the paintComponent method to redraw the game panel
    }
}