package ai;

import ai.commands.AiCommand;

import java.util.ArrayList;

/**
 * 2/28/2015
 */
public class AiNode {
    private static final int halt = 0;
    private static final int zero = 1;
    private static final int gt = 2;
    private static final int lt = 3;
    private static final int err = 4;

    private ArrayList<AiCommand> commands;
    private int linum;
    private int[] localData;
    private boolean[] flags;

    public AiNode() {
        commands = new ArrayList<AiCommand>();
        linum = 0;
        localData = new int[10];
        flags = new boolean[5];
    }

    public void run() {
        if (flags[0]) {
            return;
        }

        int result = 0;

        result = commands.get(linum).run(localData, flags);
        while (result >= AiCommand.STEP && linum < commands.size()) {
            if (result == AiCommand.STEP) {
                linum++;
            }
            if (result != AiCommand.STEP) {
                linum = result;
            }
            if (linum < commands.size() && linum >= 0) {
                result = commands.get(linum).run(localData, flags);
            } else {
                break;
            }
        }
        if (result == AiCommand.HALT || linum >= commands.size() || linum < 0) {
            flags[0] = true;
        } else if (result == AiCommand.WAIT) {
            linum++;
        }
    }

    public boolean addCommand(AiCommand command) {
        return commands.add(command);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\t"+((flags[0]) ? "[h]": "[ ]")+"\t"+((flags[1]) ? "[z]": "[ ]")+"\t"+((flags[2]) ? "[g]": "[ ]")+"\t"+((flags[3]) ? "[l]": "[ ]")+"\t"+((flags[err]) ? "[e]": "[ ]")+"\n");
        for (int i = 0; i < commands.size(); i++) {
            s.append(((i == linum) ? "*" : "")+"\t[" + i + "]\t" + ((i < 10) ? "\t" : "") + commands.get(i).toString() + "\n");
        }
        return s.toString();
    }
}
