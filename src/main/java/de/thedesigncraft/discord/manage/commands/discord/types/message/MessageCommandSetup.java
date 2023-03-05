package de.thedesigncraft.discord.manage.commands.discord.types.message;

import de.thedesigncraft.discord.manage.commands.discord.types.DiscordCommandSetup;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MessageCommandSetup extends DiscordCommandSetup {

    public MessageCommandSetup(@NotNull String name, boolean messageAnswerProtectionEnabled, boolean global, @Nullable List<Guild> guilds, boolean guildOnly, @Nullable List<Permission> requiredPermissions) {
        super(name, messageAnswerProtectionEnabled, global, guilds, null, guildOnly, requiredPermissions);
    }

}
