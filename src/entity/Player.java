package entity;


import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp; // Reference to the GamePanel for accessing game properties
        this.keyH = keyH; // Reference to the KeyHandler for processing keyboard input

        // Calculate the screen position to center the player
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues(); // Initialize default player properties
        getPlayerImage(); // Load player images for animation
    }

    public void setDefaultValues() {
        // Set initial player properties, such as position and speed
        worldX = gp.tileSize * 23; // Initial world position X
        worldY = gp.tileSize * 21; // Initial world position Y
        speed = 4; // Movement speed of the player
        direction = "down"; // Initial direction the player is facing
    }

    public void getPlayerImage(){
        // Load player images from resources for different animations
        try{
            // The images are loaded for different directions (up, down, left, right)
            // Each direction has multiple frames for animation
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/up3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/up4.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/player/up5.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/player/up6.png"));
            up7 = ImageIO.read(getClass().getResourceAsStream("/player/up7.png"));
            up8 = ImageIO.read(getClass().getResourceAsStream("/player/up8.png"));
            up9 = ImageIO.read(getClass().getResourceAsStream("/player/up9.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/down3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/down4.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/player/down5.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/player/down6.png"));
            down7 = ImageIO.read(getClass().getResourceAsStream("/player/down7.png"));
            down8 = ImageIO.read(getClass().getResourceAsStream("/player/down8.png"));
            down9 = ImageIO.read(getClass().getResourceAsStream("/player/down9.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/right3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/right4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/player/right5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/player/right6.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/player/right7.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/player/right8.png"));
            left9 = ImageIO.read(getClass().getResourceAsStream("/player/right9.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/left3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/left4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/player/left5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/player/left6.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/player/left7.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/player/left8.png"));
            right9 = ImageIO.read(getClass().getResourceAsStream("/player/left9.png"));

        }catch(IOException e){
            e.printStackTrace();// Handle exceptions during image loading
        }
    }

    public void update() {
        // Update the player's position and animation based on keyboard input
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            // Update player position and direction based on the pressed key
            if (keyH.upPressed) {
                direction = "up";
                worldY -= speed; // Move up
            } else if (keyH.downPressed) {
                direction = "down";
                worldY += speed; // Move down
            } else if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed; // Move left
            } else if (keyH.rightPressed) {
                direction = "right";
                worldX += speed; // Move right
            }

            // Update sprite animation
            spriteCounter++;
            if (spriteCounter > 5) {
                // Cycle through animation frames
                spriteNum = (spriteNum % 9) + 1;
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
        // Draw the player on the screen

        BufferedImage image = null;
        // Select the correct image based on the player's direction and animation frame

        switch (direction){
            case "up":
                if (spriteNum==1) {
                    image = up1;
                }
                if (spriteNum==2) {
                    image = up2;
                }
                if (spriteNum==3) {
                    image = up3;
                }
                if (spriteNum==4) {
                    image = up4;
                }
                if (spriteNum==5) {
                    image = up5;
                }
                if (spriteNum==6) {
                    image = up6;
                }
                if (spriteNum==7) {
                    image = up7;
                }
                if (spriteNum==8) {
                    image = up8;
                }
                if (spriteNum==9) {
                    image = up9;
                }
                break;
            case "down":
                if (spriteNum==1) {
                    image = down1;
                }
                if (spriteNum==2) {
                    image = down2;
                }
                if (spriteNum==3) {
                    image = down3;
                }
                if (spriteNum==4) {
                    image = down4;
                }
                if (spriteNum==5) {
                    image = down5;
                }
                if (spriteNum==6) {
                    image = down6;
                }
                if (spriteNum==7) {
                    image = down7;
                }
                if (spriteNum==8) {
                    image = down8;
                }
                if (spriteNum==9) {
                    image = down9;
                }
                break;
            case "left":
                if (spriteNum==1) {
                    image = left1;
                }
                if (spriteNum==2) {
                    image = left2;
                }
                if (spriteNum==3) {
                    image = left3;
                }
                if (spriteNum==4) {
                    image = left4;
                }
                if (spriteNum==5) {
                    image = left5;
                }
                if (spriteNum==6) {
                    image = left6;
                }
                if (spriteNum==7) {
                    image = left7;
                }
                if (spriteNum==8) {
                    image = left8;
                }
                if (spriteNum==9) {
                    image = left9;
                }
                break;
            case "right":
                if (spriteNum==1) {
                    image = right1;
                }
                if (spriteNum==2) {
                    image = right2;
                }
                if (spriteNum==3) {
                    image = right3;
                }
                if (spriteNum==4) {
                    image = right4;
                }
                if (spriteNum==5) {
                    image = right5;
                }
                if (spriteNum==6) {
                    image = right6;
                }
                if (spriteNum==7) {
                    image = right7;
                }
                if (spriteNum==8) {
                    image = right8;
                }
                if (spriteNum==9) {
                    image = right9;
                }
                break;

        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null); // Draw the selected image on the screen


    }
}
