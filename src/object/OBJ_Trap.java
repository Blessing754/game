package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Trap extends SuperObject {
    public int moneyPenalty; // Penalty amount
    public int powerPenalty; // Penalty amount

    public OBJ_Trap() {
        name = "Trap";
        moneyPenalty = 50; // Example penalty value
        powerPenalty = 5;  // Example penalty value
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Trap.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Additional methods (if needed)
}
