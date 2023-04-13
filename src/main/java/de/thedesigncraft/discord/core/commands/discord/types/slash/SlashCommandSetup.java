package de.thedesigncraft.discord.core.commands.discord.types.slash;

import de.thedesigncraft.discord.core.commands.discord.types.DiscordCommandSetup;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SlashCommandSetup extends DiscordCommandSetup {

    private final List<OptionData> options;

    public SlashCommandSetup(@NotNull String name, boolean messageAnswerProtectionEnabled, boolean global, @Nullable List<Guild> guilds, @Nullable List<OptionData> options, @NotNull String description, boolean guildOnly, @Nullable List<Permission> requiredPermissions) {
        super(name, messageAnswerProtectionEnabled, global, guilds, description, guildOnly, requiredPermissions);
        this.options = options;
    }

    public List<OptionData> getOptions() {
        return options;
    }

}
