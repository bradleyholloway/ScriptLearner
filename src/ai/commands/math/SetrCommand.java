package ai.commands.math;

import ai.AiNode;
import ai.commands.AiCommand;
import game.players.Player;

/**
 * 2/28/2015
 */
public class SetrCommand extends AiCommand {
    private int r1, v1;

    public SetrCommand(int r1, int v1){
        this.r1 = r1;
        this.v1 = v1;
    }

    @Override
    public int run(Player player, int[] localData, boolean[] flags) {
        try {
            localData[r1] = v1;
        } catch (Exception e) {
            flags[AiNode.err] = true;
            flags[AiNode.halt] = true;
            return REPEAT;
        }
        return STEP;
    }

    @Override
    public String toString() {
        return "Assign "+v1+" to r"+r1;
    }
}
