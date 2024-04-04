package object;

import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public boolean collision = false;
    public String name;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
    public int solidAreaDX = 0;
    public int solidAreaDY = 0;
    public int power;
    public int money;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public int getSolidAreaDX() {
        return solidAreaDX;
    }

    public void setSolidAreaDX(int solidAreaDX) {
        this.solidAreaDX = solidAreaDX;
    }

    public int getSolidAreaDY() {
        return solidAreaDY;
    }

    public void setSolidAreaDY(int solidAreaDY) {
        this.solidAreaDY = solidAreaDY;
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX;
        int screenY = worldY;
        // Draw the object at its position
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
