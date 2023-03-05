package de.thedesigncraft.discord.manage.commands.discord.types;

import de.thedesigncraft.discord.manage.ClassManager;
import de.thedesigncraft.discord.manage.DBS;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface IDiscordCommand {

    @NotNull
    DiscordCommandSetup getSetup();

    @NotNull
    static List<IDiscordCommand> getDiscordCommands() {
        List<IDiscordCommand> returnList = new ArrayList<>();
        ClassManager.getInstantiatedClasses(DBS.mainPackage, IDiscordCommand.class).forEach(o -> returnList.add((IDiscordCommand) o));
        return returnList;
    }

    enum Type {
        SLASH,
        USER,
        MESSAGE,
        SLASH_AND_USER,
        UNKNOWN
    }

}
