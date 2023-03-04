package de.thedesigncraft.dbs.manage.commands.discord.types;

import de.thedesigncraft.dbs.manage.commands.discord.types.message.MessageCommandSetup;
import de.thedesigncraft.dbs.manage.commands.discord.types.slash.SlashCommandSetup;
import de.thedesigncraft.dbs.manage.commands.discord.types.slashanduser.SlashAndUserCommandSetup;
import de.thedesigncraft.dbs.manage.commands.discord.types.user.UserCommandSetup;
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

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public IDiscordCommand.Type getType() {
        return type;
    }

    public boolean isMessageAnswerProtectionEnabled() {
        return messageAnswerProtectionEnabled;
    }

    public boolean isGlobal() {
        return global;
    }

    public List<Guild> getGuilds() {
        return guilds;
    }

    public String getDescription() {
        return description;
    }

    public boolean isGuildOnly() {
        return guildOnly;
    }

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
