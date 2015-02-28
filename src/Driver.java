import game.ScriptLearnerGame;
import utils.Game;
import utils.GameWindow;

/**
 * 2/28/2015
 */
public class Driver {
    private static int defaultWidth = 1280;
    private static int defaultHeight = 720;
    private static int defaultFrameRate = 30;

    public static void main(String args[]) {
        int width = defaultWidth, height=defaultHeight, framerate=defaultFrameRate;
        if (args.length == 2) {
            try {
                width = Integer.parseInt(args[0]);
                height = Integer.parseInt(args[1]);
            } catch (Exception e) {
                width = defaultWidth;
                height = defaultHeight;
            }
        } else if (args.length == 3) {
            try{
                width = Integer.parseInt(args[0]);
                height = Integer.parseInt(args[1]);
                framerate = Integer.parseInt(args[2]);
            } catch (Exception e) {
                width = defaultWidth;
                height = defaultHeight;
                framerate = defaultFrameRate;
            }

        } else if (args.length == 1) {
            try {
                framerate = Integer.parseInt(args[0]);
            } catch (Exception e ){
                framerate = defaultFrameRate;
            }
        }

        Game game = new ScriptLearnerGame(width, height);
        GameWindow window = new GameWindow(game, framerate);
    }
}
