package ai.commands.general;

import ai.commands.AiCommand;
import game.players.Player;

/**
 * 2/28/2015
 */
public class GotoCommand extends AiCommand {
    private int destinationLine;
    public GotoCommand(int destination) {
        this.destinationLine = destination;
    }

    @Override
    public int run(Player player, int[] localData, boolean[] flags) {
        return destinationLine;
    }

    @Override
    public String toString() {
        return "Goto "+destinationLine;
    }
}
