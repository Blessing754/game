package object;
import PlayerEntity.PlayerEntity;
import object.SuperObject;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CrystalGoblet extends SuperObject {
    private int healthBonus;

    public OBJ_CrystalGoblet() {
        this.name = "Crystal Goblets";

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/CrystalGoblet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.healthBonus = 60; // Health bonus provided by Golden Goblet
    }

    // Method to give health bonus to a player
    public void giveHealthToPlayer(PlayerEntity player) {
        player.heal(healthBonus);
    }
}

