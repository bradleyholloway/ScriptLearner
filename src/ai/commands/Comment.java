package ai.commands;

/**
 * 2/28/2015
 */
public class Comment extends AiCommand {
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    @Override
    public int run() {
        return 0;
    }

    @Override
    public String toString() {
        return comment;
    }
}
