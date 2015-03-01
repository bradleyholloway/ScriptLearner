package ai.genetics;

import ai.writeutils.Mutator;
import ai.writeutils.WriteUtil;
import game.engine.HeadlessGame;
import game.players.Player;

import java.io.File;
import java.util.ArrayList;

/**
 * 2/28/2015
 */
public class GenerationManager {

    private static final int breadth = 16;
    private static final int rematches = 10;
    private static final int generations = 20;
    private static final int registersUsed = 8;

    private static final int commandsPossible = WriteUtil.getCommandTemplates().size();
    private static final int actionCommandsPossible = WriteUtil.getActionTemplates().size();

    public static void generateWithSeed(File seed) {
        ArrayList<File> individuals;
        for (int generation = 0; generation < generations; generation++) {
            Mutator mutator = new Mutator(seed);
            individuals = new ArrayList<File>();
            File f = new File("ais/bradleygenetics/generation"+generation+"/0.ai");
            individuals.add(f);
            mutator.writeToFile(f);
            for (int species = 1; species < breadth; species++) {
                if (species%2 ==1) {
                    int index = (int)(Math.random() * actionCommandsPossible);
                    String command = WriteUtil.getActionTemplates().get(index).applyArguments();
                    mutator.insertCommand(command,(int)(Math.random()*mutator.currentLength()));
                } else if (species%3==1) {
                    int index = (int)(Math.random() * actionCommandsPossible);
                    String command = WriteUtil.getActionTemplates().get(index).applyArguments();
                    mutator.overwriteLine(command,(int)(Math.random()*mutator.currentLength()));
                } else if (species%3==2) {
                    int index = (int) (Math.random() * commandsPossible);
                    switch(index) {
                        case 0:
                            String labl = "";
                            for (int i = 0; i < 5 + Math.random()*2; i++) {
                                labl += (char)(Math.random()*26+65);
                            }
                            mutator.appendCommand(WriteUtil.getCommandTemplates().get(index).applyArguments(".L"+labl));
                            break;
                        case 1:case 2:case 3:case 4:case 5:case 6:case 7:
                            mutator.appendCommand(WriteUtil.getCommandTemplates().get(index).applyArguments(mutator.getLabels().get((int)(Math.random() * mutator.getLabels().size()))));
                            break;
                        case 11:case 12:case 13:case 14:case 15:case 16:case 18:case 27:
                            mutator.appendCommand(WriteUtil.getCommandTemplates().get(index).applyArguments("" + (int) (Math.random() * registersUsed), "" + (int) (Math.random() * registersUsed)));
                            break;
                        case 17:
                            mutator.appendCommand(WriteUtil.getCommandTemplates().get(index).applyArguments("" + (int) (Math.random() * registersUsed), "" + (int) (Math.random() * 256)));
                            break;
                        default:
                            mutator.appendCommand(WriteUtil.getCommandTemplates().get(index).applyArguments());
                            break;
                    }

                } else if (species %3==0) {
                    mutator.deleteLine((int)(Math.random()*mutator.currentLength()));
                }
                f = new File("ais/bradleygenetics/generation"+generation+"/"+species+".ai");
                individuals.add(f);
                mutator.writeToFile(f);
            }//individuals is now populated


            ArrayList<File> subListBattle;
            int[][] results = new int[individuals.size()][Player.trackedStats];
            for (int i = 0; i < individuals.size(); i++) {
                for (int j = i+1; j < individuals.size(); j++) {
                    subListBattle = new ArrayList<File>();
                    subListBattle.add(individuals.get(i));
                    subListBattle.add(individuals.get(j));
                    HeadlessGame game = new HeadlessGame(subListBattle);
                    int[][] result = game.runNGames(rematches,1000);
                    add(results, result,i,j);
                }
            }
            double[] k2d = new double[individuals.size()];
            for (int i = 0; i < individuals.size(); i++) {
                k2d[i] = (double)results[i][0]*results[i][0]/((results[i][1]==0)?1:results[i][1]);
            }
            int maxIndex = 0;
            for(int i = 1; i < individuals.size(); i++) {
                if (k2d[i] > k2d[maxIndex]) {
                    maxIndex = i;
                }
            }
            //max Index now holds most successfull individual
            System.out.println("Generation "+generation+": "+maxIndex+": "+k2d[maxIndex]);
            seed = individuals.get(maxIndex);
        }
    }
    private static void add(int[][] a, int[][] b, int c, int d) {
        for(int j = 0; j < a[0].length; j++) {
            a[c][j] += b[0][j];
            a[d][j] += b[1][j];
        }
    }
}
