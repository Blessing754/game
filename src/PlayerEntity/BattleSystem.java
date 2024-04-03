package PlayerEntity; // Declares the package for organization, PlayerEntity in this case

import javax.swing.*;

// Definition of the BattleSystem class
public class BattleSystem {

    // Method for handling the battle between two player entities
    public void engageBattle(PlayerEntity player1, PlayerEntity player2) {
        // Checks if both players are on the same tile
        if (player1.getWorldX() == player2.getWorldX() && player1.getWorldY() == player2.getWorldY()) {
            // If true, proceed with the battle

            // Declare variables to hold the winner and loser of the battle
            PlayerEntity winner, loser;

            // Compare the power of both players
            if (player1.getPower() == player2.getPower()) {
                // In case of a tie (equal power), the method returns, ending the battle
                System.out.println("Tie");
                return; // Alternatively, you can add logic to handle a tie scenario
            } else if (player1.getPower() > player2.getPower()) {
                // If player 1 is stronger, player 1 wins, player 2 loses
                winner = player1;
                loser = player2;
            } else {
                // Otherwise, player 2 wins, player 1 loses
                winner = player2;
                loser = player1;
            }

            // Calculate the money to be transferred from the loser to the winner
            // It's based on the relative power of both players and the loser's money
            int moneyTransfer = Math.min((winner.getPower() - loser.getPower()) * loser.getMoney() / (winner.getPower() + loser.getPower()), loser.getMoney());

            // Update the winner's and loser's money after the transfer
            winner.setMoney(winner.getMoney() + moneyTransfer);
            loser.setMoney(loser.getMoney() - moneyTransfer);

            // Reset the loser's position in the game world, likely to a starting point
            loser.resetPosition();

            // Print out the result of the battle to the console for logging purposes
            System.out.println(winner.name + " wins, taking " + moneyTransfer + " money from " + loser.name);
            System.out.println(loser.name + " is sent back to starting position.");
            battleStatWindow(winner);
            battleStatWindow(loser);
        }
    }

    private static void battleStatWindow(PlayerEntity player) {
        JOptionPane.showMessageDialog(null, player.name + "'s stats after battle:\nHealth: " + player.getHealth() + "\nPower: " + player.getPower() + "\nMoney: " + player.getMoney(), "Battle Outcome", JOptionPane.PLAIN_MESSAGE);
    }

}
