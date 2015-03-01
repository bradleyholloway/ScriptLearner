package ai.writeutils;

import ai.commands.math.CompareCommand;

import java.util.ArrayList;

/**
 * 2/28/2015
 */
public class CommandTemplate {

    public static enum ArgType {
        INT, LABEL, REGISTER
    }

    protected static void populateTemplates() {
        ArrayList<CommandTemplate> templates = WriteUtil.getCommandTemplates();
        ArrayList<CommandTemplate> actionTemplates = WriteUtil.getActionTemplates();
        templates.clear();
        actionTemplates.clear();
        templates.add(new CommandTemplate("labl l"));
        templates.add(new CommandTemplate("goge l"));
        templates.add(new CommandTemplate("gogt l"));
        templates.add(new CommandTemplate("goie l"));
        templates.add(new CommandTemplate("gole l"));
        templates.add(new CommandTemplate("golt l"));
        templates.add(new CommandTemplate("gone l"));
        templates.add(new CommandTemplate("goto l"));
        templates.add(new CommandTemplate("noop"));
        templates.add(new CommandTemplate("paus"));
        templates.add(new CommandTemplate("halt"));
        templates.add(new CommandTemplate("addl r r"));
        templates.add(new CommandTemplate("comp r r"));
        templates.add(new CommandTemplate("decl r"));
        templates.add(new CommandTemplate("incl r"));
        templates.add(new CommandTemplate("movl r r"));
        templates.add(new CommandTemplate("mult r r"));
        templates.add(new CommandTemplate("setr r i"));
        templates.add(new CommandTemplate("subl r r"));
        templates.add(new CommandTemplate("fire"));
        templates.add(new CommandTemplate("movb"));
        templates.add(new CommandTemplate("movf"));
        templates.add(new CommandTemplate("reld"));
        templates.add(new CommandTemplate("rotr"));
        templates.add(new CommandTemplate("rotl"));
        templates.add(new CommandTemplate("strl"));
        templates.add(new CommandTemplate("strr"));
        templates.add(new CommandTemplate("rand r"));

        actionTemplates.add(new CommandTemplate("fire"));
        actionTemplates.add(new CommandTemplate("movb"));
        actionTemplates.add(new CommandTemplate("movf"));
        actionTemplates.add(new CommandTemplate("reld"));
        actionTemplates.add(new CommandTemplate("rotr"));
        actionTemplates.add(new CommandTemplate("rotl"));
        actionTemplates.add(new CommandTemplate("strl"));
        actionTemplates.add(new CommandTemplate("strr"));
    }


    private ArrayList<ArgType> arguments;
    private String command;
    public CommandTemplate(String format) {
        //format should be of form "comd args..."
        //args can be in the form of 'i' 'l' or 'r'
        arguments = new ArrayList<ArgType>();
        command = format.substring(0,Math.min(4,format.length()));
        String[] params = format.substring(Math.min(5,format.length())).split(" ");
        for(String a : params) {
            if (a.equals("i")) {
                arguments.add(ArgType.INT);
            } else if (a.equals("r")) {
                arguments.add(ArgType.REGISTER);
            } else if (a.equals("l")) {
                arguments.add(ArgType.LABEL);
            }
        }
    }
    public String getCommand() {
        return command;
    }
    public ArrayList<ArgType> getArguments() {
        return arguments;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(command);
        for(ArgType at : arguments) {
            s.append(" "+at);
        }
        return s.toString();
    }

    public String applyArguments(String... args) {
        StringBuilder result = new StringBuilder();
        result.append(command);
        for (int i = 0; i < arguments.size(); i++) {
            result.append(" "+args[i]);
        }
        return result.toString();
    }
}
