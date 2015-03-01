package ai.commands.general;

import ai.commands.AiCommand;
import game.players.Player;

/**
 * 2/28/2015
 */
public class NoOpCommand extends AiCommand {
    @Override
    public int run(Player player, int[] localData, boolean[] flags) {
        return STEP;
    }

    @Override
    public String toString() {
        return "NoOp";
    }
}
