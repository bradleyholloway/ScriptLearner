package ai.commands.general;

import ai.commands.AiCommand;

/**
 * 2/28/2015
 */
public class NoOpCommand extends AiCommand {
    @Override
    public int run(int[] localData, boolean[] flags) {
        return STEP;
    }

    @Override
    public String toString() {
        return "NoOp";
    }
}
