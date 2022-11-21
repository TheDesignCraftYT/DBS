package de.thedesigncraft.discord.botstuff.essential.manage.commands.discord;

import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage.DiscordCommands;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage.categories.CommandCategory;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.ISlashCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.versions.Version;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public interface IDiscordCommand {

    @NotNull
    String name();

    @NotNull
    String version();

    @NotNull
    String category();

    @NotNull
    String description();

    boolean globalCommand();

    @Nullable
    List<Guild> guilds();

    boolean guildOnly();

    @NotNull
    Emoji commandEmoji();

    @Nullable
    List<Permission> requiredPermissions();

    @Nullable
    static List<IDiscordCommand> getByCategory(@NotNull CommandCategory category) {
        if (category.isEmpty())
            return null;

        return DiscordCommands.registeredDiscordCommands.values().stream().filter(iDiscordCommand -> iDiscordCommand.category().equals(category.getName())).collect(Collectors.toList());
    }

    static List<IDiscordCommand> getByVersion(Version version) {
        if (version.isEmpty())
            return null;

        return DiscordCommands.registeredDiscordCommands.values().stream().filter(iDiscordCommand -> iDiscordCommand.version().equals(version.getName())).collect(Collectors.toList());
    }

    @NotNull
    static Type getType(IDiscordCommand iDiscordCommand) {

        if (iDiscordCommand instanceof ISlashCommand)
            return Type.SLASH;

        if (iDiscordCommand instanceof IUserContextMenu)
            return Type.USER;

        if (iDiscordCommand instanceof IMessageContextMenu)
            return Type.MESSAGE;

        return Type.UNKNOWN;

    }

    enum Type {

        SLASH,
        USER,
        MESSAGE,
        UNKNOWN

    }

}
