package scriptWriter;

import ai.writeutils.WriteUtil;

import javax.print.DocFlavor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * 2/28/2015
 */
public class WriteRandomScript {
    public static void main(String args[]) {
        int fileLength = 20;
        String fileName = "ais/random.ai";
        try {
            BufferedWriter fileOut = new BufferedWriter(new FileWriter(new File(fileName)));
            ArrayList<String> labels = new ArrayList<String>();
            int registersUsed = 4;

            fileOut.append("### Randomly Generated Script ###\n");
            fileOut.append("labl start\n");
            labels.add("start");
            for (int i = 0; i < fileLength; i++) {
                int commandCode = (int) (Math.random() * 27);
                switch(commandCode) {
                    case 0: labels.add("L"+labels.size());
                        fileOut.append(WriteUtil.getCommandTemplates().get(0).applyArguments(labels.get(labels.size()-1)));
                        break;
                    case 1:case 2:case 3:case 4:case 5:case 6:case 7:
                        fileOut.append(WriteUtil.getCommandTemplates().get(commandCode).applyArguments(labels.get((int)(Math.random() * labels.size()))));
                        break;
                    case 11:case 12:case 13:case 14:case 15:case 16:case 18:case 27:
                        fileOut.append(WriteUtil.getCommandTemplates().get(commandCode).applyArguments(""+(int)(Math.random()*registersUsed),""+(int)(Math.random()*registersUsed)));
                        break;
                    case 17:
                        fileOut.append(WriteUtil.getCommandTemplates().get(commandCode).applyArguments(""+(int)(Math.random()*registersUsed),""+(int)(Math.random()*256)));
                        break;
                    default:
                        fileOut.append(WriteUtil.getCommandTemplates().get(commandCode).applyArguments());
                        break;
                }
                fileOut.append("\n");
            }
            fileOut.close();

        } catch (Exception e) {
            System.err.println("Something went wrong.");
        }
    }
}
