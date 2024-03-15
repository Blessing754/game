package PlayerEntity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends PlayerEntity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        x = 768;
        y = 448;
        speed = 2;
        direction = null;
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/back0.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/back1.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/back2.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/back3.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/fron0.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/fron1.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/fron2.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/fron3.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left0.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/left3.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right0.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/right3.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!moving) {
            boolean validKeyPress = false;

            int tempTargetX = x;
            int tempTargetY = y;

            if (keyH.upPressed) {
                tempTargetY = y - gp.tileSize;
                direction = "up";
                validKeyPress = true;
            } else if (keyH.downPressed) {
                tempTargetY = y + gp.tileSize;
                direction = "down";
                validKeyPress = true;
            } else if (keyH.leftPressed) {
                tempTargetX = x - gp.tileSize;
                direction = "left";
                validKeyPress = true;
            } else if (keyH.rightPressed) {
                tempTargetX = x + gp.tileSize;
                direction = "right";
                validKeyPress = true;
            }

            if (validKeyPress) {
                targetX = tempTargetX;
                targetY = tempTargetY;
                moving = true;
            }

            // Disable continuous movement by resetting the key flags
            keyH.upPressed = false;
            keyH.downPressed = false;
            keyH.leftPressed = false;
            keyH.rightPressed = false;
        }

        // Perform movement and animation frame by frame
        if (moving) {
            // Check if the character is close to the target position
            if (Math.abs(x - targetX) > speed || Math.abs(y - targetY) > speed) {
                if (x < targetX) {
                    x += speed;
                }
                if (x > targetX) {
                    x -= speed;
                }
                if (y < targetY) {
                    y += speed;
                }
                if (y > targetY) {
                    y -= speed;
                }

                // Update the sprite animation while moving
                spriteCounter++;
                if (spriteCounter > 10) {
                    spriteCounter = 0;
                    spriteNum = (spriteNum + 1) % 4;
                    if (spriteNum == 0) spriteNum = 1;
                }
            } else {
                // Snap to the target position and stop moving
                x = targetX;
                y = targetY;
                moving = false;
                spriteNum = 1; // Reset sprite animation
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (direction != null) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    else if (spriteNum == 2) {
                        image = up2;
                    }
                    else if (spriteNum == 3) {
                        image = up3;
                    }
                    else if (spriteNum == 4) {
                        image = up4;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    else if (spriteNum == 2) {
                        image = down2;
                    }
                    else if (spriteNum == 3) {
                        image = down3;
                    }
                    else if (spriteNum == 4) {
                        image = down4;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    else if (spriteNum == 2) {
                        image = left2;
                    }
                    else if (spriteNum == 3) {
                        image = left3;
                    }
                    else if (spriteNum == 4) {
                        image = left4;
                    }

                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    else if (spriteNum == 2) {
                        image = right2;
                    }
                    else if (spriteNum == 3) {
                        image = right3;
                    }
                    else if (spriteNum == 4) {
                        image = right4;
                    }
                    break;
            }
        }
        if (image == null) {
            image = down1_p2; // Default image
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}