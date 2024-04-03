package object;

import object.SuperObject;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Diamondring extends SuperObject {

    public OBJ_Diamondring() {
        this.name = "Diamond Ring";

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/Diamondring.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Additional properties like effects on player
    }
}
