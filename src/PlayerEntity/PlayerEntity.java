package PlayerEntity;
import object.Weapon;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import object.SuperObject;

public class PlayerEntity {
    // Attributes
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4,
            right1, right2, right3, right4;
    public BufferedImage
            up1_p2, up2_p2, up3_p2, up4_p2, up5_p2, up6_p2, up7_p2, up8_p2, up9_p2, // Up
            down1_p2, down2_p2, down3_p2, down4_p2, down5_p2, down6_p2, down7_p2, down8_p2, down9_p2, // Down
            left1_p2, left2_p2, left3_p2, left4_p2, left5_p2, left6_p2, left7_p2, left8_p2, left9_p2, // Left
            right1_p2, right2_p2, right3_p2, right4_p2, right5_p2, right6_p2, right7_p2, right8_p2, right9_p2; // Right
    public String direction;
    public int targetX, targetY;
    public boolean moving = false;
    public boolean movingTurn = false;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(); // Initialized to prevent null reference
    public int solidAreaDX, solidAreaDY;
    public boolean collisionOn = false;
    public int stepsRemaining;
    public int health;
    public int money;
    public int power;
    public int startingX, startingY; // Starting position
    public String name;
    private Weapon equippedWeapon;
    private int baseStrength = 10;
    public ArrayList<SuperObject> inventory = new ArrayList<>();
    // Constructor with parameters
    public PlayerEntity(String name, int health, int money, int power) {
        this.name = name;
        this.health =100;
        this.money = 500;
        this.power = 100;
        this.inventory = new ArrayList<>();
    }


    public PlayerEntity(int startX, int startY, int startSpeed) {
        // Set starting positions and speed
        this.worldX = startX;
        this.worldY = startY;
        this.speed = startSpeed;
    }

    public PlayerEntity() {
        this( 0, 0, 0); // Default name, positions (0,0), and speed of 0
    }

    public boolean purchaseWeapon(Weapon weapon) {
        if (inventory.stream().anyMatch(item -> item instanceof Weapon && ((Weapon)item).getName().equals(weapon.getName()))) {
            System.out.println("Already owns " + weapon.getName());
            return false;
        }
        if (money >= weapon.getPrice()) {
            money -= weapon.getPrice();
            inventory.add(weapon);  // Add weapon to inventory without equipping
            System.out.println("Purchased " + weapon.getName());
            return true;
        }
        System.out.println("Not enough money to purchase " + weapon.getName());
        return false;
    }

    public int calculateTotalStrength() {
        int totalStrength = this.power;  // Start with base power
        if (equippedWeapon != null) {
            totalStrength += equippedWeapon.getStrengthBonus();  // Add weapon strength bonus if equipped
        }
        // Optionally, iterate over inventory to add bonuses from other items, if applicable
        return totalStrength;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }
    public void addToInventory(SuperObject item) {
        if (!inventory.contains(item)) {
            inventory.add(item);
            System.out.println(this.name + " added " + item.name + " to inventory.");
        } else {
            System.out.println(this.name + " already has " + item.name + " in inventory.");
        }
    }


    // Improved equipWeapon method with clearer power update logic
    public void equipWeapon(Weapon weapon) {
        if (this.inventory.contains(weapon)) {
            if (this.equippedWeapon != null) {
                // If there's an equipped weapon, remove its strength bonus from power
                this.power -= this.equippedWeapon.getStrengthBonus();
            }

            // Update equipped weapon and add its strength bonus to power
            this.equippedWeapon = weapon;
            this.power += weapon.getStrengthBonus();

            System.out.println(weapon.getName() + " has been equipped. New power: " + this.power);
        } else {
            System.out.println("Cannot equip " + weapon.getName() + "; it is not in the inventory.");
        }
    }


    // Getters and Setters
    public String getName() { return name; }

    public void setName(String name) { this.name = name; } // Fixed setter
    public int getHealth() { return health; }

    public void setHealth(int health) { this.health = health; }
    public void setMoney(int money) { this.money = money; }
    public String getEquippedWeaponName() {
        return equippedWeapon != null ? equippedWeapon.getName() : "None";
    }
public void setPower(int power) {this.power = power;
}
    public int getPower() {
        return power;
    }
    public void addPower(int amount) {
        this.power+=amount;
    }
    public void addMoney(int amount) {
        this.money += amount;
    }
    public void heal(int amount) {
        this.health += amount;
        System.out.println(amount + " health added to " + this.name + ". New health: " + this.health);
    }

    public int getMoney() { return this.money; }
    public List<SuperObject> getInventory() { return inventory; }

    public int getStepsRemaining() {
        return stepsRemaining;
    }
    public void setStepsRemaining(int stepsRemaining) {
        this.stepsRemaining = stepsRemaining;
    }
    public void resetPosition() {
        this.worldX = startingX;
        this.worldY = startingY;
        // Reset other movement-related attributes as needed
    }

    public int getWorldX() { return worldX; }
    public int getWorldY() { return worldY; }

    // Implement methods to draw the player and handle other logic as needed.
}