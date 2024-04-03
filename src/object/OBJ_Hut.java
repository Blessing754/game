package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Hut extends  SuperObject {
    public OBJ_Hut() {
        name = "hut";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/hut.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
