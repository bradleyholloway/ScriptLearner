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
            mutefile.writeToFile(new File("ais/robertoGenetics4/competitor" + i+".ai"));

    }

    public Engine4(int numPlayers)
    {
        this.numPlayers = numPlayers;
        for(int  i = 0; i<numPlayers; i++)
            mutefile.writeToFile(new File("ais/robertoGenetics4/competitor" + i+".ai"));

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

    public void runMatchedGeneration(int playerMatchesPerGeneration, int minMatchSize, int maxMatchSize)
    {
        int bestCompetitor = 0;
        double [] scores = new double [numPlayers];
        ArrayList<ArrayList<Integer>> matchLists = MatchGenerator(playerMatchesPerGeneration,minMatchSize,maxMatchSize);

        for(ArrayList<Integer> match:matchLists)
        {
            ArrayList<File> contestants = new ArrayList<File>();
            for(int competitor:match)
            {
                contestants.add(new File("ais/robertoGenetics4/competitor" + competitor +".ai"));
            }
            HeadlessGame game = new HeadlessGame(contestants);
            int[][] results = game.runNGames(1,1000);
            int bestIndex = 0;
            for(int i = 0; i <match.size(); i++)
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
            scores[match.get(bestIndex)]++;
        }

        for(int i = 0; i< scores.length; i++)
        {
            if(scores[i]>scores[bestCompetitor])
            {
                bestCompetitor = i;
            }
        }
        System.out.print(bestCompetitor);
        setNewGeneration(bestCompetitor);
    }

    public void runMatchedGeneration(int playerMatchesPerGeneration, int matchSize)
    {
        runMatchedGeneration(playerMatchesPerGeneration,matchSize,matchSize);
    }


    public ArrayList<ArrayList<Integer>> MatchGenerator(int playerMatchesPerGeneration, int minMatchSize, int maxMatchSize)
    {
        ArrayList<Integer> playersSelectable =  new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> matchLists =  new ArrayList<ArrayList<Integer>>();
        int mostMatchesLeft = playerMatchesPerGeneration;
        int [] playerMatchesLeft = new int[numPlayers];

        for(int i = 0; i < playerMatchesLeft.length; i++){
            playerMatchesLeft[i] = playerMatchesPerGeneration;
        }

        while(mostMatchesLeft > 0)
        {
            ArrayList<Integer> match = new ArrayList<Integer>();
            int numPerMatch = (int) Math.round(Math.random() * (maxMatchSize - minMatchSize) + minMatchSize);
            while(mostMatchesLeft!=0&&match.size()<numPerMatch)
            {
                //creates a list of selectable players based on who has the most matches left
                for(int i = 0;i<numPlayers;i++)
                {
                    //adds player to selectable list if they have the most matches left
                    if(playerMatchesLeft[i]==mostMatchesLeft&&mostMatchesLeft!=0){
                        playersSelectable.add(i);
                    }
                }
                //if no one has the most matches, reduces the most matches
                if(playersSelectable.size()==0&&mostMatchesLeft!=0){
                    mostMatchesLeft--;
                }

                if(playersSelectable.size()>0)
                {
                    int playerToAdd = playersSelectable.get((int)Math.round(Math.random()*(playersSelectable.size()-1)));
                    playersSelectable.clear();
                    playerMatchesLeft[playerToAdd]--;
                    match.add(playerToAdd);
                }
            }
            if(mostMatchesLeft>0)
                matchLists.add(match);

        }
        return matchLists;
    }

    public void runBasicGeneration ()
    {
        ArrayList<File> contestants = new ArrayList<File>();

        int bestIndex = 0;

        for(int i = 0; i <numPlayers; i++)
        {
            contestants.add(new File("ais/robertoGenetics4/competitor" + i+".ai"));
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
        mutefile = new Mutator(new File("ais/robertoGenetics4/competitor" + bestIndex+".ai"));
        mutefile.overwriteFile(new File("ais/robertoGenetics4/competitor" + 0 + ".ai"));

        for(int i = 1; i <numPlayers; i++)
        {
            int numVariations = (int)(Math.random()*10);
            if((int)Math.round(Math.random()) == 1)
                for(int x = 0; x < numVariations; x++)
                    addRandomAction();
            else
                for(int x = 0; x < numVariations; x++)
                    deleteRandomAction();
            mutefile.overwriteFile(new File("ais/robertoGenetics4/competitor" + i + ".ai"));
        }
    }
}
