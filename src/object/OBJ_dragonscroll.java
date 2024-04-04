package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import PlayerEntity.PlayerEntity; // Import PlayerEntity to interact with the player

public class OBJ_dragonscroll extends SuperObject {

    // Instance variable for storing power value
    private int power;

    public OBJ_dragonscroll() {
        this.name = "Dragon's Scroll";

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/Dragonscroll.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Initialize power with a random value between 1 and 100
        power = (int) (Math.random() * 100) + 1;
    }

    // Method to add the scroll's power value to the player's power
    public void addPowerToPlayer(PlayerEntity player) {
        if (player != null) {
            player.addPower(power);
        }
    }

    // Getter method for the power value
    public int getPower() {
        return power;
    }

    // Setter method for the power value
    public void setPower(int power) {
        this.power = power;
    }
}
