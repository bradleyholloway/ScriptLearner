package ai.writeutils;

import java.io.*;
import java.util.ArrayList;

/**
 * 2/28/2015
 */
public class Mutator {
    private File associatedFile;
    private ArrayList<String> fileLines;
    private ArrayList<String> labels;

    public Mutator() {
        fileLines = new ArrayList<String>();
        labels = new ArrayList<String>();
    }
    public Mutator(File file) {
        associatedFile = file;
        fileLines = new ArrayList<String>();
        labels = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.substring(0,Math.min(4,line.length())).toLowerCase().equals("labl")) {
                    try {
                        labels.add(line.substring(Math.min(5, line.length())).toLowerCase().split(" ")[0]);
                    } catch (Exception e) {
                        System.err.println("Invalid label in file.");
                    }
                }
                fileLines.add(line);
            }
        } catch (Exception e) {
            System.err.println("Error in reading file: " + file.toString());
        }
    }
    public void deleteLine(int index) {
        if (fileLines.size()==0) {
            return;
        }
        if(index == 0) {
            index = 1;
        }
        String temp = fileLines.remove(index-1);
        if (temp.substring(0,Math.min(4,temp.length())).toLowerCase().equals("labl")) {
            labels.remove(temp.substring(Math.min(5,temp.length())).toLowerCase().split(" ")[0]);
        }
    }
    public void clearBuffer() {
        fileLines.clear();
        labels.clear();
    }

    public void appendCommand(String command) {
        insertCommand(command, fileLines.size()+1);
    }

    public void insertCommand(String command, int index) {
        index--;
        if (command.substring(0,Math.min(4,command.length())).toLowerCase().equals("labl")) {
            labels.add(command.substring(Math.min(5,command.length())).toLowerCase().split(" ")[0]);
        }

        if (index == fileLines.size()) {
            fileLines.add(command);
            return;
        }
        if(index == -1) {
            index++;
        }
        fileLines.add(index, command);
    }

    public void overwriteLine(String command, int index) {
        if (index == 0) {
            index = 1;
        }
        fileLines.remove(index-1);
        insertCommand(command, index);
    }

    public void writeToFile(File file) {
        file.getParentFile().mkdirs();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String command : fileLines) {
                writer.append(command+"\n");
            }
            writer.close();
            associatedFile = file;
        } catch (Exception e) {
            System.err.println("Error in writing file: " + file.toString());
        }
    }

    public void overwriteFile() {
        if(associatedFile!= null) {
            writeToFile(associatedFile);
            return;
        }
        System.err.println("No Associated File!");
    }

    public int currentLength() {
        return fileLines.size()+1;
    }

    public ArrayList<String> getLabels() {
        return labels;
    }
}
