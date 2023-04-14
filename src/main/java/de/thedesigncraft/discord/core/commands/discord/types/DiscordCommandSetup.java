package de.thedesigncraft.discord.core.commands.discord.types;

import de.thedesigncraft.discord.core.commands.discord.types.message.MessageCommandSetup;
import de.thedesigncraft.discord.core.commands.discord.types.slash.SlashCommandSetup;
import de.thedesigncraft.discord.core.commands.discord.types.slashanduser.SlashAndUserCommandSetup;
import de.thedesigncraft.discord.core.commands.discord.types.user.UserCommandSetup;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiscordCommandSetup {
    private final String name;
    private final IDiscordCommand.Type type;
    private final boolean messageAnswerProtectionEnabled;
    private final boolean global;
    private final List<Guild> guilds;
    private final String description;
    private final boolean guildOnly;
    private final List<Permission> requiredPermissions;

    /**
     * Creates a new {@link DiscordCommandSetup} with the given parameters
     *
     * @param name                       The name of the command
     * @param messageAnswerProtectionEnabled If the message answer protection is enabled
     * @param global                     If the command is global
     * @param guilds                     The guilds where the command is available
     * @param description                The description of the command
     * @param guildOnly                  If the command is only available in guilds
     * @param requiredPermissions        The required permissions to execute the command
     */
    public DiscordCommandSetup(@NotNull String name, boolean messageAnswerProtectionEnabled, boolean global, @Nullable List<Guild> guilds, @Nullable String description, boolean guildOnly, @Nullable List<Permission> requiredPermissions) {
        this.name = name;
        this.messageAnswerProtectionEnabled = messageAnswerProtectionEnabled;
        this.global = global;
        this.guilds = guilds;
        this.description = description;
        this.guildOnly = guildOnly;
        this.requiredPermissions = requiredPermissions;

        this.type = retreiveType(this);

    }

    /**
     * @return The name of the command
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * @return The type of the command
     */
    @NotNull
    public IDiscordCommand.Type getType() {
        return type;
    }

    /**
     * @return If the message answer protection is enabled
     */
    public boolean isMessageAnswerProtectionEnabled() {
        return messageAnswerProtectionEnabled;
    }

    /**
     * @return If the command is global
     */
    public boolean isGlobal() {
        return global;
    }

    /**
     * @return The guilds where the command is available
     */
    public List<Guild> getGuilds() {
        return guilds;
    }

    /**
     * @return The description of the command
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return If the command is only available in guilds
     */
    public boolean isGuildOnly() {
        return guildOnly;
    }

    /**
     * @return The required permissions to execute the command
     */
    public List<Permission> getRequiredPermissions() {
        return requiredPermissions;
    }

    private static IDiscordCommand.Type retreiveType(@NotNull DiscordCommandSetup commandSetup) {
        if (commandSetup instanceof SlashCommandSetup)
            return IDiscordCommand.Type.SLASH;

        if (commandSetup instanceof MessageCommandSetup)
            return IDiscordCommand.Type.MESSAGE;

        if (commandSetup instanceof UserCommandSetup)
            return IDiscordCommand.Type.USER;

        if (commandSetup instanceof SlashAndUserCommandSetup)
            return IDiscordCommand.Type.SLASH_AND_USER;

        return IDiscordCommand.Type.UNKNOWN;
    }

}
