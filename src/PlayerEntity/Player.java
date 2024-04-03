package PlayerEntity;

import main.GamePanel;
import main.KeyHandler;
import object.Weapon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Player extends PlayerEntity {

    public int screenX;
    public int screenY;
    private int stepsRemaining;

    public int money; // Add money property
    private int strength; // Add strength property
    private Dice dice;
    private List<Weapon> inventory;
    private Weapon equippedWeapon;
    GamePanel gp;
    KeyHandler keyH;
    int sword = 0;

    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH, int health, int money, int power) {
        //super(200, 500, 150);
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 48;

        solidAreaDX = solidArea.x;
        solidAreaDY = solidArea.y;

        dice = new Dice();

        setDefaultValues();
        getPlayerImage();
        this.name = "Player 1";
        startingX = 768; // Your starting X, assuming it's a constant
        startingY = 384; // Your starting Y, assuming it's a constant
        this.setMoney(500);
        this.setHealth(200);
        this.setPower(150); // Initialize with some strength
        this.inventory = new ArrayList<>();
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setDefaultValues() {

        worldX = 768;
        worldY = 384;
        speed = 2;
        direction = null;
    }

    public void addMoney(int amount) {
        this.money += amount;
        System.out.println("Player Money Updated: " + this.money);
    }

    @Override
    public int getMoney() {
        return this.money;
    }
    public List<Weapon> getInventory() {
        return inventory;
    }
    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/back0.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/back1.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/back2.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/back3.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/fron0.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/fron1.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/fron2.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/fron3.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left0.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/left3.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right0.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/right3.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (gp.turnManager.isPlayerTurn(this) && !moving) {
            // Player must press 'R' to roll the dice if steps are 0.
            if (stepsRemaining == 0 && keyH.rPressed) {
                movingTurn = true;
                stepsRemaining = dice.roll();
                System.out.println("Dice rolled: " + stepsRemaining + " steps.");
                keyH.rPressed = false; // Reset the dice roll flag to prevent multiple rolls in one turn.
            }

            // Proceed with the movement if the dice has been rolled.
            if (stepsRemaining > 0 && (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)) {
                setDirectionAndTargetPosition();

                collisionOn = gp.cChecker.checkTileCollision(this, targetX, targetY);
                if (!collisionOn) {
                    moving = true;
                    stepsRemaining--; // Decrement steps after each move
                }

                // Reset movement keys to prevent continuous movement
                if (keyH.upPressed) keyH.upPressed = false;
                if (keyH.downPressed) keyH.downPressed = false;
                if (keyH.leftPressed) keyH.leftPressed = false;
                if (keyH.rightPressed) keyH.rightPressed = false;

                // End the player's turn if they have no more steps to move
                if (stepsRemaining == 0) {
                    movingTurn = false;
                    gp.turnManager.endTurn();
                }
            }
        }

        // If in motion, continue moving towards the target
        if (moving) {
            moveTowardsTarget();
            updateAnimation();
        }

        // Check for object interactions
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);
        //System.out.println("Player 1: " + worldX + " and " + worldY);
    }


    public void pickUpObject(int i) {
        if (i != 999) {
            String ObjectName = gp.obj[i].name;

            switch (ObjectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("key:" + hasKey);
                    break;

                case "Castle":
                    if (hasKey >= 3) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    System.out.println("key:" + hasKey);

                    break;

            }
        }
    }

    private void setDirectionAndTargetPosition() {
        int tileSize = gp.tileSize;

        if (keyH.upPressed) {
            direction = "up";
            targetX = worldX;
            targetY = worldY - tileSize;
        } else if (keyH.downPressed) {
            direction = "down";
            targetX = worldX;
            targetY = worldY + tileSize;
        } else if (keyH.leftPressed) {
            direction = "left";
            targetX = worldX - tileSize;
            targetY = worldY;
        } else if (keyH.rightPressed) {
            direction = "right";
            targetX = worldX + tileSize;
            targetY = worldY;
        }
    }

    private void moveTowardsTarget() {
        if (Math.abs(targetX - worldX) > speed) {
            worldX += speed * (targetX > worldX ? 1 : -1);
        } else {
            worldX = targetX;
        }

        if (Math.abs(targetY - worldY) > speed) {
            worldY += speed * (targetY > worldY ? 1 : -1);
        } else {
            worldY = targetY;
        }

        // Stop moving when the target is reached
        if (worldX == targetX && worldY == targetY) {
            moving = false;
        }
    }

    private void updateAnimation() {
        // Your sprite animation update logic here
        spriteCounter++;
        if (spriteCounter > 7) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 1;
            }
            spriteCounter = 0;

        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (direction != null) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    } else if (spriteNum == 2) {
                        image = up2;
                    } else if (spriteNum == 3) {
                        image = up3;
                    } else if (spriteNum == 4) {
                        image = up4;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    } else if (spriteNum == 2) {
                        image = down2;
                    } else if (spriteNum == 3) {
                        image = down3;
                    } else if (spriteNum == 4) {
                        image = down4;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    } else if (spriteNum == 2) {
                        image = left2;
                    } else if (spriteNum == 3) {
                        image = left3;
                    } else if (spriteNum == 4) {
                        image = left4;
                    }

                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    } else if (spriteNum == 2) {
                        image = right2;
                    } else if (spriteNum == 3) {
                        image = right3;
                    } else if (spriteNum == 4) {
                        image = right4;
                    }
                    break;
            }
        }
        if (image == null) {
            image = down1_p2; // Default image
        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
    @Override
    public boolean purchaseWeapon(Weapon weapon) {
        // Check if the weapon is already in the inventory
        if (inventory.contains(weapon)) {
            System.out.println(this.name + " already owns " + weapon.getName() + ".");
            return false; // Indicate the purchase did not happen
        }

        // Check if the player has enough money to purchase the weapon
        if (this.money >= weapon.getPrice()) {
            this.money -= weapon.getPrice(); // Deduct the weapon's cost from the player's money
            inventory.add(weapon); // Add the weapon to the player's inventory
            System.out.println(this.name + " purchased " + weapon.getName() + ".");
            return true; // Indicate a successful purchase
        } else {
            System.out.println(this.name + " does not have enough money to purchase " + weapon.getName() + ".");
            return false; // Indicate the purchase did not happen
        }
    }
    // Method to equip a weapon
    public void equipWeapon(Weapon weapon) {
        if (this.inventory.contains(weapon)) {
            this.equippedWeapon = weapon; // Equip the weapon if it's in the inventory
            System.out.println(weapon.getName() + " has been equipped.");
        } else {
            System.out.println(weapon.getName() + " cannot be equipped because it is not in the inventory.");
        }
    }

    public int calculateTotalStrength() {
        int totalStrength = this.power;
        if (equippedWeapon != null) {
            totalStrength += equippedWeapon.getStrengthBonus();
        }
        return totalStrength;
    }


    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }
}