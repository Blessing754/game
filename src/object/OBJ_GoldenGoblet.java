package object;
import object.SuperObject;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_GoldenGoblet extends SuperObject {

    public OBJ_GoldenGoblet() {
        this.name = "Golden Goblet";

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/GoldeeenGoblet.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Goblet-specific properties, perhaps related to health or magic
    }
}
