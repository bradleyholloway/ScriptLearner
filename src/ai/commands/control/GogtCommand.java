package ai.commands.control;

import ai.AiNode;
import ai.commands.AiCommand;
import game.players.Player;

/**
 * 2/28/2015
 */
public class GogtCommand extends AiCommand {
    private int destination;
    public GogtCommand(int destination) {
        this.destination = destination;
    }

    @Override
    public int run(Player player, int[] localData, boolean[] flags) {
        return (flags[AiNode.gt]) ? destination : STEP;
    }

    @Override
    public String toString() {
        return "GOTO "+destination+" if the g flag is true";
    }
}
