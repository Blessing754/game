package main;
import object.*;

import PlayerEntity.Player;
import PlayerEntity.Player2;
import object.SuperObject;
import tile.TileManager;
import PlayerEntity.BattleSystem;
import object.Weapon;
import main.StatusBoard;
import object.OBJ_Chest;
import object.OBJ_Trap;
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

    public TileManager tileM = new TileManager(this); // Manages the tiles in the game
    KeyHandler keyH = new KeyHandler(); // Handles keyboard input
    Thread gameThread; // Thread for the game loop
    public CollisionChecker cChecker = new CollisionChecker(this); // Checks for collisions
    public AssetSetter aSetter = new AssetSetter(this); // Sets up game assets
    Player player = new Player(this, keyH, 100, 500, 50); // Player entity
    Player2 player2 = new Player2(this, keyH, 100, 300, 12); // Second player entity
    public SuperObject obj[] = new SuperObject[50]; // Array for storing objects in the game
    BattleSystem battleSystem = new BattleSystem(); // Handles battle mechanics
    private StatusBoard statusBoard;


    // Player default position and speed
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 5;

    public GamePanel() {
        // Other initialization...
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        PlayerEntity[] playerEntities = {player, player2};
        turnManager = new main.TurnManager(playerEntities);


        statusBoard = new StatusBoard(player, player2);

        startGameThread();
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
        player.update();
        player2.update();
        checkCollision(); // Check for collisions between the player and huts
        refreshGamePanel(); // Refresh the display after processing updates
        statusBoard.updatePlayerStats();
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

        // Now use the new render method to draw the status board
        statusBoard.render(g2);

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


    public void checkCollision() {
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] instanceof OBJ_House) {
                OBJ_House house = (OBJ_House) obj[i];
                // Check and handle interactions for both players with the house
                interactWithHouse(player, house, i);
                interactWithHouse(player2, house, i);
            } else if (obj[i] instanceof OBJ_Chest) {
                // Check and handle interactions for both players with the chest
                interactWithChest(player, (OBJ_Chest) obj[i], i);
                interactWithChest(player2, (OBJ_Chest) obj[i], i);
            } else if (obj[i] instanceof OBJ_Trap) {
                // Check and handle interactions for both players with the trap
                interactWithTrap(player, (OBJ_Trap) obj[i], i);
                interactWithTrap(player2, (OBJ_Trap) obj[i], i);
            }
        }
    }

    private void interactWithHouse(PlayerEntity player, OBJ_House house, int index) {
        if (house != null && isPlayerCloseToHouse(player, house)) {
            int moneyCollected = house.collectItems();
            if (moneyCollected > 0) {
                player.addMoney(moneyCollected);
                obj[index] = null; // Remove the house after interaction
            }
        }
    }

    public boolean isPlayerCloseToHouse(PlayerEntity player, OBJ_House house) {
        // Calculate the distance between the player and the house
        double distance = Math.sqrt(Math.pow(player.getWorldX() - house.worldX, 2) + Math.pow(player.getWorldY() - house.worldY, 2));
        final int interactionRadius = 50; // Interaction radius in pixels
        return distance <= interactionRadius; // Check if within interaction radius
    }

    private void interactWithChest(PlayerEntity player, OBJ_Chest chest, int index) {
        if (chest != null && chest.collision && isPlayerCloseToChest(player, chest)) {
            SuperObject treasure = chest.getTreasure(); // Get the pre-generated treasure
            showTreasurePopup(treasure); // Show popup with the treasure name

            handleTreasure(player, treasure); // Handle the treasure obtained from the chest

            chest.collision = false; // Mark chest as interacted
            obj[index] = null; // Remove the chest from the game
        }
    }

    private void handleTreasure(PlayerEntity player, SuperObject treasure) {
        if (treasure instanceof Weapon) {
            handleWeapon(player, (Weapon) treasure);
        } else if (treasure instanceof OBJ_paladinShield) {
            handleShield(player, (OBJ_paladinShield) treasure);
        } else if (treasure instanceof OBJ_woodenbow) {
            handleBow(player, (OBJ_woodenbow) treasure);
        } else if (treasure instanceof OBJ_JewelEncrustedSword) {
            handleSword(player, (OBJ_JewelEncrustedSword) treasure);
        } else if (treasure instanceof OBJ_GoldenGoblet || treasure instanceof OBJ_CrystalGoblet) {
            handleGoblet(player, (SuperObject) treasure);
        } else if (treasure instanceof OBJ_Key || treasure instanceof OBJ_Diamondring) {
            handleMoney(player, (SuperObject) treasure);
        } else if (treasure instanceof OBJ_dragonscroll) {
            handlePower(player, (OBJ_dragonscroll) treasure);
        }
    }

    private void handleWeapon(PlayerEntity player, Weapon weapon) {
        player.addToInventory(weapon); // Add to inventory
        player.equipWeapon(weapon); // Equip the weapon
        player.setPower(player.getPower() + weapon.getStrengthBonus()); // Increase player's power
    }

    private void handleShield(PlayerEntity player, OBJ_paladinShield shield) {
        player.addToInventory(shield); // Add to inventory
        player.equipWeapon(shield); // Equip the shield
        player.setPower(player.getPower() + shield.getStrengthBonus());
    }

    private void handleBow(PlayerEntity player, OBJ_woodenbow bow) {
        player.addToInventory(bow); // Add to inventory
        player.equipWeapon(bow); // Equip the bow
        player.setPower(player.getPower() + bow.getStrengthBonus()); // Increase player's power
    }

    private void handleSword(PlayerEntity player, OBJ_JewelEncrustedSword sword) {
        player.addToInventory(sword); // Add to inventory
        player.equipWeapon(sword); // Equip the sword
        player.setPower(player.getPower() + sword.getStrengthBonus());// Increase player's power
    }
    private void handleGoblet(PlayerEntity player, SuperObject goblet) {
        if (goblet instanceof OBJ_GoldenGoblet) {
            ((OBJ_GoldenGoblet) goblet).giveHealthToPlayer(player); // Give health bonus to the player
        } else if (goblet instanceof OBJ_CrystalGoblet) {
            ((OBJ_CrystalGoblet) goblet).giveHealthToPlayer(player); // Give health bonus to the player
        } else {
            System.out.println("Unhandled goblet type: " + goblet.getClass().getSimpleName());
        }
    }


    private void handleMoney(PlayerEntity player, SuperObject moneyItem) {
        if (moneyItem instanceof OBJ_Key) {
            ((OBJ_Key) moneyItem).addMoneyToPlayer(player); // Add money to the player
        } else if (moneyItem instanceof OBJ_Diamondring) {
            ((OBJ_Diamondring) moneyItem).addMoneyToPlayer(player); // Add money to the player
        }
    }

    private void handlePower(PlayerEntity player, OBJ_dragonscroll dragonScroll) {
        dragonScroll.addPowerToPlayer(player); // Add power to the player
    }



    // Method to show a popup message with the found treasure
    private void showTreasurePopup(SuperObject treasure) {
        JOptionPane.showMessageDialog(null, "You found a " + treasure.getName() + "!", "Treasure Found", JOptionPane.INFORMATION_MESSAGE);
    }


    private boolean isPlayerCloseToChest(PlayerEntity player, OBJ_Chest chest) {
        double distance = Math.sqrt(Math.pow(player.worldX - chest.worldX, 2) + Math.pow(player.worldY - chest.worldY, 2));
        final int interactionRadius = 50;
        return distance <= interactionRadius;
    }

    private void interactWithTrap(PlayerEntity player, OBJ_Trap trap, int index) {
        if (trap != null && trap.collision && isPlayerCloseToTrap(player, trap)) {
            // Assuming there's a method in OBJ_Trap to apply the trap's effect
            trap.triggerEffect(player);
            // Optionally, remove or disable the trap after interaction
            trap.collision = false; // Example: Mark the trap as non-collidable after triggering
            // If the trap should be removed after triggering, uncomment the next line
            // obj[index] = null;
        }
    }

    private boolean isPlayerCloseToTrap(PlayerEntity player, OBJ_Trap trap) {
        double distance = Math.sqrt(Math.pow(player.worldX - trap.worldX, 2) + Math.pow(player.worldY - trap.worldY, 2));
        final int interactionRadius = 50; // Interaction radius in pixels
        return distance <= interactionRadius;
    }



public void refreshGamePanel() {
        this.repaint();  // Calls the paintComponent method to redraw the game panel
    }
}
