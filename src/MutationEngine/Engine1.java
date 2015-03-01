package MutationEngine;

import ai.writeutils.Mutator;
import ai.writeutils.WriteUtil;
import game.engine.HeadlessGame;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Roberto on 2/28/2015.
 */
public class Engine1 {
    int numCompetetors = 20;
    public Mutator[] mutators = new Mutator[numCompetetors];
    int fileLength = 20;

    public Engine1()
    {
        int current = 0;
        for (int i = 0; i < mutators.length; i++)
        {
            mutators[i] = new Mutator();
            File f = new File("ais/competitor" + i+".ai");
            mutators[i].writeToFile(f);
        }
    }

    public void createRandom (int mutatorindex)
    {
        Mutator index = mutators[mutatorindex];
        ArrayList<String> labels = new ArrayList<String>();
        int registersUsed = 4;
        index.clearBuffer();

        labels.add("start");
        for (int i = 0; i < fileLength; i++) {
            int commandCode = (int) (Math.random() * 27);
            switch(commandCode) {
                case 0: labels.add("L"+labels.size());
                    index.appendCommand(WriteUtil.getCommandTemplates().get(commandCode).applyArguments(labels.get(labels.size() - 1)));
                    break;
                case 1:case 2:case 3:case 4:case 5:case 6:case 7:
                    index.appendCommand(WriteUtil.getCommandTemplates().get(commandCode).applyArguments(labels.get((int) (Math.random() * labels.size()))));
                    break;
                case 11:case 12:case 13:case 14:case 15:case 16:case 18:case 27:
                    index.appendCommand(WriteUtil.getCommandTemplates().get(commandCode).applyArguments("" + (int) (Math.random() * registersUsed), "" + (int) (Math.random() * registersUsed)));
                    break;
                case 17:
                    index.appendCommand(WriteUtil.getCommandTemplates().get(commandCode).applyArguments("" + (int) (Math.random() * registersUsed), "" + (int) (Math.random() * 256)));
                    break;
                default:
                    index.appendCommand(WriteUtil.getCommandTemplates().get(commandCode).applyArguments());
                    break;
            }
        }
        index.overwriteFile();
    }




    public void testMutations()
    {
        ArrayList<File> contestants = new ArrayList<File>();

        for(int i = 0; i < numCompetetors; i++) {
            contestants.add(new File("ais/competitor" + i+".ai"));
        }
        HeadlessGame game = new HeadlessGame(contestants);
        int[][] results = game.runNGames(2,1000);

        int highIndex = 0;
        int best = 0;
        for(int i = 0; i < results.length; i++) {
            if(results[i][0]>results[highIndex][0])
            {
                best = results[i][0];
                highIndex = i;
            }
        }

        for(int i = 0; i < results.length; i++) {
            if(i!=highIndex)
                createRandom(i);

        }
    }


}//Engine1
