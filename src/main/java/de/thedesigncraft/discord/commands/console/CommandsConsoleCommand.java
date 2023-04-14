package de.thedesigncraft.discord.commands.console;

import de.thedesigncraft.discord.core.commands.console.IConsoleCommand;
import org.jetbrains.annotations.NotNull;

/**
 * Replies with all console commands
 */
public class CommandsConsoleCommand implements IConsoleCommand {

    @Override
    public @NotNull String getName() {
        return "Commands";
    }

    @Override
    public boolean isActivated() {
        return true;
    }

    @Override
    public boolean needsLines() {
        return true;
    }
    @NotNull
    @Override
    public String execute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("All console commands:");
        IConsoleCommand.getActivatedConsoleCommandsMap().forEach((s, iConsoleCommand) -> stringBuilder.append("\n| - ").append(iConsoleCommand.getName().toLowerCase()));
        return stringBuilder.toString();
    }
}
