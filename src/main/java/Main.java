
import othello.api.OthelloBot;
import othello.bots.*;
import othello.ui.UI;

/**
 *
 * @author riikoro
 */
public class Main {

    public static void main(String[] args) {
        UI.tournament(new JaniTileScorer(), new JaniRandom(), 50);
        /*if (args.length == 0) {
            UI.battle(new OthelloHuman(), new OthelloHuman(), true);
        } else if (args.length == 1) {
            try {
                Class botClass = Class.forName("othello.bots." + args[0]);
                OthelloBot bot;
                try {
                    bot = (OthelloBot) botClass.getDeclaredConstructor().newInstance();
                    UI.battle(new OthelloHuman(), bot, true);
                } catch (NoSuchMethodException ex) {
                    System.out.println("Invalid class " + args[0]);
                } catch (Exception ex) {
                    System.out.println("Could not instantiate class " + args[0]);
                    ex.printStackTrace();
                }
            } catch (ClassNotFoundException ex) {
                System.out.println("Invalid class name " + args[0]);
            }
        } else if (args.length == 2 || args.length == 3){
            try {
                Class botClass1 = Class.forName("othello.bots." + args[0]);
                OthelloBot bot1;
                
                Class botClass2 = Class.forName("othello.bots." + args[1]);
                OthelloBot bot2;
                try {
                    bot1 = (OthelloBot) botClass1.getDeclaredConstructor().newInstance();
                    bot2 = (OthelloBot) botClass2.getDeclaredConstructor().newInstance();

                    if (args.length == 2){
                        UI.battle(bot1, bot2, true);
                    } else {
                        int numberOfGames = Integer.parseInt(args[2]);
                        UI.tournament(bot1, bot2, numberOfGames);
                    }
                    
                } catch (Exception ex){
                    System.out.println("Error instantiating; USAGE: java -jar pathto.jar BotClass1 [BotClass2] [numberofgames]: ");
                    ex.printStackTrace();
                }
            } catch(ClassNotFoundException ex) {
                System.out.println("Exception instantiating classes:");
                ex.printStackTrace();
            }
        } else {
            System.out.println("Invalid arguments; USAGE: java -jar pathto.jar BotClass1 [BotClass2] [numberofgames]");
        }*/
    }
}
