import ai.genetics.GenerationManager;

import java.io.File;

/**
 * 3/1/2015
 */
public class Scratchwork {
    public static void main(String args[]) {
        String fileName = "ais/dodgey.ai";
        if (args.length > 0) {
            fileName = args[0];
        }
        GenerationManager.generateWithSeed(new File(fileName));
        while(true) {
            GenerationManager.generateWithSeed(new File("ais/bradleygenetics/generation99/0.ai"));
        }
    }
}
