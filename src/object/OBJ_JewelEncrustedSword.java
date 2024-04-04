package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_JewelEncrustedSword extends Weapon {

    public OBJ_JewelEncrustedSword() {
        // Assuming the sword is interactable by default
        // If different, adjust the true value accordingly
        super("Jewel-Encrusted Sword", 50, 300, true); // Include isInteractable in the constructor

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/sword_normal.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void interact() {
        // Interaction logic specific to the Jewel-Encrusted Sword
        setHasBeenInteracted(true);
        // Additional interaction behaviors can be added here
    }

    // Override setHasBeenInteracted if there's additional behavior for this sword when it's interacted with
    @Override
    public void setHasBeenInteracted(boolean interacted) {
        super.setHasBeenInteracted(interacted);
          }
}
