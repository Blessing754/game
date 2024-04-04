package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;
import PlayerEntity.PlayerEntity;

public class OBJ_Chest extends SuperObject {
    private SuperObject treasure; // Holds the generated treasure
    private boolean treasureGenerated = false; // Flag to check if treasure has been generated

    public OBJ_Chest() {
        name = "chest";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
        generateRandomTreasure();
    }

    // Method to generate a random treasure, only once
    public SuperObject generateRandomTreasure() {
        String[] treasures = {"OBJ_CrystalGoblet", "OBJ_Diamondring", "OBJ_GoldenGoblet", "OBJ_dragonscroll", "OBJ_JewelEncrustedSword", "OBJ_paladinShield", "OBJ_woodenbow", "OBJ_Key"};
        Random random = new Random();
        int index = random.nextInt(treasures.length);

        switch (index) {
                case 0:
                    treasure = new OBJ_CrystalGoblet();
                    break;
                case 1:
                    treasure = new OBJ_Diamondring();
                    break;
                case 2:
                    treasure = new OBJ_GoldenGoblet();
                    break;
                case 3:
                    treasure = new OBJ_dragonscroll();
                    break;
                case 4:
                    treasure = new OBJ_JewelEncrustedSword();
                    break;
                case 5:
                    treasure = new OBJ_paladinShield();
                    break;
                case 6:
                    treasure = new OBJ_woodenbow();
                    break;
                case 7:
                    treasure = new OBJ_Key();
                    break;

        }

        return treasure; // Return the stored treasure
    }
    public SuperObject getTreasure() {
        return treasure;
    }
    public void interact(PlayerEntity player) {
        if (this.collision) {

            this.collision = false; // Mark the chest as interacted
        }
    }
}
