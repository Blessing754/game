package PlayerEntity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends PlayerEntity {

    public int screenX;
    public int screenY;
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidArea.width=48;
        solidArea.height=48;

        solidAreaDX= solidArea.x;
        solidAreaDY= solidArea.y;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = 768;
        worldY = 384;
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
        // Update the player's position and animation based on keyboard input
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            // Update player position and direction based on the pressed key
            if (keyH.upPressed) {
                direction = "up";
                // Move up
            } else if (keyH.downPressed) {
                direction = "down";
                // Move down
            } else if (keyH.leftPressed) {
                direction = "left";
                // Move left
            } else if (keyH.rightPressed) {
                direction = "right";
                // Move right
            }

            //check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // check object collision
            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            // player collision

            if (collisionOn==false) {

                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }


            }

            // Update sprite animation
            spriteCounter++;
            if (spriteCounter > 7) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 1;
                }
                spriteCounter = 0;

            }
        }

    }

    public void pickUpObject(int i){
        if(i != 999){
            gp.obj[i] =null;
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
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}