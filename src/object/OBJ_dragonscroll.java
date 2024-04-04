package object;
import object.SuperObject;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_dragonscroll extends SuperObject {

    public OBJ_dragonscroll() {
        this.name = "Dragon's Scroll";

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/Dragonscroll.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Scroll-specific effects, perhaps unlocking powerful spells or abilities
    }
}
