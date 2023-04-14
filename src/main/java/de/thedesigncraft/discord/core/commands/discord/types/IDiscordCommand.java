package de.thedesigncraft.discord.core.commands.discord.types;

import de.thedesigncraft.discord.core.ClassManager;
import de.thedesigncraft.discord.core.DBS;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface IDiscordCommand {

    /**
     * @return The setup of the command
     */
    @NotNull
    DiscordCommandSetup getSetup();

    /**
     * @return All registered discord commands
     */
    @NotNull
    static List<IDiscordCommand> getDiscordCommands() {
        List<IDiscordCommand> returnList = new ArrayList<>();
        ClassManager.getInstantiatedClasses(DBS.mainPackage, IDiscordCommand.class).forEach(o -> returnList.add((IDiscordCommand) o));
        return returnList;
    }

    /**
     * All types of discord commands
     */
    enum Type {
        SLASH,
        USER,
        MESSAGE,
        SLASH_AND_USER,
        UNKNOWN
    }

}
