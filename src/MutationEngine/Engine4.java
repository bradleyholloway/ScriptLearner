package MutationEngine;

import ai.writeutils.Mutator;
import ai.writeutils.WriteUtil;
import game.engine.HeadlessGame;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Roberto on 3/1/2015.
 */
public class Engine4 {
    Mutator mutefile = new Mutator();
    int numPlayers;

    public Engine4()
    {
        numPlayers = 10;
        for(int  i = 0; i<numPlayers; i++)
            mutefile.writeToFile(new File("ais/robertoGenetics/competitor" + i+".ai"));

    }

    public Engine4(int numPlayers)
    {
        for(int  i = 0; i<numPlayers; i++)
            mutefile.writeToFile(new File("ais/robertoGenetics/competitor" + i+".ai"));

    }

    /**
     * adds a random action to the current Mutator's buffer
     *
     */
    public void addRandomAction()
    {
        int commandIndex = (int) Math.round( Math.random()*7+19);
        mutefile.appendCommand(WriteUtil.getCommandTemplates().get(commandIndex).applyArguments());
    }

    public void deleteRandomAction()

    {
        mutefile.deleteLine((int) Math.random() * (mutefile.currentLength() - 1));
    }

    public void runMatchedGeneration(int playerMatchesPerGeneration, int numPerMatch)
    {
        double [] scores = new double [numPlayers];
        ArrayList<int []> matches = MatchGenerator(playerMatchesPerGeneration,numPerMatch);

    }

    public ArrayList<int []> MatchGenerator(int playerMatchesPerGeneration, int numPerMatch)
    {
        int [] playerMatchesLeft = new int[numPlayers];
        for(int current: playerMatchesLeft)
            playerMatchesLeft[current] = playerMatchesPerGeneration;



        return null;
    }

    public ArrayList<int []> MatchGenerator(int playerMatchesPerGeneration)
    {
        return null;
    }

    public void runBasicGeneration ()
    {
        ArrayList<File> contestants = new ArrayList<File>();

        int bestIndex = 0;

        for(int i = 0; i <numPlayers; i++)
        {
            contestants.add(new File("ais/robertoGenetics/competitor" + i+".ai"));
        }
        HeadlessGame game = new HeadlessGame(contestants);
        int[][] results = game.runNGames(1,1000);

        for(int i = 0; i <numPlayers; i++)
        {
            if(results[i][2]>results[bestIndex][2])
            {
                bestIndex = i;
            }
            else
            {
                if(results[i][0]>results[bestIndex][0])
                {
                    bestIndex = i;
                }
            }
        }
        System.out.print(bestIndex);

        setNewGeneration(bestIndex);
    }

    public void setNewGeneration(int bestIndex)
    {
        mutefile = new Mutator(new File("ais/robertoGenetics/competitor" + bestIndex+".ai"));
        mutefile.overwriteFile(new File("ais/robertoGenetics/competitor" + 0 + ".ai"));

        for(int i = 1; i <numPlayers; i++)
        {
            int numVariations = (int)(Math.random()*10);
            if((int)Math.round(Math.random()) == 1)
                for(int x = 0; x < numVariations; x++)
                    addRandomAction();
            else
                for(int x = 0; x < numVariations; x++)
                    deleteRandomAction();
            mutefile.overwriteFile(new File("ais/robertoGenetics/competitor" + i + ".ai"));
        }
    }
}
