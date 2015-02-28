package ai.commands.math;

import ai.AiNode;
import ai.commands.AiCommand;

/**
 * 2/28/2015
 */
public class CompareCommand extends AiCommand {
    int r1,r2;
    public CompareCommand(int r1, int r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public int run(int[] localData, boolean[] flags) {
        try {
            int temp = localData[r1] - localData[r2];
            if (temp == 0) {
                flags[AiNode.zero] = true;
                flags[AiNode.gt] = false;
                flags[AiNode.lt] = false;
            } else if (temp > 0) {
                flags[AiNode.zero] = false;
                flags[AiNode.gt] = true;
                flags[AiNode.lt] = false;
            } else if (temp < 0) {
                flags[AiNode.zero] = false;
                flags[AiNode.gt] = false;
                flags[AiNode.lt] = true;
            }
        } catch (Exception e) {
            flags[AiNode.err] = true;
            flags[AiNode.halt] = true;
            return REPEAT;
        }
        return AiCommand.STEP;
    }

    @Override
    public String toString() {
        return "Compare r"+r1+" r"+r2;
    }
}
