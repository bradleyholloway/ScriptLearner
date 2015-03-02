package ai;

import ai.commands.AiCommand;
import game.players.Player;

import java.util.ArrayList;

/**
 * 2/28/2015
 */
public class AiNode {
    public static final int timeout = 1;

    public static final int halt = 0;
    public static final int zero = 1;
    public static final int gt = 2;
    public static final int lt = 3;
    public static final int err = 4;

    private Player player;
    private ArrayList<AiCommand> commands;
    private int linum;
    private int[] localData;
    private boolean[] flags;

    public AiNode() {
        commands = new ArrayList<AiCommand>();
        linum = 0;
        localData = new int[20];
        flags = new boolean[5];
    }
    public void linkPlayer(Player player) {
        this.player = player;
    }

    public boolean run() {
        if (linum==commands.size()) {
            flags[halt] = true;
        }

        if (flags[halt]) {
            return false;
        }
        long startTime = (long)(System.nanoTime()/1e6);
        int result = 0;

        result = commands.get(linum).run(player, localData, flags);
        while (result >= AiCommand.STEP && linum < commands.size() && (System.nanoTime()/1e6)-startTime < timeout) {
            if (result == AiCommand.STEP) {
                linum++;
            }
            if (result != AiCommand.STEP) {
                linum = result;
            }
            if (linum < commands.size() && linum >= 0) {
                result = commands.get(linum).run(player, localData, flags);
            } else {
                break;
            }
        }
        if ((System.nanoTime()/1e6)-startTime >= timeout) {
            return false;
        }
        if (result == AiCommand.HALT || linum >= commands.size() || linum < 0) {
            flags[halt] = true;
            return false;
        } else if (result == AiCommand.WAIT) {
            linum++;
        }
        return true;
    }

    public int[] getRegisters() {
        return localData;
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
