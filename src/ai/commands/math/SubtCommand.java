package ai.commands.math;

import ai.AiNode;
import ai.commands.AiCommand;

/**
 * 2/28/2015
 */
public class SubtCommand extends AiCommand{
    private int r1, r2;

    public SubtCommand(int r1, int r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public int run(int[] localData, boolean[] flags) {
        try {
            localData[r2] -= localData[r1];
        } catch (Exception e) {
            flags[AiNode.err] = true;
            flags[AiNode.halt] = true;
            return REPEAT;
        }

        return STEP;
    }

    @Override
    public String toString() {
        return "Subtract r"+r1+" from r"+r2;
    }
}
