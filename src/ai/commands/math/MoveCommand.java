package ai.commands.math;

import ai.AiNode;
import ai.commands.AiCommand;
import game.players.Player;

/**
 * 2/28/2015
 */
public class MoveCommand extends AiCommand {
    private int r1, r2;

    public MoveCommand(int r1, int r2) {
        this.r1 = r1;
        this.r2 = r2;
    }
    @Override
    public int run(Player player, int[] localData, boolean[] flags) {
        try {
            localData[r2] = localData[r1];
        } catch (Exception e) {
            flags[AiNode.err] = true;
            flags[AiNode.halt] = true;
            return HALT;
        }


        return STEP;
    }

    @Override
    public String toString() {
        return "Move r"+r1+" into r"+r2;
    }
}
