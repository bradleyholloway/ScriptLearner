package ai.commands.general;

import ai.commands.AiCommand;

/**
 * 2/28/2015
 */
public class HaltCommand extends AiCommand {
    @Override
    public int run(int[] localData, boolean[] flags) {
        return HALT;
    }

    @Override
    public String toString() {
        return "HALT";
    }
}
