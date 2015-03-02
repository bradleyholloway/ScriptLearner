package ai.commands.math;

import ai.commands.AiCommand;
import game.players.Player;

/**
 * 3/1/2015
 */
public class AbsoluteValueCommand extends AiCommand {
    private int r1;
    public AbsoluteValueCommand(int r1) {
        this.r1 = r1;
    }

    @Override
    public int run(Player player, int[] localData, boolean[] flags) {
        localData[r1] = Math.abs(localData[r1]);
        return STEP;
    }

    @Override
    public String toString() {
        return "Take the Absolute Value of r"+r1;
    }
}
