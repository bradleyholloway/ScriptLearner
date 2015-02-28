package ai.commands;

/**
 * 2/28/2015
 */
public abstract class AiCommand {
    public static final int HALT = -3;
    public static final int WAIT = -2;
    public static final int REPEAT = -1;
    public static final int STEP = 0;

    public abstract int run(int[] localData, boolean[] flags);
    public abstract String toString();
}
