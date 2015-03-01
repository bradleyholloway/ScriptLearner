package ai.writeutils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 2/28/2015
 */
public class WriteUtil {
    private static ArrayList<CommandTemplate> commandTemplates;
    private static ArrayList<CommandTemplate> actionTemplates;

    public static ArrayList<CommandTemplate> getCommandTemplates() {
        if (commandTemplates == null) {
            commandTemplates = new ArrayList<CommandTemplate>();
            CommandTemplate.populateTemplates();
        }
        return commandTemplates;
    }
    public static ArrayList<CommandTemplate> getActionTemplates() {
        if (actionTemplates == null) {
            actionTemplates = new ArrayList<CommandTemplate>();
            CommandTemplate.populateTemplates();
        }
        return actionTemplates;
    }
}
