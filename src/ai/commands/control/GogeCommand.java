package ai.commands.control;

import ai.AiNode;
import ai.commands.AiCommand;

/**
 * 2/28/2015
 */
public class GogeCommand extends AiCommand {
    private int destination;
    public GogeCommand(int destination) {
        this.destination = destination;
    }

    @Override
    public int run(int[] localData, boolean[] flags) {
        return (!flags[AiNode.lt]) ? destination : STEP;
    }

    @Override
    public String toString() {
        return "GOTO "+destination+" if the l flag is false";
    }
}
