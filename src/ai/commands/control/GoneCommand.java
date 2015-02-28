package ai.commands.control;

import ai.AiNode;
import ai.commands.AiCommand;

/**
 * 2/28/2015
 */
public class GoneCommand extends AiCommand {
    private int destination;
    public GoneCommand(int destination) {
        this.destination = destination;
    }

    @Override
    public int run(int[] localData, boolean[] flags) {
        return (flags[AiNode.zero]) ? STEP : destination;
    }

    @Override
    public String toString() {
        return "GOTO "+destination+" if the z flag is false";
    }
}
