package de.thedesigncraft.discord.core.commands.console;

import de.thedesigncraft.discord.manage.ClassManager;
import de.thedesigncraft.discord.manage.DBS;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface IConsoleCommand {

    @NotNull
    String getName();

    boolean isActivated();

    boolean needsLines();

    void execute();

    @NotNull
    static Map<String, IConsoleCommand> getConsoleCommandsMap() {
        Map<String, IConsoleCommand> returnMap = new ConcurrentHashMap<>();
        getConsoleCommands().forEach(consoleCommand -> returnMap.put(consoleCommand.getName().toLowerCase(), consoleCommand));
        return returnMap;
    }

    @NotNull
    static Map<String, IConsoleCommand> getActivatedConsoleCommandsMap() {
        Map<String, IConsoleCommand> returnMap = new ConcurrentHashMap<>();
        getConsoleCommands().stream().filter(IConsoleCommand::isActivated).forEach(consoleCommand -> returnMap.put(consoleCommand.getName().toLowerCase(), consoleCommand));
        return returnMap;
    }

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
