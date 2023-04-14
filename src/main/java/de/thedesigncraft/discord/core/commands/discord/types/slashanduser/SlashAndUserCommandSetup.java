package de.thedesigncraft.discord.core.commands.discord.types.slashanduser;

import de.thedesigncraft.discord.core.commands.discord.types.DiscordCommandSetup;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SlashAndUserCommandSetup extends DiscordCommandSetup {

    /**
     * The option description of the slash command
     */
    private final String slashCommandOptionDescription;

    /**
     * Creates a new {@link SlashAndUserCommandSetup} with the given parameters
     *
     * @param name                       The name of the command
     * @param messageAnswerProtectionEnabled If the message answer protection is enabled
     * @param global                     If the command is global
     * @param guilds                     The guilds where the command is available
     * @param description                The description of the command
     * @param guildOnly                  If the command is only available in guilds
     * @param requiredPermissions        The required permissions to execute the command
     * @param slashCommandOptionDescription The option description of the slash command
     */
    SlashAndUserCommandSetup(@NotNull String name, boolean messageAnswerProtectionEnabled, boolean global, @Nullable List<Guild> guilds, @NotNull String description, boolean guildOnly, @Nullable List<Permission> requiredPermissions, @NotNull String slashCommandOptionDescription) {
        super(name, messageAnswerProtectionEnabled, global, guilds, description, guildOnly, requiredPermissions);
        this.slashCommandOptionDescription = slashCommandOptionDescription;
    }

    /**
     * @return The option description of the slash command
     */
    public String getSlashCommandOptionDescription() {
        return slashCommandOptionDescription;
    }

}
