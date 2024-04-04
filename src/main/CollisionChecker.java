package main;

import PlayerEntity.PlayerEntity;
import object.*;

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

    public int checkObject(PlayerEntity entity, boolean isPlayer) {
        int index = 999; // Assuming 999 is a default value indicating no collision

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                // Update solid area positions for collision checking
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                // Check for intersection between entity and object solid areas
                if (entity.solidArea.intersects(gp.obj[i].solidArea) && gp.obj[i].collision) {
                    if (gp.obj[i] instanceof OBJ_Chest && isPlayer) {
                        interactWithChest(entity, (OBJ_Chest) gp.obj[i], i);
                    } else if (gp.obj[i] instanceof OBJ_House && isPlayer) {
                        handleHouseCollision(entity, i);
                    } else if (gp.obj[i] instanceof OBJ_Trap && isPlayer) {
                        handleTrapCollision(entity, i); // Call the dedicated method for handling trap collisions
                    }
                    index = i;
                }

                resetSolidAreas(entity, gp.obj[i]);
            }
        }
        return index;
    }

    // Reset solid areas after collision checking
    private void resetSolidAreas(PlayerEntity entity, SuperObject obj) {
        if (obj != null && obj.solidArea != null) {
            // Reset the solid areas back to original after the collision check
            entity.solidArea.x = entity.solidAreaDX;
            entity.solidArea.y = entity.solidAreaDY;
            obj.solidArea.x = obj.solidAreaDX;
            obj.solidArea.y = obj.solidAreaDY;
        }

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
                gp.repaint(); // Refresh display
            }
        }
    }
    private void handleTrapCollision(PlayerEntity player, int trapIndex) {
        if (trapIndex < 0 || trapIndex >= gp.obj.length) {
            return; // Ensure index is in bounds
        }

        SuperObject obj = gp.obj[trapIndex];
        if (obj instanceof OBJ_Trap) {
            OBJ_Trap trap = (OBJ_Trap) obj;
            if (!trap.hasTriggered) { // Check if the trap has already been triggered
                trap.triggerEffect(player); // Trigger the trap's effect
                System.out.println(player.getName() + " hit a trap! Money and power penalized.");
            }
        }
    }


    private void interactWithChest(PlayerEntity entity, OBJ_Chest chest, int index) {
        if (chest.collision) {
            SuperObject generatedObject = chest.generateRandomTreasure();

            if (generatedObject instanceof OBJ_paladinShield) {
                OBJ_paladinShield shield = (OBJ_paladinShield) generatedObject;
                entity.addToInventory(shield); // Add to inventory
                entity. equipWeapon(shield); // Equip the shield
                entity.setPower(entity.getPower() + shield.getStrengthBonus());
            } else if (generatedObject instanceof OBJ_woodenbow) {
                OBJ_woodenbow bow = (OBJ_woodenbow) generatedObject;
                entity.addToInventory(bow); // Add to inventory
                entity. equipWeapon(bow); // Equip the bow
                entity.setPower(entity.getPower() + bow.getStrengthBonus()); // Increase player's power
            } else if (generatedObject instanceof OBJ_JewelEncrustedSword) {
                OBJ_JewelEncrustedSword sword = (OBJ_JewelEncrustedSword) generatedObject;
                entity.addToInventory(sword); // Add to inventory
                entity.equipWeapon(sword); // Equip the sword
                entity.setPower(entity.getPower() + sword.getStrengthBonus());// Increase player's power
            } else if (generatedObject instanceof OBJ_GoldenGoblet) {
                OBJ_GoldenGoblet goblet = (OBJ_GoldenGoblet) generatedObject;
                goblet.giveHealthToPlayer(entity);
            } else if (generatedObject instanceof OBJ_CrystalGoblet) {
                OBJ_CrystalGoblet goblet = (OBJ_CrystalGoblet) generatedObject;
                goblet.giveHealthToPlayer(entity);
            } else if (generatedObject instanceof OBJ_Key) {
                OBJ_Key key = (OBJ_Key) generatedObject;
                key.addMoneyToPlayer(entity);
            } else if (generatedObject instanceof OBJ_Diamondring) {
                OBJ_Diamondring diamondRing = (OBJ_Diamondring) generatedObject;
                diamondRing.addMoneyToPlayer(entity);
            } else if (generatedObject instanceof OBJ_dragonscroll) {
                OBJ_dragonscroll dragonScroll = (OBJ_dragonscroll) generatedObject;
                dragonScroll.addPowerToPlayer(entity);
            }

            chest.collision = false; // Mark chest as interacted
            gp.obj[index] = null; // Remove the chest from the game
        }
    }

}