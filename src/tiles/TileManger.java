package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManger {

    GamePanel gp; // Reference to the GamePanel for accessing game properties.
    Tile[] tile; // Array to store various tile types.
    int mapTileNum[][]; // 2D array to represent the map in terms of tile numbers.


    public TileManger(GamePanel gp) {
        this.gp = gp; // Initialize the reference to the GamePanel.
        tile = new Tile[10]; // Create an array for storing up to 10 different tile types.
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // Initialize the 2D array for the map using world dimensions.

        getTileImage(); // Call method to load tile images.
        loadMap("/maps/world01.txt"); // Load the tile map from the specified file.
    }

    public void getTileImage(){
        try {
            // Load tile images and assign them to tile objects.

            tile[0]=new Tile();
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/Grass0.png"));

            tile[1]=new Tile();
            tile[1].image= ImageIO.read(getClass().getResourceAsStream("/tiles/stone1.png"));

            tile[2]=new Tile();
            tile[2].image= ImageIO.read(getClass().getResourceAsStream("/tiles/Water2.png"));

            tile[3]=new Tile();
            tile[3].image= ImageIO.read(getClass().getResourceAsStream("/tiles/dirt3.png"));

            tile[4]=new Tile();
            tile[4].image= ImageIO.read(getClass().getResourceAsStream("/tiles/tree4.png"));

            tile[5]=new Tile();
            tile[5].image= ImageIO.read(getClass().getResourceAsStream("/tiles/sand5.png"));

        }catch (IOException e) {
            e.printStackTrace(); // Print an error if there's an issue loading images.
        }
    }


    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath); // Open an input stream for the map file.
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // Wrap the input stream in a BufferedReader.

            int col = 0;
            int row = 0;

            // Read the map file line by line and fill the mapTileNum array.
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine(); // Read a line from the map file.
                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" "); // Split the line into tile numbers.
                    int num = Integer.parseInt(numbers[col]); // Parse each number as an integer.
                    mapTileNum[col][row] = num; // Assign the tile number to the map array.
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0; // Reset column index to 0 after reaching max.
                    row++; // Move to the next row.
                }
            }
            br.close(); // Close the BufferedReader.

        } catch (Exception e) {
            e.printStackTrace(); // Print an error if there's an issue reading the map file.
        }
    }

    public void draw(Graphics2D g2) {
        // Render the map tiles to the screen.
        int worldCol = 0; // Column index for looping through the map array.
        int worldRow = 0; // Row index for looping through the map array.

        // Loop through each tile in the map array and draw it.
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow]; // Get the tile number.

            // Calculate the tile's x and y position in the world.
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            // Convert world coordinates to screen coordinates.
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Check if the tile is within the visible area of the screen.
            // Visibility Check for Each Tile: The purpose of this if statement is to check if a tile is within the visible area of the screen.
            // If a tile is outside the player's current view, there's no need to draw it.
            // This is an optimization technique to avoid rendering parts of the world that the player cannot see, thereby improving the game's performance.
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && // This checks if the right edge of the tile (worldX + gp.tileSize)
                    // is to the right of the left edge of the visible area.
                    // The left edge of the visible area is calculated by gp.player.worldX - gp.player.screenX (player's position minus half the screen width).

                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && //  This checks if the left edge of the tile (worldX - gp.tileSize)
                    // is to the left of the right edge of the visible area.
                    // The right edge of the visible area is gp.player.worldX + gp.player.screenX (player's position plus half the screen width).

                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && // This checks if the bottom edge of the tile (worldY + gp.tileSize)
                    // is below the top edge of the visible area,
                    // which is gp.player.worldY - gp.player.screenY (player's position minus half the screen height).

                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) { // This checks if the top edge of the tile (worldY - gp.tileSize)
                // is above the bottom edge of the visible area,
                // which is gp.player.worldY + gp.player.screenY (player's position plus half the screen height).

                // Draw the tile image.
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++; // Move to the next column.

            // When the end of a row is reached, reset the column and move to the next row.
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
