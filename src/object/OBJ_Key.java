package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import PlayerEntity.PlayerEntity; // Import PlayerEntity to interact with the player

public class OBJ_Key extends SuperObject {

    // Instance variable for storing money value
    private int money;

    public OBJ_Key() {
        this.name = "Key";

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize money with a random value between 1 and 100
        money = (int) (Math.random() * 100) + 1;
    }

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

// Sette
