package de.thedesigncraft.discord.botstuff.someMoreCommands.commands.console;

import de.thedesigncraft.discord.botstuff.essential.manage.Main;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.console.IConsoleCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import de.thedesigncraft.discord.botstuff.someMoreCommands.SomeMoreCommandsPackage;
import org.jetbrains.annotations.NotNull;

public class PingConsoleCommand implements IConsoleCommand {
    @Override
    public @NotNull String name() {
        return "Ping";
    }

    @Override
    public boolean activated() {
        return IStuffPackage.activatedStuffPackageClasses().contains(SomeMoreCommandsPackage.class);
    }

    @Override
    public boolean printLines() {
        return true;
    }

    @Override
    public void code() {

        System.out.println("Discord API Gateway:");
        System.out.println("| " + Main.jda.getGatewayPing() + "ms");

        System.out.println("Discord API REST:");
        System.out.println("| " + Main.jda.getRestPing().complete() + "ms");

    }
}
