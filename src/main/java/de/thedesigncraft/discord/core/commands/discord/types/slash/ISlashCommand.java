package de.thedesigncraft.discord.core.commands.discord.types.slash;

import de.thedesigncraft.discord.core.ClassManager;
import de.thedesigncraft.discord.core.DBS;
import de.thedesigncraft.discord.core.commands.discord.events.DBSSlashCommandInteractionEvent;
import de.thedesigncraft.discord.core.commands.discord.types.IDiscordCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public interface ISlashCommand extends IDiscordCommand {

    /**
     * @return The {@link SlashCommandSetup} of the {@link ISlashCommand}
     */
    @NotNull
    SlashCommandSetup getSetup();

    /**
     * The code that should be executed when the command is called
     * @param event The command event
     */
    void execute(@NotNull DBSSlashCommandInteractionEvent event);

    /**
     * @return All registered slash commands
     */
    @NotNull
    static List<ISlashCommand> getSlashCommands() {
        List<ISlashCommand> returnList = new ArrayList<>();
        ClassManager.getInstantiatedClasses(DBS.mainPackage, ISlashCommand.class).forEach(o -> returnList.add((ISlashCommand) o));
        return returnList;
    }

    /**
     * @param name The name of the command
     * @return The {@link ISlashCommand} with the given name or null if no command with the given name was found
     */
    @Nullable
    static ISlashCommand getByName(@NotNull String name) {
        List<ISlashCommand> results = getSlashCommands().stream().filter(iSlashCommand -> iSlashCommand.getSetup().getName().equals(name)).toList();
        return results.size() > 0 ? results.get(0) : null;
    }

}
