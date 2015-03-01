package ai.commands.game;

import ai.commands.AiCommand;
import game.players.Player;

/**
 * 2/28/2015
 */
public class StrafeLeftCommand extends AiCommand {
    @Override
    public int run(Player player, int[] localData, boolean[] flags) {
        player.strafeLeft();
        return WAIT;
    }

    @Override
    public String toString() {
        return "StrafeLeft";
    }
}
