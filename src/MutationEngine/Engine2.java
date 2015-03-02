package MutationEngine;

import ai.writeutils.Mutator;
import ai.writeutils.WriteUtil;
import game.engine.HeadlessGame;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Roberto on 3/1/2015.
 */
public class Engine2 {



    int numCompetetors = 10;
    int [] scores  = new int [numCompetetors];
    public Mutator[] mutators = new Mutator[numCompetetors];
    int fileLength = 20;

    public Engine2()
    {
        int current = 0;
        for (int i = 0; i < mutators.length; i++)
        {
            mutators[i] = new Mutator();
            File f = new File("ais/competitor" + i+".ai");
            mutators[i].writeToFile(f);
            createRandom(i);
        }
    }


    public void createRandom (int mutatorindex)
    {
        Mutator index = mutators[mutatorindex];
        ArrayList<String> labels = new ArrayList<String>();
        int registersUsed = 4;
        index.clearBuffer();

        index.appendCommand(WriteUtil.getCommandTemplates().get(0).applyArguments("start"));
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
                case 10:
                    break;
                default:
                    index.appendCommand(WriteUtil.getCommandTemplates().get(commandCode).applyArguments());
                    break;
            }
        }
        index.overwriteFile();
    }



    public void simpleMutation(int mutatorindex)
    {
        Mutator index = mutators[mutatorindex];
        ArrayList<String> labels = new ArrayList<String>();
        int registersUsed = 4;

        index.appendCommand(WriteUtil.getCommandTemplates().get(0).applyArguments("start"));

        int commandCode = (int) (Math.random() * 27);
        labels.add("start");
        switch(commandCode) {
            case 0: labels.add("L"+labels.size());
                index.insertCommand(WriteUtil.getCommandTemplates().get(commandCode).applyArguments(labels.get(labels.size() - 1)), (int) Math.random() * (index.currentLength()-1));
                break;
            case 1:case 2:case 3:case 4:case 5:case 6:case 7:
                index.insertCommand(WriteUtil.getCommandTemplates().get(commandCode).applyArguments(labels.get((int) (Math.random() * labels.size()))), (int) Math.random() * (index.currentLength()-1));
                break;
            case 11:case 12:case 13:case 14:case 15:case 16:case 18:case 27:
                index.insertCommand(WriteUtil.getCommandTemplates().get(commandCode).applyArguments("" + (int) (Math.random() * registersUsed), "" + (int) (Math.random() * registersUsed)),(int)Math.random()*(index.currentLength()-1));
                break;
            case 17:
                index.insertCommand(WriteUtil.getCommandTemplates().get(commandCode).applyArguments("" + (int) (Math.random() * registersUsed), "" + (int) (Math.random() * 256)),(int)Math.random()*(index.currentLength()-1));
                break;
            default:
                index.insertCommand(WriteUtil.getCommandTemplates().get(commandCode).applyArguments(),(int)Math.random()*(index.currentLength()-1));
                break;
        }
        index.overwriteFile();
    }


    public void addMutation(int mutatorindex)
    {
        Mutator index = mutators[mutatorindex];
        ArrayList<String> labels = new ArrayList<String>();
        int registersUsed = 4;

        int commandCode = (int) (Math.random() * 27);
        index.appendCommand(WriteUtil.getCommandTemplates().get(0).applyArguments("start"));
        labels.add("start");
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
        index.overwriteFile();
    }

    public void deleteMutation(int mutatorindex)
    {
        Mutator index = mutators[mutatorindex];
        index.deleteLine((int)Math.random()*(index.currentLength()-1));
    }



    public void testMutations()
    {
        ArrayList<File> contestants = new ArrayList<File>();

        for(int x = 0; x < numCompetetors; x++)
        {
            for(int y = 0; y < numCompetetors; y++)
            {
                contestants.clear();
                contestants.add(new File("ais/competitor" + x+".ai"));
                contestants.add(new File("ais/competitor" + y+".ai"));
                HeadlessGame game = new HeadlessGame(contestants);
                int[][] results = game.runNGames(1,1000);
                if(results [0][2] > results [1][2])
                    scores[x]++;
                else
                    if(results [0][1] > results [1][1])
                        scores[x]++;
                scores[y]--;

            }
        }


        int secondBest = 0;
        int best = 0;
        int bestnum = 0;
        for(int i = 0; i < scores.length; i++) {
            if(scores[i]>bestnum)
            {
                bestnum = scores[i];
                secondBest = best;
                best = i;
            }
        }

        mutators[best].writeToFile(new File("ais/competitor" + 0 +".ai"));
        mutators[secondBest].writeToFile(new File("ais/competitor" + 1 +".ai"));
        createRandom(2);

        for(int i = 0; i < scores.length; i++) {
            if(i<2)
            {
                mutators[i] = new Mutator(new File("ais/competitor" + i +".ai"));
            }
            else{
                if(best!=0&&i%2==1)
                    mutators[best].writeToFile(new File("ais/competitor" + i + ".ai"));
                if(secondBest!=1&&i%2==0)
                    mutators[secondBest].writeToFile(new File("ais/competitor" + i + ".ai"));
                mutators[i] = new Mutator(new File("ais/competitor" + i +".ai"));
                for(int x = 0; x< 4; x++)
                simpleMutation(i);
                if(i%3==0)
                    for(int x = 0; x< 4; x++)
                        addMutation(i);

                if(i%3==1)
                    for(int x = 0; x< 4; x++)
                        deleteMutation(i);
            }
        }
    }
}
