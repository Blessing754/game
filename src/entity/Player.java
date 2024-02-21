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

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
        direction="down";

    }

    public void getPlayerImage(){
        try{
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
            e.printStackTrace();
        }
    }

    public void update(){

        if (keyH.upPressed==true || keyH.downPressed==true || keyH.leftPressed==true || keyH.rightPressed==true){

            if (keyH.upPressed == true) { // If so, decrease the player's Y-coordinate by the player's speed
                direction ="up";
                // This moves the player up on the screen
                y -= speed;

            }
            // Check if the 'down' key is pressed
            else if (keyH.downPressed == true) { // If so, increase the player's Y-coordinate by the player's speed
                direction ="down";
                // This moves the player down on the screen
                y += speed;

            }
            // Check if the 'left' key is pressed
            else if (keyH.leftPressed == true) { // If so, decrease the player's X-coordinate by the player's speed
                direction ="left";
                // This moves the player left on the screen
                x -= speed;

            }
            // Check if the 'right' key is pressed
            else if (keyH.rightPressed == true) {// If so, increase the player's X-coordinate by the player's speed
                direction ="right";
                // This moves the player right on the screen
                x += speed;

            }

            spriteCounter++;
            if (spriteCounter>5){
                if (spriteNum ==1){
                    spriteNum=2;
                }
                else if (spriteNum ==2){
                    spriteNum=3;
                }
                else if (spriteNum ==3){
                    spriteNum=4;
                }
                else if (spriteNum ==4){
                    spriteNum=5;
                }
                else if (spriteNum ==5){
                    spriteNum=6;
                }
                else if (spriteNum ==6){
                    spriteNum=7;
                }
                else if (spriteNum ==7){
                    spriteNum=8;
                }
                else if (spriteNum ==8){
                    spriteNum=9;
                }
                else if (spriteNum ==9){
                    spriteNum=2;
                }
                spriteCounter=0;

            }

        }
        // Check if the 'up' key is pressed


    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;

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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize,null);


    }
}
