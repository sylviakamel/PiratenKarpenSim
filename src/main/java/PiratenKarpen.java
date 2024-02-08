import pk.Game;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class PiratenKarpen {
    
    static Game game = new Game();
    public static void main(String[] args) {
        final Logger logger = LogManager.getLogger(PiratenKarpen.class);
        logger.trace("Welcome to Piraten Karpen Simulator!");
        Game.home(args[0], args[1]);
        logger.trace("That's all folks!");

    }

}