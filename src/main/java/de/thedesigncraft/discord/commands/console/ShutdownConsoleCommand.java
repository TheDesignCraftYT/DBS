package de.thedesigncraft.discord.commands.console;

import de.thedesigncraft.discord.core.DBS;
import de.thedesigncraft.discord.core.commands.console.IConsoleCommand;
import org.jetbrains.annotations.NotNull;

/**
 * Shutdowns the bot
 */
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

    @NotNull
    @Override
    public String execute() {
        DBS.shutdown();
        return "";
    }
}
