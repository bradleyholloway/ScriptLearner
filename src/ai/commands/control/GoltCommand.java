package ai.commands.control;

import ai.AiNode;
import ai.commands.AiCommand;
import game.players.Player;

/**
 * 2/28/2015
 */
public class GoltCommand extends AiCommand {
    private int destination;
    public GoltCommand(int destination) {
        this.destination = destination;
    }

    @Override
    public int run(Player player, int[] localData, boolean[] flags) {
        return (flags[AiNode.lt]) ? destination : STEP;
    }

    @Override
    public String toString() {
        return "GOTO "+destination+" if the l flag is true";
    }
}
