package ai.writeutils;

import java.io.*;
import java.util.ArrayList;

/**
 * 2/28/2015
 */
public class Mutator {
    private ArrayList<String> fileLines;

    public Mutator(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null)
                fileLines.add(line);
        } catch (Exception e) {
            System.err.println("Error in reading file: " + file.toString());
        }
    }

    public void appendCommand(String command) {
        insertCommand(command, fileLines.size());
    }

    public void insertCommand(String command, int index) {
        if (index == fileLines.size()) {
            fileLines.add(command);
            return;
        }
        fileLines.add(index, command);
    }

    public void overwriteLine(String command, int index) {
        fileLines.remove(index);
        insertCommand(command, index);
    }

    public void writeToFile(File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String command : fileLines) {
                writer.append(command+"\n");
            }
        } catch (Exception e) {
            System.err.println("Error in writing file: " + file.toString());
        }
    }
}
