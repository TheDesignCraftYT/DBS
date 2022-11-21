package de.thedesigncraft.discord.botstuff.essential.manage.commands.console;

import de.thedesigncraft.discord.botstuff.essential.manage.Manager;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface IConsoleCommand {

    @NotNull
    String name();

    boolean activated();

    boolean printLines();

    void code();

    @NotNull
    static Map<String, IConsoleCommand> getConsoleCommands() {

        Map<String, IConsoleCommand> returnMap = new ConcurrentHashMap<>();

        Manager.getConsoleCommands().forEach(consoleCommand -> {

            returnMap.put(consoleCommand.name().toLowerCase(), consoleCommand);

        });

        return returnMap;

    }

    @NotNull
    static Map<String, IConsoleCommand> getActivatedConsoleCommands() {

        Map<String, IConsoleCommand> returnMap = new ConcurrentHashMap<>();

        getConsoleCommands().forEach((s, iConsoleCommand) -> {

            if (iConsoleCommand.activated())
                returnMap.put(s, iConsoleCommand);

        });

        return returnMap;

    }

}
