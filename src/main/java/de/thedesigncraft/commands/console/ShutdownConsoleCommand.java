package de.thedesigncraft.commands.console;

import de.thedesigncraft.manage.DBS;
import de.thedesigncraft.manage.commands.console.IConsoleCommand;
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
