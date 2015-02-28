package ai.commands;

/**
 * 2/28/2015
 */
public class GotoCommand extends AiCommand{
    private int destinationLine;
    public GotoCommand(int destination) {
        this.destinationLine = destination;
    }

    @Override
    public int run() {
        return destinationLine;
    }

    @Override
    public String toString() {
        return "Goto "+destinationLine;
    }
}
