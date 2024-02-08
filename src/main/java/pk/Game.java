package pk;
import java.util.logging.Level;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class Game { 
    //private static int games = 10;
    public static Object dice;
    Game game;
    Players player1 = new Players("Player 1");
    Players player2 = new Players("Player 2");
    
    public static void home(String p1Strat, String p2Strat) {
        final Logger logger = LogManager.getLogger(Game.class);
        Game game = new Game();
        int games = 42;
        double winsPercentage1;
        double winsPercentage2;
        int numWins1 = 0; 
        int numWins2 = 0; 
        game.player1.setStrat(p1Strat);
        game.player2.setStrat(p2Strat);
        for (int i = 0; i < games; i++) {
            logger.trace("Game " + (i + 1));
            int roundCount = 1;
            
            game.player1.playRound("Player 1", 1, game.player1, p1Strat);
            game.player2.playRound("Player 2", 1, game.player2, p2Strat);
            while ((game.player1.getScore() < 6000) && (game.player2.getScore() < 6000)) {
                roundCount++;
                game.player1.playRound("Player 1", roundCount, game.player1, p1Strat);
                game.player2.playRound("Player 2", roundCount, game.player2, p2Strat);
            }

            if (game.player1.getScore() >= 6000) {
                game.player1.setScore(0); //resetting score for next game
                game.player2.setScore(0);
                game.player1.setNumWins();
                logger.trace("Player 1 won game " + (i+1));
            }
            else if (game.player2.getScore() >= 6000){
                game.player1.setScore(0);
                game.player2.setScore(0);
                game.player2.setNumWins();
                logger.trace("Player 2 won game " + (i+1));
            }

        }
        winsPercentage1 = ((game.player1.getNumWins())/42.0) * 100f;
        winsPercentage2 = ((game.player2.getNumWins())/42.0) * 100f;
        System.out.printf("PLAYER 1 WINS PERCENTAGE: %.1f%%\n", winsPercentage1);
        System.out.printf("PLAYER 2 WINS PERCENTAGE: %.1f%%\n", winsPercentage2);
    }

    public static void main(String[] args) {
        
    }
}