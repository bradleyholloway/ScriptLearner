package ai;

import ai.commands.AiCommand;

import java.util.ArrayList;

/**
 * 2/28/2015
 */
public class AiNode {
    private ArrayList<AiCommand> commands;
    private int linum;
    private boolean halted;

    public AiNode() {
        commands = new ArrayList<AiCommand>();
        linum = 0;
        halted = false;
    }

    public void run() {
        if (halted) {
            return;
        }

        int result = 0;

        result = commands.get(linum).run();
        while (result >= AiCommand.STEP && linum < commands.size()) {
            if (result == AiCommand.STEP) {
                linum++;
            }
            if (result != AiCommand.STEP) {
                linum = result;
            }
            result = commands.get(linum).run();
        }
        if (result == AiCommand.HALT || linum >= commands.size() || linum < 0) {
            halted = true;
        } else if (result == AiCommand.WAIT) {
            linum++;
        }
    }

    public boolean addCommand(AiCommand command) {
        return commands.add(command);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < commands.size(); i++) {
            s.append(((i == linum) ? "*" : "")+"\t[" + i + "]\t" + ((i < 10) ? "\t" : "") + commands.get(i).toString() + "\n");
        }
        return s.toString();
    }
}
