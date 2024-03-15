package PlayerEntity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player2 extends PlayerEntity {

    GamePanel gp;
    KeyHandler keyH;

    public Player2(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValuesP2();
        getPlayerImageP2();
        getPlayerImageP2();
    }

    public void setDefaultValuesP2() {
        x =64;
        y =448;
        speed = 2;
        direction = null;
    }

    public void getPlayerImageP2() {
        try {
            up1_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/up0.png"));
            up2_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/up1.png"));
            up3_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/up2.png"));
            up4_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/up3.png"));

            down1_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/down0.png"));
            down2_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/down1.png"));
            down3_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/down2.png"));
            down4_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/down3.png"));

            left1_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/left0.png"));
            left2_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/left1.png"));
            left3_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/left2.png"));
            left4_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/left3.png"));

            right1_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/right0.png"));
            right2_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/right1.png"));
            right3_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/right2.png"));
            right4_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/right3.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!moving) {
            boolean validKeyPress = false;

            int tempTargetX = x;
            int tempTargetY = y;

            if (keyH.wPressed) {
                tempTargetY = y - gp.tileSize;
                direction = "up";
                validKeyPress = true;
            } else if (keyH.sPressed) {
                tempTargetY = y + gp.tileSize;
                direction = "down";
                validKeyPress = true;
            } else if (keyH.aPressed) {
                tempTargetX = x - gp.tileSize;
                direction = "left";
                validKeyPress = true;
            } else if (keyH.dPressed) {
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
            keyH.wPressed = false;
            keyH.sPressed = false;
            keyH.aPressed = false;
            keyH.dPressed = false;
        }


        // Movement logic - if the player is currently moving, interpolate the position
        if (moving) {
            if (Math.abs(x - targetX) > speed || Math.abs(y - targetY) > speed) {
                if (x < targetX) {
                    x += speed;
                } else if (x > targetX) {
                    x -= speed;
                }
                if (y < targetY) {
                    y += speed;
                } else if (y > targetY) {
                    y -= speed;
                }

                // Update sprite animation
                spriteCounter++;
                if (spriteCounter > 10) {
                    spriteCounter = 0;
                    spriteNum = (spriteNum + 1) % 4;
                    if (spriteNum == 0) spriteNum = 1;
                }
            } else {
                // Snap to the target position
                x = targetX;
                y = targetY;
                moving = false; // Stop moving
                spriteNum = 1; // Reset sprite animation to the starting position
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (direction != null) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1_p2;
                    } else if (spriteNum == 2) {
                        image = up2_p2;
                    } else if (spriteNum == 3) {
                        image = up3_p2;
                    } else if (spriteNum == 4) {
                        image = up4_p2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1_p2;
                    } else if (spriteNum == 2) {
                        image = down2_p2;
                    } else if (spriteNum == 3) {
                        image = down3_p2;
                    } else if (spriteNum == 4) {
                        image = down4_p2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1_p2;
                    } else if (spriteNum == 2) {
                        image = left2_p2;
                    } else if (spriteNum == 3) {
                        image = left3_p2;
                    } else if (spriteNum == 4) {
                        image = left4_p2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1_p2;
                    } else if (spriteNum == 2) {
                        image = right2_p2;
                    } else if (spriteNum == 3) {
                        image = right3_p2;
                    } else if (spriteNum == 4) {
                        image = right4_p2;
                    }
                    break;
            }
        }
        if (image == null) {
            image = down1; // Default image
        }

        // Draw Player 2 image at its current position
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}