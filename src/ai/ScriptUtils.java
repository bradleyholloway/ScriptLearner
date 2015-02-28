package ai;

import ai.commands.*;
import ai.commands.control.*;
import ai.commands.general.*;
import ai.commands.math.*;

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
            node.addCommand(new CommentCommand("###  " + file.toString() + "  ###"));
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
            aiCommand = new CommentCommand("Label: "+params[0]);
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
                    return new NoOpCommand();
                }
            }
            aiCommand = new GotoCommand(destination);
        } else if (command.contains("#")) {
            aiCommand = new CommentCommand(line);
        } else if (command.equals("paus")) {
            aiCommand = new PauseCommand();
        } else if (command.equals("halt")) {
            aiCommand = new HaltCommand();
        } else if (command.equals("addl")) {
            aiCommand = new AddCommand(Integer.parseInt(params[0]),Integer.parseInt(params[1]));
        } else if (command.equals("subl")) {
            aiCommand = new SubtCommand(Integer.parseInt(params[0]),Integer.parseInt(params[1]));
        } else if (command.equals("incl")) {
            aiCommand = new InclCommand(Integer.parseInt(params[0]));
        } else if (command.equals("decl")) {
            aiCommand = new DeclCommand(Integer.parseInt(params[0]));
        } else if (command.equals("comp")) {
            aiCommand = new CompareCommand(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        } else if (command.equals("mult")) {
            aiCommand = new MultCommand(Integer.parseInt(params[0]),Integer.parseInt(params[1]));
        } else if (command.equals("setr")) {
            aiCommand = new SetrCommand(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        } else if (command.equals("goge")) {
            int destination;
            try {
                destination = Integer.parseInt(params[0]);
            } catch (Exception e) {
                //System.out.println("Exception in parsing int, trying label");
                if (labels.containsKey(params[0])) {
                    destination = labels.get(params[0]);
                } else {
                    System.out.println("Destination [" + params[0] + "] does not exist.");
                    return new NoOpCommand();
                }
            }
            aiCommand = new GogeCommand(destination);
        } else if (command.equals("gogt")) {
            int destination;
            try {
                destination = Integer.parseInt(params[0]);
            } catch (Exception e) {
                //System.out.println("Exception in parsing int, trying label");
                if (labels.containsKey(params[0])) {
                    destination = labels.get(params[0]);
                } else {
                    System.out.println("Destination [" + params[0] + "] does not exist.");
                    return new NoOpCommand();
                }
            }
            aiCommand = new GogtCommand(destination);
        } else if (command.equals("gole")) {
            int destination;
            try {
                destination = Integer.parseInt(params[0]);
            } catch (Exception e) {
                //System.out.println("Exception in parsing int, trying label");
                if (labels.containsKey(params[0])) {
                    destination = labels.get(params[0]);
                } else {
                    System.out.println("Destination [" + params[0] + "] does not exist.");
                    return new NoOpCommand();
                }
            }
            aiCommand = new GoleCommand(destination);
        } else if (command.equals("golt")) {
            int destination;
            try {
                destination = Integer.parseInt(params[0]);
            } catch (Exception e) {
                //System.out.println("Exception in parsing int, trying label");
                if (labels.containsKey(params[0])) {
                    destination = labels.get(params[0]);
                } else {
                    System.out.println("Destination [" + params[0] + "] does not exist.");
                    return new NoOpCommand();
                }
            }
            aiCommand = new GoltCommand(destination);
        } else if (command.equals("goie")) {
            int destination;
            try {
                destination = Integer.parseInt(params[0]);
            } catch (Exception e) {
                //System.out.println("Exception in parsing int, trying label");
                if (labels.containsKey(params[0])) {
                    destination = labels.get(params[0]);
                } else {
                    System.out.println("Destination [" + params[0] + "] does not exist.");
                    return new NoOpCommand();
                }
            }
            aiCommand = new GoieCommand(destination);
        } else if (command.equals("gone")) {
            int destination;
            try {
                destination = Integer.parseInt(params[0]);
            } catch (Exception e) {
                //System.out.println("Exception in parsing int, trying label");
                if (labels.containsKey(params[0])) {
                    destination = labels.get(params[0]);
                } else {
                    System.out.println("Destination [" + params[0] + "] does not exist.");
                    return new NoOpCommand();
                }
            }
            aiCommand = new GoneCommand(destination);
        } else {
            aiCommand = new NoOpCommand();
        }

        return aiCommand;
    }


}
