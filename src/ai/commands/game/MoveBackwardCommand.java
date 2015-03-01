package ai.commands.game;

import ai.commands.AiCommand;
import game.players.Player;

/**
 * 2/28/2015
 */
public class MoveBackwardCommand extends AiCommand {
    @Override
    public int run(Player player, int[] localData, boolean[] flags) {
        player.moveBackwards();
        return WAIT;
    }

    @Override
    public String toString() {
        return "Move backwards";
    }
}
