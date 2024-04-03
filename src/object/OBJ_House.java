package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;
import PlayerEntity.Player;
import PlayerEntity.Player2;

public class OBJ_House extends SuperObject {
    public boolean hasLostItems = true; // Indicates if the house contains lost items (money).
    public int moneyAvailable; // The amount of money available in this house.

    public OBJ_House() {
        this.name = "House";
        // Attempt to load the house image
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/market2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Randomly assign an amount of money to the house
        Random rand = new Random();
        this.moneyAvailable = rand.nextInt(100) + 1; // Assigns a random amount between 1 and 100
    }

    // Method to change the house's appearance to grass
    public void changeAppearanceToGrass() {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));
            System.out.println("House appearance changed to grass."); // Debugging
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to handle item collection logic
    public int collectItems() {
        if (hasLostItems) {
            hasLostItems = false; // Mark items as collected
            changeAppearanceToGrass(); // Change appearance to grass
            return moneyAvailable; // Return the amount of money collected
        }
        return 0; // Return 0 if no items were collected
    }

    // Method to handle interaction with either player
    public void interact(Player player) {
        int collectedAmount = collectItems();
        if (collectedAmount > 0) {
            player.setMoney(player.getMoney() + collectedAmount);
            System.out.println(player.getName() + " has collected " + collectedAmount + " from the house.");
        }
    }

    // Overloaded method for Player2
    public void interact(Player2 player2) {
        int collectedAmount = collectItems(); // Use the collectItems method to handle logic
        if (collectedAmount > 0) {
            player2.setMoney(player2.getMoney() + collectedAmount);
            System.out.println(player2.getName() + " has collected " + collectedAmount + " from the house.");
        }
    }
}
