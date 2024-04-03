package PlayerEntity;

import javax.swing.*;

public class BattleSystem {

    // Handles the battle considering the `PlayerEntity` type for both participants
    public void engageBattle(PlayerEntity player1, PlayerEntity player2) {
        if (player1.getWorldX() == player2.getWorldX() && player1.getWorldY() == player2.getWorldY()) {
            PlayerEntity winner, loser;

            int player1Strength = player1.calculateTotalStrength();
            int player2Strength = player2.calculateTotalStrength();

            if (player1Strength == player2Strength) {
                System.out.println("Tie");
                return;
            } else if (player1Strength > player2Strength) {
                winner = player1;
                loser = player2;
            } else {
                winner = player2;
                loser = player1;
            }

            int moneyTransfer = Math.min((winner.calculateTotalStrength() - loser.calculateTotalStrength()) * loser.getMoney() / (winner.calculateTotalStrength() + loser.calculateTotalStrength()), loser.getMoney());

            winner.setMoney(winner.getMoney() + moneyTransfer);
            loser.setMoney(loser.getMoney() - moneyTransfer);

            loser.resetPosition();


            int loserStrength = loser.getPower();  // Assuming getPower() returns the player's current strength
            loser.setPower(0);  // Set the loser's strength to zero
            winner.setPower(winner.getPower() - loserStrength);  // Reduce the winner's strength

            // Optional: Handle the case where the winner's strength could go negative
            if (winner.getPower() < 0) {
                winner.setPower(0);
            }

            System.out.println(winner.getName() + " wins the battle against " + loser.getName());
            battleStatWindow(winner);
            battleStatWindow(loser);
        }
    }

    private static void battleStatWindow(PlayerEntity player) {
        JOptionPane.showMessageDialog(null, player.name + "'s stats after battle:\nHealth: " + player.getHealth() + "\nTotal Strength: " + player.calculateTotalStrength() + "\nMoney: " + player.getMoney() + "\nEquipped Weapon: " + (player.getEquippedWeapon() != null ? player.getEquippedWeapon().getName() : "None"), "Battle Outcome", JOptionPane.PLAIN_MESSAGE);
    }


}
