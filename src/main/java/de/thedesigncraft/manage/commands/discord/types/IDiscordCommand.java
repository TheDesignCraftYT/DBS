package de.thedesigncraft.manage.commands.discord.types;

import de.thedesigncraft.manage.ClassManager;
import de.thedesigncraft.manage.DBS;
import de.thedesigncraft.manage.commands.discord.events.DBSCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface IDiscordCommand {

    @NotNull
    DiscordCommandSetup getSetup();

    void execute(@NotNull DBSCommandInteractionEvent event);

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
