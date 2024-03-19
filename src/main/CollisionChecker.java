package main;

import PlayerEntity.PlayerEntity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp=gp;

    }

    public boolean checkTile(PlayerEntity entity, String direction) {
        int nextX = entity.worldX;
        int nextY = entity.worldY;

        if (direction.equals("up")) {
            nextY = entity.worldY - entity.speed;
        } else if (direction.equals("down")) {
            nextY = entity.worldY + entity.speed;
        } else if (direction.equals("left")) {
            nextX = entity.worldX - entity.speed;
        } else if (direction.equals("right")) {
            nextX = entity.worldX + entity.speed;
        }

        // Convert nextX and nextY to tile positions
        int tileX = nextX / gp.tileSize;
        int tileY = nextY / gp.tileSize;

        // Check if the tile at the new position is solid
        if (gp.tileM.tile[gp.tileM.mapTileNum[tileX][tileY]].collision) {
            return true;
        }

        return false;
    }
    public boolean checkTileCollision(PlayerEntity entity, int targetX, int targetY) {
        // Calculate the future position's tile coordinates
        int tileX = targetX / gp.tileSize;
        int tileY = targetY / gp.tileSize;

        // Check if the tile at the future position is solid
        if (gp.tileM.tile[gp.tileM.mapTileNum[tileX][tileY]].collision) {
            return true; // Collision detected
        }

        return false; // No collision
    }

}
