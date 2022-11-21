package de.thedesigncraft.discord.botstuff.essential.commands.console;

import de.thedesigncraft.discord.botstuff.essential.manage.commands.console.IConsoleCommand;
import org.jetbrains.annotations.NotNull;

public class CommandsConsoleCommand implements IConsoleCommand {

    @Override
    public @NotNull String name() {
        return "Commands";
    }

    @Override
    public boolean activated() {
        return true;
    }

    @Override
    public boolean printLines() {
        return true;
    }

    @Override
    public void code() {

        IConsoleCommand.getActivatedConsoleCommands().forEach((s, iConsoleCommand) -> System.out.println("| - " + iConsoleCommand.name().toLowerCase()));

    }
}
