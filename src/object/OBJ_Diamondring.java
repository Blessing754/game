package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import PlayerEntity.PlayerEntity; // Import PlayerEntity to interact with the player

public class OBJ_Diamondring extends SuperObject {

    // Instance variable for storing money value
    private int money;

    public OBJ_Diamondring() {
        this.name = "Diamond Ring";

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/Diamondring.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize money with a random value between 1 and 100
        money = (int) (Math.random() * 100) + 1;
    }

    // Method to add the diamond ring's money value to the player's money

    public void addMoneyToPlayer(PlayerEntity player) {
        if (player != null) {
            player.addMoney(money);
        }
    }

    // Getter method for the money value
    public int getMoney() {
        return money;
    }

    // Setter method for the money value (Not typically needed for this case, but included for completeness)
    public void setMoney(int money) {
        if (money >= 0) {
            this.money = money;
        } else {
            throw new IllegalArgumentException("Money value cannot be negative.");
        }
    }
}
