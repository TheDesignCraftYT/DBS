package de.thedesigncraft.discord.core.commands.discord.types.slashanduser;

import de.thedesigncraft.discord.core.ClassManager;
import de.thedesigncraft.discord.core.DBS;
import de.thedesigncraft.discord.core.commands.discord.events.DBSSlashAndUserCommandInteractionEvent;
import de.thedesigncraft.discord.core.commands.discord.types.IDiscordCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public interface ISlashAndUserCommand extends IDiscordCommand {

    /**
     * @return The setup of the command
     */
    @NotNull
    SlashAndUserCommandSetup getSetup();

    /**
     * The code that should be executed when the command is called
     * @param event The command event
     */
    void execute(@NotNull DBSSlashAndUserCommandInteractionEvent event);

    /**
     * @return All registered slash and user commands
     */
    @NotNull
    static List<ISlashAndUserCommand> getSlashAndUserCommands() {
        List<ISlashAndUserCommand> returnList = new ArrayList<>();
        ClassManager.getInstantiatedClasses(DBS.mainPackage, ISlashAndUserCommand.class).forEach(o -> returnList.add((ISlashAndUserCommand) o));
        return returnList;
    }

    /**
     * @param name The name of the command
     * @return The command with the given name or null if no command with the given name was found
     */
    @Nullable
    static ISlashAndUserCommand getByName(@NotNull String name) {
        List<ISlashAndUserCommand> results = getSlashAndUserCommands().stream().filter(iSlashAndUserCommand -> iSlashAndUserCommand.getSetup().getName().equals(name)).toList();
        return results.size() > 0 ? results.get(0) : null;
    }

}
