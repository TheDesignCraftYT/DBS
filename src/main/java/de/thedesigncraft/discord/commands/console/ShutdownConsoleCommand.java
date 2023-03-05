package de.thedesigncraft.discord.commands.console;

import de.thedesigncraft.discord.manage.DBS;
import de.thedesigncraft.discord.manage.commands.console.IConsoleCommand;
import org.jetbrains.annotations.NotNull;

public class ShutdownConsoleCommand implements IConsoleCommand {
    @Override
    public @NotNull String getName() {
        return "Shutdown";
    }

    @Override
    public boolean isActivated() {
        return true;
    }

    @Override
    public boolean needsLines() {
        return false;
    }

    @Override
    public void execute() {
        DBS.shutdown();
    }
}