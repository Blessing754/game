package PlayerEntity;

public class BattleSystem {

    public void engageBattle(PlayerEntity player1, PlayerEntity player2) {
        // Check for same tile
        if (player1.getWorldX() == player2.getWorldX() && player1.getWorldY() == player2.getWorldY()) {
            // Determine winner and loser
            PlayerEntity winner, loser;
            if (player1.getPower() == player2.getPower()) {
                // Handle tie situation
                return; // Or any other tie handling logic
            } else if (player1.getPower() > player2.getPower()) {
                winner = player1;
                loser = player2;
            } else {
                winner = player2;
                loser = player1;
            }

            // Calculate and transfer money
            int moneyTransfer = Math.min((winner.getPower() - loser.getPower()) * loser.getMoney() / (winner.getPower() + loser.getPower()), loser.getMoney());
            winner.setMoney(winner.getMoney() + moneyTransfer);
            loser.setMoney(loser.getMoney() - moneyTransfer);

            // Reset loser's position
            loser.resetPosition();

            // Log battle result
            System.out.println(winner.getName() + " wins, taking " + moneyTransfer + " money from " + loser.getName());
            System.out.println(loser.getName() + " is sent back to starting position.");
        }
    }


    public void printStats(PlayerEntity player){
        System.out.println(player.getName() + "'s Stats after battle:");
        System.out.println("Health: " + player.getHealth());
        System.out.println("Power: " + player.getPower());
        System.out.println("Money: " + player.getMoney());
        System.out.println("--------------------------------");
    }
}