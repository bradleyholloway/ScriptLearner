package ai.commands;

/**
 * 2/28/2015
 */
public class HaltCommand extends AiCommand {
    @Override
    public int run() {
        return HALT;
    }

    @Override
    public String toString() {
        return "HALT";
    }
}
