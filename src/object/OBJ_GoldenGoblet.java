package object;
import PlayerEntity.PlayerEntity;
import object.SuperObject;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_GoldenGoblet extends SuperObject {
    private int healthBonus;

    public OBJ_GoldenGoblet() {
        this.name = "Golden Goblet";

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/GoldeeenGoblet.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Goblet-specific properties, perhaps related to health or magic


        this.healthBonus = 30; // Health bonus provided by Golden Goblet
    }

    // Method to give health bonus to a player
    public void giveHealthToPlayer(PlayerEntity player) {
        player.heal(healthBonus);
    }
}