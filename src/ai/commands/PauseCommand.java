package ai.commands;

/**
 * 2/28/2015
 */
public class PauseCommand extends AiCommand {
    @Override
    public int run() {
        return WAIT;
    }

    @Override
    public String toString() {
        return "Pause";
    }
}
