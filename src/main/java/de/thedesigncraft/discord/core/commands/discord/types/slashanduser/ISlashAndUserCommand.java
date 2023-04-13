package de.thedesigncraft.discord.core.commands.discord.types.slashanduser;

import de.thedesigncraft.discord.manage.ClassManager;
import de.thedesigncraft.discord.manage.DBS;
import de.thedesigncraft.discord.manage.commands.discord.events.DBSSlashAndUserCommandInteractionEvent;
import de.thedesigncraft.discord.manage.commands.discord.types.IDiscordCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public interface ISlashAndUserCommand extends IDiscordCommand {

    @NotNull
    SlashAndUserCommandSetup getSetup();

    void execute(@NotNull DBSSlashAndUserCommandInteractionEvent event);

    @NotNull
    static List<ISlashAndUserCommand> getSlashAndUserCommands() {
        List<ISlashAndUserCommand> returnList = new ArrayList<>();
        ClassManager.getInstantiatedClasses(DBS.mainPackage, ISlashAndUserCommand.class).forEach(o -> returnList.add((ISlashAndUserCommand) o));
        return returnList;
    }

    @Nullable
    static ISlashAndUserCommand getByName(@NotNull String name) {
        List<ISlashAndUserCommand> results = getSlashAndUserCommands().stream().filter(iSlashAndUserCommand -> iSlashAndUserCommand.getSetup().getName().equals(name)).toList();
        return results.size() > 0 ? results.get(0) : null;
    }

}
