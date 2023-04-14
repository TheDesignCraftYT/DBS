package de.thedesigncraft.discord.core.commands.console;

import de.thedesigncraft.discord.core.ClassManager;
import de.thedesigncraft.discord.core.DBS;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface IConsoleCommand {

    /**
     * @return The name of the command
     */
    @NotNull
    String getName();

    /**
     * @return If the command is activated / can be used.
     */
    boolean isActivated();

    /**
     * @return If the command needs lines around it in the console, when executed.
     */
    boolean needsLines();

    /**
     * @return The String that will be printed in the console, when the command is executed.
     */
    @NotNull
    String execute();

    /**
     * @return A Map with all console commands, with the name as key and the command as value.
     */
    @NotNull
    static Map<String, IConsoleCommand> getConsoleCommandsMap() {
        Map<String, IConsoleCommand> returnMap = new ConcurrentHashMap<>();
        getConsoleCommands().forEach(consoleCommand -> returnMap.put(consoleCommand.getName().toLowerCase(), consoleCommand));
        return returnMap;
    }

    /**
     * @return A Map with all activated console commands, with the name as key and the command as value.
     */
    @NotNull
    static Map<String, IConsoleCommand> getActivatedConsoleCommandsMap() {
        Map<String, IConsoleCommand> returnMap = new ConcurrentHashMap<>();
        getConsoleCommands().stream().filter(IConsoleCommand::isActivated).forEach(consoleCommand -> returnMap.put(consoleCommand.getName().toLowerCase(), consoleCommand));
        return returnMap;
    }

    /**
     * @return A List with all console commands.
     */
    @NotNull
    static List<IConsoleCommand> getConsoleCommands() {
        List<IConsoleCommand> returnList = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();
        ClassManager.getInstantiatedClasses(DBS.mainPackage, IConsoleCommand.class).stream().filter(o -> !classes.contains(o.getClass())).forEach(o -> {
            classes.add(o.getClass());
            returnList.add((IConsoleCommand) o);
        });
        ClassManager.getInstantiatedClasses(DBS.dbsPackage, IConsoleCommand.class).stream().filter(o -> !classes.contains(o.getClass())).forEach(o -> {
            classes.add(o.getClass());
            returnList.add((IConsoleCommand) o);
        });
        return returnList;
    }

}
