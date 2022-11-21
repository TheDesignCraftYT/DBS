package de.thedesigncraft.discord.botstuff.someMoreCommands.commands.console;

import de.thedesigncraft.discord.botstuff.essential.manage.Main;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.console.IConsoleCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import de.thedesigncraft.discord.botstuff.someMoreCommands.SomeMoreCommandsPackage;
import net.dv8tion.jda.api.interactions.commands.Command;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DiscordCommandsConsoleCommand implements IConsoleCommand {

    @Override
    public @NotNull String name() {
        return "DiscordCommands";
    }

    @Override
    public boolean activated() {
        return IStuffPackage.activatedStuffPackageClasses().contains(SomeMoreCommandsPackage.class);
    }

    @Override
    public boolean printLines() {
        return false;
    }

    @Override
    public void code() {

        List<Command> commandsToPrint = Main.jda.retrieveCommands().complete();

        System.out.println("--------------------");

        commandsToPrint.forEach(command -> System.out.println("| - " + command.getName()));

        System.out.println("--------------------");

        Main.jda.getGuilds().get(0).retrieveCommands().queue(System.out::println);

    }
}
