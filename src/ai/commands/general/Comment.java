package ai.commands.general;

import ai.commands.AiCommand;

/**
 * 2/28/2015
 */
public class Comment extends AiCommand {
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }


    @Override
    public int run(int[] localData, boolean[] flags) {
        return STEP;
    }

    @Override
    public String toString() {
        return comment;
    }
}
