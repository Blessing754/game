package tiles;

import java.awt.image.BufferedImage;

public class Tile {
    // The image variable holds the graphical representation of the tile.
    public BufferedImage image;

    // The collision variable indicates whether the tile is solid or walkable.
    // If collision is true, entities (like the player or NPCs) cannot move through this tile.
    // If false, entities can pass through it.
    public boolean collision = false;
}
