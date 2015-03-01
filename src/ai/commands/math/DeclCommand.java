package ai.commands.math;

import ai.AiNode;
import ai.commands.AiCommand;
import game.players.Player;

/**
 * 2/28/2015
 */
public class DeclCommand extends AiCommand {
    private int r1;
    public DeclCommand(int r1) {
        this.r1 = r1;
    }

    @Override
    public int run(Player player, int[] localData, boolean[] flags) {
        try {
            localData[r1]--;
        } catch (Exception e) {
            flags[AiNode.err] = true;
            flags[AiNode.halt] = true;
            return REPEAT;
        }
        return STEP;
    }

    @Override
    public String toString() {
        return "Decrement r"+r1;
    }
}
