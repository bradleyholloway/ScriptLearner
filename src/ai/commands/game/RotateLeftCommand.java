package ai.commands.game;

import ai.commands.AiCommand;
import game.players.Player;

/**
 * 2/28/2015
 */
public class RotateLeftCommand extends AiCommand {
    @Override
    public int run(Player player, int[] localData, boolean[] flags) {
        player.rotateLeft();
        return WAIT;
    }

    @Override
    public String toString() {
        return "Rotate Left";
    }
}
