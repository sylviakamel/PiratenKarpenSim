package pk;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.*;

public class Players {
    int score = 0; //not static bc differs for each player
    static int roundScore = 0;
    int numWins = 0;
    String Strat = "";
    String maxFace = "";
    // rolling_stragey
    public void setNumWins() {
        this.numWins++;
    }
    public int getNumWins() {
        return this.numWins;
    }
    public Players(String string) {}
    public void editScore(int score) {
        this.score = this.score + score;
    }
    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = 0;
    }
    public int getRoundScore() {
        return this.roundScore;
    }
    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }

    public void setStrat(String Strat) {
        this.Strat = Strat;
    }
    public void setMaxFace(String maxFace) {
        this.maxFace = maxFace;
    }
    public String getMaxFace() {
        return this.maxFace;
    }

    public int playRound(String name, int round, Players player, String Strat) {
        final Logger logger = LogManager.getLogger(Players.class);
        logger.trace("\n" + name + ", Round " + round);
        logger.trace("\nRoll 1:");
        Dice dice = new Dice();
        Object[] rolls = new Object[8];
        for (int j = 0; j < rolls.length; j++) {
            rolls[j] = dice.roll();
        }
        for (int j = 0; j < rolls.length - 1; j++) {
            logger.trace(rolls[j] + ", ");
        }
        logger.trace(rolls[7]);
        int skullCount = 0;
        for (Object index: rolls) {
            if (index == Faces.SKULL) {
                skullCount++;
            }
        }
        logger.trace("\nskulls: " + skullCount);

        String[] stringRolls = new String[rolls.length];
        for (int j = 0; j < rolls.length; j++) {
            stringRolls[j] = rolls[j].toString();
        }
        
        if (skullCount < 3) {
            countDice(stringRolls, player.getScore(), player);
            if (Strat == "random") {
                return rerollRand(rolls, skullCount, stringRolls, score, player);
            }
            else {
                return rerollCombo(rolls, skullCount, stringRolls, score, player);
            }
            
        }
        else {
            logger.trace("recieved 3 skulls, round is over");
            player.setRoundScore(roundScore);
            player.editScore(roundScore);
            logger.trace("Roll Score: " + roundScore);

            return player.getScore();
        }
    }

    public static int rerollRand(Object[] rolls, int skullCount, String stringRolls[], int score, Players player) {
        final Logger logger = LogManager.getLogger(Players.class);
        Dice dice = new Dice();
        int rollCount = 1;
        while (skullCount < 3) {
            rollCount++;
            logger.trace("\nRoll " + rollCount + ":");
            //making a ist of indices I want to reroll
            ArrayList<String> myList = new ArrayList<>();
            Random rand = new Random();
            int numOfRerolledIndices = rand.nextInt(7) + 2;
            for (int i = 0; i < numOfRerolledIndices; i++) {
                int m = rand.nextInt(8);
                String n = Integer.toString(m);
                myList.add(n);
            }
            String[] strArrayRerolls = myList.toArray(new String[myList.size()]); //list of indices I wanna reroll
    
            for (String index: strArrayRerolls) {
                int i = Integer.parseInt(index);
                if (rolls[i] != Faces.SKULL) {
                    rolls[i] = dice.roll();
                }
            }
            for (int i = 0; i < rolls.length; i++) { //updating stringRolls w/ new rerolls
                stringRolls[i] = rolls[i].toString();
            } 
            skullCount = 0;
            for (Object index: rolls) {
                if (index == Faces.SKULL) {
                    skullCount++; //counting skulls
                }
            }
    
            if (strArrayRerolls.length == 1) {
                logger.trace("You must reroll at least 2 dice at a time!");
            }
            else {
                for (int i = 0; i < rolls.length - 1; i++) {
                    logger.trace(rolls[i] + ", ");
                }
                logger.trace(rolls[7]);
    
                logger.trace("\nskulls: " + skullCount);
                if (skullCount < 3) {
                    countDice(stringRolls, player.getScore(), player); 
                }
            }
        }
        logger.trace("recieved 3 skulls, round is over");
        player.setRoundScore(roundScore);
        player.editScore(roundScore);
        logger.trace("Roll Score: " + roundScore);
        logger.trace("overall Score: " + player.getScore());
        return player.getScore();
    }
    public static int rerollCombo(Object[] rolls, int skullCount, String stringRolls[], int score, Players player) {
        final Logger logger = LogManager.getLogger(Players.class);
        Dice dice = new Dice();
        int rollCount = 1;
        while (skullCount < 3) {
            rollCount++;
            logger.trace("\nRoll " + rollCount + ":");

            String faceAvoid = player.getMaxFace(); //face to avoid rerolling bc it's common
            //making a ist of indices I want to reroll
            ArrayList<String> myList = new ArrayList<>();
            Random rand = new Random();
            int numOfRerolledIndices = rand.nextInt(7) + 2;
            for (int i = 0; i < numOfRerolledIndices; i++) {
                int m = rand.nextInt(8);
                String n = Integer.toString(m);
                myList.add(n);
            }
            String[] strArrayRerolls = myList.toArray(new String[myList.size()]); //list of indices I wanna reroll
    
            for (String index: strArrayRerolls) {
                int i = Integer.parseInt(index);
                if ((rolls[i] != Faces.SKULL) && (rolls[i] != faceAvoid)) {
                    rolls[i] = dice.roll();
                }
            }
            for (int i = 0; i < rolls.length; i++) { //updating stringRolls w/ new rerolls
                stringRolls[i] = rolls[i].toString();
            } 
            skullCount = 0;
            for (Object index: rolls) {
                if (index == Faces.SKULL) {
                    skullCount++; //counting skulls
                }
            }
    
            if (strArrayRerolls.length == 1) {
                logger.trace("You must reroll at least 2 dice at a time!");
            }
            else {
                for (int i = 0; i < rolls.length - 1; i++) {
                    logger.trace(rolls[i] + ", ");
                }
                logger.trace(rolls[7]);
    
                logger.trace("\nskulls: " + skullCount);
                if (skullCount < 3) {
                    countDice(stringRolls, player.getScore(), player); 
                }
            }
        }
        logger.trace("recieved 3 skulls, round is over");
        player.setRoundScore(roundScore);
        player.editScore(roundScore);
        logger.trace("Roll Score: " + roundScore);
        logger.trace("overall Score: " + player.getScore());
        return player.getScore();
    }
    public static int countDice(String stringRolls[], int score, Players player) { 
        final Logger logger = LogManager.getLogger(Players.class);
        int roundScore = 0;
        int MONKEY = 0; //initializing variables to count how many of each face there is
        int PARROT = 0;
        int GOLD = 0;
        int DIAMOND = 0;
        int SABER = 0;

        for (int i = 0; i < stringRolls.length; i++) {
            switch (stringRolls[i]) {
                case "MONKEY":
                    MONKEY++;
                    break;
                case "PARROT":
                    PARROT++;
                    break;
                case "GOLD":
                    GOLD++;  
                    break;
                case "DIAMOND":
                    DIAMOND++; 
                    break;
                case "SABER":
                    SABER++; 
                    break;
            }
        }
            int maxCount = MONKEY;
            player.setMaxFace("MONKEY");
            if (PARROT > maxCount) {
                maxCount = PARROT;
                player.setMaxFace("PARROT");
            }
            if (GOLD > maxCount) {
                maxCount = GOLD;
                player.setMaxFace("GOLD");
            }
            if (DIAMOND > maxCount) {
                maxCount = DIAMOND;
                player.setMaxFace("DIAMOND");
            }
            if (SABER > maxCount) {
                maxCount = SABER;
                player.setMaxFace("SABER");
            }
        //(Math.max(Math.max(Math.max(Math.max(GOLD, PARROT), MONKEY), DIAMOND), SABER));
        logger.trace("\nScoring: ");
        int s1 = calculateScore(MONKEY, "MONKEY", player.getScore(), roundScore, player);
        int s2 = calculateScore(PARROT, "PARROT", player.getScore(), roundScore, player);
        int s3 = calculateScore(GOLD, "GOLD", player.getScore(), roundScore, player);
        int s4 = calculateScore(DIAMOND, "DIAMOND", player.getScore(), roundScore, player);
        int s5 = calculateScore(SABER, "SABER", player.getScore(), roundScore, player);
        int s6 = indScore(GOLD, roundScore, "GOLD");
        int s7 = indScore(DIAMOND, roundScore, "DIAMOND");
        roundScore = (s1 + s2 + s3 + s4 + s5 + s6 + s7);
        player.setRoundScore(roundScore);
        
        logger.trace("Roll Score: " + roundScore);
            
        //player.editScore(player.getRoundScore());
        //logger.trace("overall Score: " + player.getScore());
        roundScore = 0;
        return roundScore;
       }
    public static int calculateScore(int numOfFaces, String faceName, int score, int roundScore, Players player){ //var is GOLD or PARROT from above function (number of them present) and faceName is string of corresponding face  
        final Logger logger = LogManager.getLogger(Players.class);
        int roundScoreCheck = roundScore;
        switch (numOfFaces){
            case 3:
                roundScore = roundScore + 100;
                logger.trace("set of 3 " + faceName + " = " + 100);
                break;
            case 4:
                roundScore = roundScore + 200;
                logger.trace("set of 4 " + faceName + " = " + 200);
                break;
            case 5:
                roundScore = roundScore + 500;
                logger.trace("set of 5 " + faceName + " = " + 500);
                break;
            case 6:
                roundScore = roundScore + 1000;
                logger.trace("set of 6 " + faceName + " = " + 1000);
                break;
            case 7:
                roundScore = roundScore + 2000;
                logger.trace("set of 7 " + faceName + " = " + 2000);
                break;
            case 8:
                roundScore = roundScore + 4000;
                logger.trace("set of 8 " + faceName + " = " + 4000);
                break;
        }
        if (roundScore == roundScoreCheck) {
            return 0;
        }
        else{
            return roundScore;
        }
    }
    
    public static int indScore(int numOfFaces, int roundScore, String faceName) {
        final Logger logger = LogManager.getLogger(Players.class);
        if (numOfFaces > 0) {
            int indPoints = numOfFaces*100;
            roundScore = roundScore + indPoints;
            logger.trace(numOfFaces + " " + faceName + " = " + indPoints);
            return indPoints;
        }
        return 0;
}
}
        