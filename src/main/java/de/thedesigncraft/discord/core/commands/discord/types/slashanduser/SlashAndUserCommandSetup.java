package de.thedesigncraft.discord.core.commands.discord.types.slashanduser;

import de.thedesigncraft.discord.manage.commands.discord.types.DiscordCommandSetup;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SlashAndUserCommandSetup extends DiscordCommandSetup {

    private final String slashCommandOptionDescription;

    SlashAndUserCommandSetup(@NotNull String name, boolean messageAnswerProtectionEnabled, boolean global, @Nullable List<Guild> guilds, @NotNull String description, boolean guildOnly, @Nullable List<Permission> requiredPermissions, @NotNull String slashCommandOptionDescription) {
        super(name, messageAnswerProtectionEnabled, global, guilds, description, guildOnly, requiredPermissions);
        this.slashCommandOptionDescription = slashCommandOptionDescription;
    }

    public String getSlashCommandOptionDescription() {
        return slashCommandOptionDescription;
    }

}
