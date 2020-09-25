
import othello.api.OthelloBot;
import othello.bots.*;
import othello.ui.UI;

import java.lang.reflect.InvocationTargetException;

/**
 * Initialize bots and launch game or tournament.
 *
 * @author riikoro
 */
public class Main {

    /**
     * Create bot instances from args, launch game or tournament.
     *
     * @param args parameters specifying players etc, see readme
     */
    public static void main(String[] args) {
        UI.battle(new JaniDummy(), new JaniPrgrs(), true);

        /*if (args.length == 0) {
            UI.battle(new OthelloHuman(), new OthelloHuman(), true);
        } else if (args.length == 1) {
            OthelloBot bot = createBotFromClassName("othello.bots." + args[0]);
            UI.battle(new OthelloHuman(), bot, true);
        } else if (args.length >= 2) {
            OthelloBot bot1 = createBotFromClassName("othello.bots." + args[0]);
            OthelloBot bot2 = createBotFromClassName("othello.bots." + args[1]);

            if (bot1 == null || bot2 == null) {
                System.out.println("Failed to load bots");
                return;
            }

            if (args.length == 3) {
                int numberOfGames = Integer.parseInt(args[2]);
                UI.tournament(bot1, bot2, numberOfGames);
            } else {
                UI.battle(bot1, bot2, true);
            }

        } else {
            System.out.println("Invalid arguments; USAGE: java -jar pathto.jar"
                    + " BotClass1 [BotClass2] [NumberOfGames]");
        }*/
    }

    /**
     * Method for converting input argument to instance of an othellobot class.
     *
     * @param className class to create bot from
     * @return bot instance
     */
    public static OthelloBot createBotFromClassName(String className) {
        try {
            Class botClass = Class.forName(className);
            OthelloBot bot
                    = (OthelloBot) botClass.getDeclaredConstructor().newInstance();
            return bot;
        } catch (ClassNotFoundException ex) {
            System.out.println("CLASS " + className + " NOT FOUND");
            return null;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException
                | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            System.out.println("FAILED TO CREATE INSTANCE FROM BOT CLASS " + className);
            ex.printStackTrace(System.out);
            return null;
        }
    }
}
