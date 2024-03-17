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

        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidArea.width=48;
        solidArea.height=48;

        setDefaultValuesP2();
        getPlayerImageP2();
        getPlayerImageP2();
    }

    public void setDefaultValuesP2() {
        worldX =64;
        worldY =384;
        speed = 2;
        direction = null;
    }

    public void getPlayerImageP2() {
        try {
            up1_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/up1.png"));
            up2_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/up2.png"));
            up3_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/up3.png"));
            up4_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/up4.png"));
            up5_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/up5.png"));
            up6_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/up6.png"));
            up7_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/up7.png"));
            up8_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/up8.png"));
            up9_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/up9.png"));

            down1_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/down1.png"));
            down2_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/down2.png"));
            down3_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/down3.png"));
            down4_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/down4.png"));
            down5_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/down5.png"));
            down6_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/down6.png"));
            down7_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/down7.png"));
            down8_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/down8.png"));
            down9_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/down9.png"));

            left1_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/right1.png"));
            left2_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/right2.png"));
            left3_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/right3.png"));
            left4_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/right4.png"));
            left5_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/right5.png"));
            left6_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/right6.png"));
            left7_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/right7.png"));
            left8_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/right8.png"));
            left9_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/right9.png"));

            right1_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/left1.png"));
            right2_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/left2.png"));
            right3_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/left3.png"));
            right4_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/left4.png"));
            right5_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/left5.png"));
            right6_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/left6.png"));
            right7_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/left7.png"));
            right8_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/left8.png"));
            right9_p2 = ImageIO.read(getClass().getResourceAsStream("/player2/left9.png"));




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        // Update the player's position and animation based on keyboard input
        if (keyH.wPressed || keyH.sPressed || keyH.dPressed || keyH.aPressed) {
            // Update player position and direction based on the pressed key
            if (keyH.wPressed) {
                direction = "up";
                // Move up
            } else if (keyH.sPressed) {
                direction = "down";
                // Move down
            } else if (keyH.aPressed) {
                direction = "left";
                // Move left
            } else if (keyH.dPressed) {
                direction = "right";
                // Move right
            }

            //check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

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
                    spriteNum = 5;
                } else if (spriteNum == 5) {
                    spriteNum = 6;
                } else if (spriteNum == 6) {
                    spriteNum = 7;
                } else if (spriteNum == 7) {
                    spriteNum = 8;
                } else if (spriteNum == 8) {
                    spriteNum = 9;
                } else if (spriteNum == 9) {
                    spriteNum = 2;
                }
                spriteCounter = 0;

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
                    }else if (spriteNum == 5) {
                        image = up5_p2;
                    }else if (spriteNum == 6) {
                        image = up6_p2;
                    }else if (spriteNum == 7) {
                        image = up7_p2;
                    }else if (spriteNum == 8) {
                        image = up8_p2;
                    }else if (spriteNum == 9) {
                        image = up9_p2;
                    }

                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down2_p2;
                    } else if (spriteNum == 2) {
                        image = down2_p2;
                    } else if (spriteNum == 3) {
                        image = down3_p2;
                    } else if (spriteNum == 4) {
                        image = down4_p2;
                    }else if (spriteNum == 5){
                        image = down5_p2;
                    } else if (spriteNum == 6) {
                        image = down6_p2;
                    }else if (spriteNum == 7) {
                        image = down7_p2;
                    }else if (spriteNum == 8) {
                        image = down8_p2;
                    }else if (spriteNum == 9) {
                        image = down9_p2;
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
                    }else if (spriteNum == 5) {
                        image = left5_p2;
                    } else if (spriteNum == 6) {
                        image = left6_p2;
                    }else if (spriteNum == 7) {
                        image = left7_p2;
                    }else if (spriteNum == 8) {
                        image = left8_p2;
                    }else if (spriteNum == 9) {
                        image = left9_p2;
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
                    }else if (spriteNum == 5) {
                        image = right5_p2;
                    } else if (spriteNum == 6) {
                        image = right6_p2;
                    }else if (spriteNum == 7) {
                        image = right7_p2;
                    }else if (spriteNum == 8) {
                        image = right8_p2;
                    }else if (spriteNum == 9) {
                        image = right9_p2;
                    }
                    break;
            }
        }
        if (image == null) {
            image = down1; // Default image
        }

        // Draw Player 2 image at its current position
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}
