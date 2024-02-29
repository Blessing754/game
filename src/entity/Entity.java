package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    // World coordinates of the entity
    public int worldX, worldY;

    // Movement speed of the entity
    public int speed;

    // BufferedImages to hold different animation frames for each direction
    // There are nine frames per direction for detailed animation
    public BufferedImage up1, up2, up3, up4, up5, up6, up7, up8, up9;
    public BufferedImage down1, down2, down3, down4, down5, down6, down7, down8, down9;
    public BufferedImage left1, left2, left3, left4, left5, left6, left7, left8, left9;
    public BufferedImage right1, right2, right3, right4, right5, right6, right7, right8, right9;

    // The current direction the entity is facing ('up', 'down', 'left', 'right')
    public String direction;

    // Variables to control animation frames
    public int spriteNum = 1;      // Current frame number in the animation sequence
    public int spriteCounter = 1;  // Counter used to determine when to change to the next animation frame

    public Rectangle solidArea;
    public boolean collision = false;
    public boolean collisionOn;
}
