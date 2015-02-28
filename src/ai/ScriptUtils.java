package ai;

import ai.commands.*;
import ai.commands.general.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

/**
 * 2/28/2015
 */
public class ScriptUtils {
    public static AiNode loadFromFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            AiNode node = new AiNode();
            node.addCommand(new Comment("###  " + file.toString() + "  ###"));
            HashMap<String, Integer> labels = new HashMap<String, Integer>();
            int linum = 1;
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                parseLabels(currentLine, linum, labels);
                linum++;
            }
            reader.close();
            reader = new BufferedReader(new FileReader(file));
            linum = 1;
            while ((currentLine = reader.readLine()) != null) {
                node.addCommand(parseCommand(currentLine, linum, labels));
                linum++;
            }
            reader.close();
            return node;
        } catch (Exception e) {
            System.out.println("File Error : " + file + " : " + e.getMessage());
        }
        return null;
    }

    private static void parseLabels(String line, int linum, HashMap<String, Integer> labels) {
        String command = line.substring(0, Math.min(4, line.length())).toLowerCase();
        String[] params = line.substring(Math.min(5, line.length())).toLowerCase().split(" ");

        if (command.equals("labl")) {
            if (!labels.containsKey(params[0])) {
                labels.put(params[0], linum);
            }
        }
    }

    private static AiCommand parseCommand(String line, int linum, HashMap<String, Integer> labels) {
        String command = line.substring(0, Math.min(4, line.length())).toLowerCase();
        String[] params = line.substring(Math.min(5, line.length())).toLowerCase().split(" ");
        //System.out.print("[" + linum + "] " + command + " : ");
        //System.out.println(Arrays.toString(params));

        AiCommand aiCommand;
        if (command.equals("labl")) {
            aiCommand = new Comment("Label: "+params[0]);
        } else if (command.equals("goto")) {
            int destination;
            try {
                destination = Integer.parseInt(params[0]);
            } catch (Exception e) {
                //System.out.println("Exception in parsing int, trying label");
                if (labels.containsKey(params[0])) {
                    destination = labels.get(params[0]);
                } else {
                    System.out.println("Destination [" + params[0] + "] does not exist.");
                    return new NoOp();
                }
            }
            aiCommand = new GotoCommand(destination);
        } else if (command.contains("#")) {
            aiCommand = new Comment(line);
        } else if (command.equals("paus")) {
            aiCommand = new PauseCommand();
        } else if (command.equals("halt")) {
            aiCommand = new HaltCommand();
        } else {
            aiCommand = new NoOp();
        }

        return aiCommand;
    }


}
