package object;

import PlayerEntity.PlayerEntity;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Trap extends SuperObject {
    public int moneyPenalty; // Penalty amount
    public int powerPenalty; // Penalty amount
    public boolean hasTriggered = false;

    public OBJ_Trap() {
        this.name = "Trap";
        this.moneyPenalty = 50; // Example penalty value
        this.powerPenalty = 5;  // Example penalty value
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/Trap.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.collision = true; // Assuming the trap should trigger a collision
    }

    public void triggerEffect(PlayerEntity player) {
        // Directly reduce the player's money and power
        player.money -= this.moneyPenalty;
        player.power -= this.powerPenalty;

        // Ensure money and power do not go below zero
        if (player.money < 0) {
            player.money = 0; // Prevent money from going negative
        }
        if (player.power < 0) {
            player.power = 0; // Prevent power from going negative
        }
        hasTriggered = true;
    }


    // Getters and setters for trap attributes can also be added if needed
}
