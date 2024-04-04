package object;
import object.SuperObject;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CrystalGoblet extends SuperObject {

    public OBJ_CrystalGoblet() {
        this.name = "Crystal Goblets";

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/CrystalGoblet.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Maybe used for storing or mixing potions
    }
}

