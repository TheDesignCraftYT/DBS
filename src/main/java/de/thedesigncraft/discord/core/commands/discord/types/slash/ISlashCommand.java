package de.thedesigncraft.discord.core.commands.discord.types.slash;

import de.thedesigncraft.discord.manage.ClassManager;
import de.thedesigncraft.discord.manage.DBS;
import de.thedesigncraft.discord.manage.commands.discord.events.DBSSlashCommandInteractionEvent;
import de.thedesigncraft.discord.manage.commands.discord.types.IDiscordCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public interface ISlashCommand extends IDiscordCommand {

    @NotNull
    SlashCommandSetup getSetup();

    void execute(@NotNull DBSSlashCommandInteractionEvent event);

    @NotNull
    static List<ISlashCommand> getSlashCommands() {
        List<ISlashCommand> returnList = new ArrayList<>();
        ClassManager.getInstantiatedClasses(DBS.mainPackage, ISlashCommand.class).forEach(o -> returnList.add((ISlashCommand) o));
        return returnList;
    }

    @Nullable
    static ISlashCommand getByName(@NotNull String name) {
        List<ISlashCommand> results = getSlashCommands().stream().filter(iSlashCommand -> iSlashCommand.getSetup().getName().equals(name)).toList();
        return results.size() > 0 ? results.get(0) : null;
    }

}
