package ai.commands.game;

import ai.commands.AiCommand;
import game.players.Player;

/**
 * 2/28/2015
 */
public class StrafeRightCommand extends AiCommand {
    @Override
    public int run(Player player, int[] localData, boolean[] flags) {
        player.strafeRight();
        return WAIT;
    }

    @Override
    public String toString() {
        return "Strafe right";
    }
}
