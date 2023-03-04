package de.thedesigncraft.manage.commands.console;

import org.jetbrains.annotations.NotNull;

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

    @Override
    public void execute() {
        System.out.println("All console commands:");
        IConsoleCommand.getActivatedConsoleCommandsMap().forEach((s, iConsoleCommand) -> System.out.println("| - " + iConsoleCommand.getName().toLowerCase()));
    }
}
