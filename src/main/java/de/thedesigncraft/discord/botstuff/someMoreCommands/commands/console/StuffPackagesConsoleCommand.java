package de.thedesigncraft.discord.botstuff.someMoreCommands.commands.console;

import de.thedesigncraft.discord.botstuff.essential.manage.Manager;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.console.IConsoleCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import de.thedesigncraft.discord.botstuff.someMoreCommands.SomeMoreCommandsPackage;
import org.jetbrains.annotations.NotNull;

public class StuffPackagesConsoleCommand implements IConsoleCommand {

    @Override
    public @NotNull String name() {
        return "StuffPackages";
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

        Manager.getStuffPackages().forEach(iStuffPackage -> System.out.println("| - " + iStuffPackage.name().toLowerCase()));

    }

}
