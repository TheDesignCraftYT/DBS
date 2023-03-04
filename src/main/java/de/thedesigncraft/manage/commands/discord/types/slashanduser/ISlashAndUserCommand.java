package de.thedesigncraft.manage.commands.discord.types.slashanduser;

import de.thedesigncraft.manage.commands.discord.types.IDiscordCommand;
import org.jetbrains.annotations.NotNull;

public interface ISlashAndUserCommand extends IDiscordCommand {

    @NotNull
    SlashAndUserCommandSetup getSetup();

}
