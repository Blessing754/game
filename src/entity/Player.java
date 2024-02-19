package entity;


import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
    }

    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;

    }
    public void update(){
        // Check if the 'up' key is pressed
        if (keyH.upPressed == true) { // If so, decrease the player's Y-coordinate by the player's speed

            // This moves the player up on the screen
            y -= speed;

        }
        // Check if the 'down' key is pressed
        else if (keyH.downPressed == true) { // If so, increase the player's Y-coordinate by the player's speed

            // This moves the player down on the screen
            y += speed;

        }
        // Check if the 'left' key is pressed
        else if (keyH.leftPressed == true) { // If so, decrease the player's X-coordinate by the player's speed

            // This moves the player left on the screen
            x -= speed;

        }
        // Check if the 'right' key is pressed
        else if (keyH.rightPressed == true) {// If so, increase the player's X-coordinate by the player's speed

            // This moves the player right on the screen
            x += speed;

        }
    }
    public void draw(Graphics2D g2){

        // Set the drawing color to white
        g2.setColor(Color.white);

        // Draw a filled rectangle representing the player at the current (playerX, playerY) position, The size of the rectangle is determined by 'tileSize'
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

    }
}
