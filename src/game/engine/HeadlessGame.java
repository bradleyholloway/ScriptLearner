package game.engine;

import game.players.Player;

import java.io.File;
import java.util.ArrayList;

/**
 * 2/28/2015
 */
public class HeadlessGame {

    ArrayList<Player> combatants;
    ArrayList<File> contestants;
    private long previousTime;

    public HeadlessGame(ArrayList<File> contestants) {
        this.contestants = contestants;
    }

    public int[][] runGame(int cycles) {
        int[][] results = new int[contestants.size()][];
        combatants = new ArrayList<Player>();
        ScriptLearnerShooterMatchInstance instance = new ScriptLearnerShooterMatchInstance();
        instance.loadHeadlessContent(contestants, combatants);
        previousTime = System.nanoTime();
        for (int c = 0; c < cycles; c++) {
            long temp;
            instance.update((long)Math.round(((temp = System.nanoTime()) - previousTime)/1e6));
            previousTime = temp;
        }
        for(int i = 0; i < combatants.size(); i++) {
            results[i] = combatants.get(i).getStats();
        }
        return results;
    }

    public int[][] runNGames(int n, int cycles) {
        int[][] results = new int[contestants.size()][];
        for (int i = 0; i < n; i++) {
            results = mergeDestructive(results, runGame(cycles));
        }
        return results;
    }

    private int[][] mergeDestructive(int[][] a, int[][] b){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j]+=b[i][j];
            }
        }
        return a;
    }

}
