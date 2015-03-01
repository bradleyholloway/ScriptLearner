import game.engine.HeadlessGame;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 2/28/2015
 */
public class HeadlessDriver {
    public static void main(String args[]) {
        ArrayList<File> contestants = new ArrayList<File>();

        for(int i = 0; i < 3; i++) {
            contestants.add(new File("ais/test.ai"));
        }

        HeadlessGame game = new HeadlessGame(contestants);
        int[][] results = game.runGame(10000);

        for(int i = 0; i < results.length; i++) {
            System.out.println(Arrays.toString(results[i]));
        }
    }
}
