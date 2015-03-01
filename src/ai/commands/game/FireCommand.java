package ai.commands.game;

import ai.commands.AiCommand;
import game.players.Player;

/**
 * 2/28/2015
 */
public class FireCommand extends AiCommand {
    @Override
    public int run(Player player, int[] localData, boolean[] flags) {
        if (player.fire()) {
            return WAIT;
        }
        return REPEAT;
    }

    @Override
    public String toString() {
        return "Fire!";
    }
}
