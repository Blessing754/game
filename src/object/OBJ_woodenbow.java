package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_woodenbow extends Weapon {

    public OBJ_woodenbow() {
        // Call to the superclass constructor must be the first statement in the subclass constructor
        super("Wooden Bow", 7, 200, true); // Initializes name, strengthBonus, price, and isInteractable

        // Load the image resource for the wooden bow
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/bow-png.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the image for the wooden bow.");
        }
    }

    @Override
    public void setHasBeenInteracted(boolean interacted) {
        // Call the superclass method to maintain the base behavior
        super.setHasBeenInteracted(interacted);
        // Add any additional logic for OBJ_woodenbow when interaction state changes
    }
}
