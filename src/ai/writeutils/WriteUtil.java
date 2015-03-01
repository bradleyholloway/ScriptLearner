package ai.writeutils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 2/28/2015
 */
public class WriteUtil {
    private static ArrayList<CommandTemplate> commandTemplates;

    public static ArrayList<CommandTemplate> getCommandTemplates() {
        if (commandTemplates == null) {
            commandTemplates = new ArrayList<CommandTemplate>();
            CommandTemplate.populateTemplates();
        }
        return commandTemplates;
    }
}
