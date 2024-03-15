package PlayerEntity;

import java.awt.image.BufferedImage;

public class PlayerEntity {

    public int x,y;
    public int speed;

    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4,
            right1, right2, right3, right4;
    public BufferedImage
            up1_p2, up2_p2, up3_p2, up4_p2, up5_p2, up6_p2, up7_p2, up8_p2, up9_p2, // Up
            down1_p2, down2_p2, down3_p2, down4_p2, down5_p2, down6_p2, down7_p2, down8_p2, down9_p2, // Down
            left1_p2, left2_p2, left3_p2, left4_p2, left5_p2, left6_p2, left7_p2, left8_p2, left9_p2, // Left
            right1_p2, right2_p2, right3_p2, right4_p2, right5_p2, right6_p2, right7_p2, right8_p2, right9_p2; // Right
    public String direction;
    public int targetX, targetY;
    public boolean moving = false;
    public int spriteCounter = 0;
    public int spriteNum = 1;
}
