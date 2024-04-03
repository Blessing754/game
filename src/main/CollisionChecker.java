package main;

import PlayerEntity.PlayerEntity;
import object.OBJ_House;
import object.SuperObject;
import object.Weapon;
import object.OBJ_JewelEncrustedSword;
import object.OBJ_paladinShield;
import object.OBJ_woodenbow;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;

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

    // In the CollisionChecker class
    public int checkObject(PlayerEntity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                // Update solid area positions for collision checking
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                // Check for intersection between entity and object solid areas
                if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                    if (gp.obj[i].collision && player) { // Check if collision should be handled
                        // Handle collision based on object type
                        if (gp.obj[i] instanceof OBJ_House) {
                            handleHouseCollision(entity, i);
                        } else if (gp.obj[i] instanceof OBJ_JewelEncrustedSword || gp.obj[i] instanceof OBJ_paladinShield || gp.obj[i] instanceof OBJ_woodenbow) {
                            handleWeaponCollision(entity, gp.obj[i], i);
                        }
                        index = i; // Mark index of the collided object
                    }
                }

                // Reset solid area positions after checking
                resetSolidAreas(entity, gp.obj[i]);
            }
        }
        return index;
    }


    // Reset solid areas after collision checking
    private void resetSolidAreas(PlayerEntity entity, SuperObject obj) {
        entity.solidArea.x = entity.solidAreaDX;
        entity.solidArea.y = entity.solidAreaDY;
        obj.solidArea.x = obj.solidAreaDX;
        obj.solidArea.y = obj.solidAreaDY;
    }

    private void handleHouseCollision(PlayerEntity player, int houseIndex) {
        if (houseIndex < 0 || houseIndex >= gp.obj.length) {
            return; // Ensure index is in bounds
        }

        SuperObject obj = gp.obj[houseIndex];
        if (obj instanceof OBJ_House) {
            OBJ_House house = (OBJ_House) obj;
            if (house.hasLostItems) {
                player.addMoney(house.moneyAvailable); // Correctly calling addMoney on the player instance
                house.hasLostItems = false; // Mark as looted
                house.changeAppearanceToGrass(); // Visual feedback
                gp.repaint(); // Refresh display
            }
        }
    }
    private void handleWeaponCollision(PlayerEntity player, SuperObject weapon, int weaponIndex) {
        if (weapon instanceof Weapon) {
            Weapon actualWeapon = (Weapon) weapon;
            // Check if the purchase was successful to ensure the weapon is only removed if it was actually bought
            boolean purchaseSuccessful = player.purchaseWeapon(actualWeapon);
            if (purchaseSuccessful) {
                player.equipWeapon(actualWeapon);
                gp.obj[weaponIndex] = null;
                int tileX = weapon.worldX / gp.tileSize;
                int tileY = weapon.worldY / gp.tileSize;
                gp.tileM.setTileToGrass(tileX, tileY);
            }
            gp.repaint(); // Refresh display to reflect any changes
        }
    }
}