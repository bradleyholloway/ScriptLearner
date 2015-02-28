package ai.commands;

/**
 * 2/28/2015
 */
public class NoOp extends AiCommand{
    @Override
    public int run() {
        return STEP;
    }

    @Override
    public String toString() {
        return "NoOp";
    }
}
