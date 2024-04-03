package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_paladinShield extends Weapon {

    public OBJ_paladinShield() {
        super("Paladin Shield", 5, 50, true); // Updated to include the isInteractable parameter

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/shield_blue.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void interact() {
        setHasBeenInteracted(true);
    }

    @Override
    public void setHasBeenInteracted(boolean interacted) {
        super.setHasBeenInteracted(interacted);
    }

}
