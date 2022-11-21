package de.thedesigncraft.discord.botstuff.essential.commands.console;

import de.thedesigncraft.discord.botstuff.essential.manage.Main;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.console.IConsoleCommand;
import org.jetbrains.annotations.NotNull;

public class ShutdownConsoleCommand implements IConsoleCommand {
    @Override
    public @NotNull String name() {
        return "Shutdown";
    }

    @Override
    public boolean activated() {
        return true;
    }

    @Override
    public boolean printLines() {
        return false;
    }

    @Override
    public void code() {

        Main.shutdown();

    }
}
