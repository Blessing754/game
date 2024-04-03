package main;

import java.awt.*;
import javax.swing.*;
import PlayerEntity.Player;
import PlayerEntity.PlayerEntity;

public class StatusBoard extends JPanel {
    private Player player1;
    private PlayerEntity player2;

    public StatusBoard(Player player1, PlayerEntity player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    private void drawBackground(Graphics2D g, int x, int y, int width, int height) {
        g.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black background
        g.fillRect(x, y, width, height);
    }

    private void drawPlayerStatus(Graphics2D g, Player player, int x, int y, int width, int height) {
        // Draw the background first
        drawBackground(g, x - 5, y - 15, width, height);

        // Set text properties
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 11));

        // Draw the player name
        String playerName = "Name: " + player.getName();
        g.drawString(playerName, 500, 15);

        // Draw player stats
        String player1Stats =  String.format("Health: %d, Money: $%d, Power: %d, Steps: %d, Weapon: %s",
                player.getHealth(), player.getMoney(), player.getPower(), player.getStepsRemaining(),
                player.getEquippedWeapon() != null ? player.getEquippedWeapon().getName() : "None");
        g.drawString(player1Stats, 500, 30 + 20);

        // Draw the player's inventory
        String inventory = "Inventory: ";
        if (!player.getInventory().isEmpty()) {
            inventory += player.getInventory().stream()
                    .map(item -> item.getName())
                    .reduce((item1, item2) -> item1 + ", " + item2)
                    .get();
        } else {
            inventory += "Empty";
        }
        g.drawString(inventory, 500, 50 + 40);
    }

    private void drawPlayerEntityStatus(Graphics2D g, PlayerEntity player, int x, int y, int width, int height) {
        // Draw the background
        drawBackground(g, x - 5, y - 15, width, height);

        // Set text properties
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 11));

        // Draw the player name
        String playerName = "Name: " + player.getName();
        g.drawString(playerName, 30, 15);

        // Draw player stats
        String player2Stats = String.format("Health: %d, Money: $%d, Power: %d, Steps: %d, Weapon: %s",
                player.getHealth(), player.getMoney(), player.getPower(), player.getStepsRemaining(),
                player.getEquippedWeapon() != null ? player.getEquippedWeapon().getName() : "None");
        g.drawString(player2Stats, 30, 30 + 20);

        // Draw the player's inventory (assuming PlayerEntity has inventory; adjust if not)
        String inventory = "Inventory: ";
        if (!player.getInventory().isEmpty()) {
            inventory += player.getInventory().stream()
                    .map(item -> item.getName())
                    .reduce((item1, item2) -> item1 + ", " + item2)
                    .orElse("Empty");
        } else {
            inventory += "Empty";
        }
        g.drawString(inventory, 30, 50 + 40);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int y = 20; // Top margin
        int width = 250; // Background width
        int height = 60; // Background height

        // Draw Player 1's status on the right
        drawPlayerStatus(g2, player1, getWidth() - width, y, width, height);

        // Draw Player 2's status on the left
        drawPlayerEntityStatus(g2, player2, 10, y, width, height);
    }
    public void render(Graphics2D g2) {
        int y = 20; // Top margin
        int width = 400; // Background width
        int height = 100; // Background height, adjusted to fit the text

        // Adjust the x-coordinate for player 1 to align to the right
        int player1X = getWidth() - width - 10; // 10 pixels padding from the right edge

        // Draw Player 1's status on the right
        drawPlayerStatus(g2, player1, player1X, y, width, height); // Adjusted for Player 1

        // Draw Player 2's status on the left
        drawPlayerEntityStatus(g2, player2, 10, y, width, height); // Adjusted for Player 2
    }

}
