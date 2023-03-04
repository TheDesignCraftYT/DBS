package de.thedesigncraft.manage.commands.discord.types.user;

import de.thedesigncraft.manage.commands.discord.types.DiscordCommandSetup;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UserCommandSetup extends DiscordCommandSetup {

    public UserCommandSetup(@NotNull String name, boolean messageAnswerProtectionEnabled, boolean global, @Nullable List<Guild> guilds, boolean guildOnly, @Nullable List<Permission> requiredPermissions) {
        super(name, messageAnswerProtectionEnabled, global, guilds, null, guildOnly, requiredPermissions);
    }

}
