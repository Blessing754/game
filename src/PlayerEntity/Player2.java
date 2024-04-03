package PlayerEntity;

import main.GamePanel;
import main.KeyHandler;
import object.Weapon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Player2 extends PlayerEntity {

    GamePanel gp;
    KeyHandler keyH;
    private int stepsRemaining;
    private Dice dice;
    int hasKeyP2 = 0;
    private List<Weapon> inventory = new ArrayList<>();
    private Weapon equippedWeapon;
    public Player2(GamePanel gp, KeyHandler keyH, int health, int money, int power) {
        //super(200, 600, 200);
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidArea.width=48;
        solidArea.height=48;

        dice = new Dice();
        setDefaultValuesP2();
        getPlayerImageP2();
        getPlayerImageP2();
        this.name = "Player 2";
        this.setMoney(600);
        this.setHealth(200);
        this.setPower(200);
    }
    public void setMoney(int money) {
        this.money = money;
    }


    @Override
    public int getMoney() {
        return this.money;
    }

    public void setDefaultValuesP2() {
        this.startingX = 64;
        this.startingY = 384;
        worldX =64;
        worldY =384;
        speed = 2;
        direction = null;
    }
    public void addMoney(int amount) {
        this.money = money+ amount;
        System.out.println("Player Money Updated: " + this.money);
    }
    @Override
    public boolean purchaseWeapon(Weapon weapon) {
        // Check if the weapon is already in the inventory
        if (inventory.contains(weapon)) {
            System.out.println(this.name + " already owns " + weapon.getName() + ".");
            return false; // Indicate the purchase did not happen
        }

        // Check if the player has enough money to purchase the weapon
        if (this.money >= weapon.getPrice()) {
            this.money -= weapon.getPrice(); // Deduct the weapon's cost from the player's money
            inventory.add(weapon); // Add the weapon to the player's inventory
            System.out.println(this.name + " purchased " + weapon.getName() + ".");
            return true; // Indicate a successful purchase
        } else {
            System.out.println(this.name + " does not have enough money to purchase " + weapon.getName() + ".");
            return false; // Indicate the purchase did not happen
        }
    }


    public int calculateTotalStrength() {
        int totalStrength = this.power; // Assuming getPower() returns base strength
        if (equippedWeapon != null) {
            totalStrength += equippedWeapon.getStrengthBonus();
        }
        return totalStrength;
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
        if (gp.turnManager.isPlayerTurn(this) && !moving) {
            // Player 2 must press 'R' to roll the dice if steps are 0.
            if (stepsRemaining == 0 && keyH.rPressed) {
                movingTurn = true;
                stepsRemaining = dice.roll();
                System.out.println("Player 2 rolled: " + stepsRemaining + " steps.");
                keyH.rPressed = false; // Reset the dice roll flag to prevent multiple rolls in one turn.
            }

            // Proceed with the movement if the dice has been rolled.
            if (stepsRemaining > 0 && (keyH.wPressed || keyH.sPressed || keyH.aPressed || keyH.dPressed)) {
                setDirectionAndTargetPositionP2();

                collisionOn = gp.cChecker.checkTileCollision(this, targetX, targetY);
                if (!collisionOn) {
                    moving = true;
                    stepsRemaining--; // Decrement steps after each move
                }

                // Reset key press states
                if (keyH.wPressed) keyH.wPressed = false;
                if (keyH.sPressed) keyH.sPressed = false;
                if (keyH.aPressed) keyH.aPressed = false;
                if (keyH.dPressed) keyH.dPressed = false;

                // End the player's turn if they have no more steps to move
                if (stepsRemaining == 0) {
                    gp.turnManager.endTurn();
                    movingTurn = false;
                }
            }
            //System.out.println(worldX + " and " + worldY);
        }

        // If in motion, continue moving towards the target
        if (moving) {
            moveTowardsTargetP2();
            updateAnimationP2();
        }

        // Check for object interactions
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);
        //System.out.println("Player 2: " + worldX + " and " + worldY);
    }


    public void pickUpObject(int i) {
        if (i != 999) {
            String ObjectName = gp.obj[i].name;

            switch(ObjectName){
                case "Key":
                    hasKeyP2++;
                    gp.obj[i] = null;
                    System.out.println("key for P2:"+hasKeyP2);
                    break;

                case "Castle" :
                    if (hasKeyP2 >= 3){
                        gp.obj[i] = null;
                        hasKeyP2--;
                    }
                    System.out.println("key for P2:"+hasKeyP2);
                    break;
            }
        }
    }
    private void setDirectionAndTargetPositionP2() {
        int tileSize = gp.tileSize;

        if (keyH.wPressed) {
            direction = "up";
            targetX = worldX;
            targetY = worldY - tileSize;
        } else if (keyH.sPressed) {
            direction = "down";
            targetX = worldX;
            targetY = worldY + tileSize;
        } else if (keyH.aPressed) {
            direction = "left";
            targetX = worldX - tileSize;
            targetY = worldY;
        } else if (keyH.dPressed) {
            direction = "right";
            targetX = worldX + tileSize;
            targetY = worldY;
        }
    }

    private void moveTowardsTargetP2() {
        if (Math.abs(targetX - worldX) > speed) {
            worldX += speed * (targetX > worldX ? 1 : -1);
        } else {
            worldX = targetX;
        }

        if (Math.abs(targetY - worldY) > speed) {
            worldY += speed * (targetY > worldY ? 1 : -1);
        } else {
            worldY = targetY;
        }

        // Stop moving when the target is reached
        if (worldX == targetX && worldY == targetY) {
            moving = false;
        }
    }
    private void updateAnimationP2() {
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
