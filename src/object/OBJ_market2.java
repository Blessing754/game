package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_market2 extends  SuperObject{
    public OBJ_market2() {
        name = "market2";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/market2.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
