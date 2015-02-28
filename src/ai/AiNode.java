package ai;

import ai.commands.AiCommand;

import java.util.ArrayList;

/**
 * 2/28/2015
 */
public class AiNode {
    public static final int halt = 0;
    public static final int zero = 1;
    public static final int gt = 2;
    public static final int lt = 3;
    public static final int err = 4;

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
        if (flags[halt]) {
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
            flags[halt] = true;
        } else if (result == AiCommand.WAIT) {
            linum++;
        }
    }

    public boolean addCommand(AiCommand command) {
        return commands.add(command);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\t"+((flags[halt]) ? "[h]": "[ ]")+"\t"+((flags[zero]) ? "[z]": "[ ]")+"\t"+((flags[gt]) ? "[g]": "[ ]")+"\t"+((flags[lt]) ? "[l]": "[ ]")+"\t"+((flags[err]) ? "[e]": "[ ]")+"\n");
        for (int i = 0; i < commands.size(); i++) {
            s.append(((i == linum) ? "*" : "")+"\t[" + i + "]\t" + ((i < 10) ? "\t" : "") + commands.get(i).toString() + "\n");
        }
        return s.toString();
    }
}
