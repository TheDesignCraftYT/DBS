package de.thedesigncraft.discord.core.commands.discord.types.user;

import de.thedesigncraft.discord.core.commands.discord.types.DiscordCommandSetup;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UserCommandSetup extends DiscordCommandSetup {

    /**
     * Creates a new {@link UserCommandSetup} with the given parameters
     *
     * @param name                       The name of the command
     * @param messageAnswerProtectionEnabled If the message answer protection is enabled
     * @param global                     If the command is global
     * @param guilds                     The guilds where the command is available
     * @param guildOnly                  If the command is only available in guilds
     * @param requiredPermissions        The required permissions to execute the command
     */
    public UserCommandSetup(@NotNull String name, boolean messageAnswerProtectionEnabled, boolean global, @Nullable List<Guild> guilds, boolean guildOnly, @Nullable List<Permission> requiredPermissions) {
        super(name, messageAnswerProtectionEnabled, global, guilds, null, guildOnly, requiredPermissions);
    }

}
