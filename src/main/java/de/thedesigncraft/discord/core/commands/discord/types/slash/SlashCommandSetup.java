package de.thedesigncraft.discord.core.commands.discord.types.slash;

import de.thedesigncraft.discord.core.commands.discord.types.DiscordCommandSetup;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SlashCommandSetup extends DiscordCommandSetup {

    /**
     * The options of the slash command
     */
    private final List<OptionData> options;

    /**
     * Creates a new {@link SlashCommandSetup} with the given parameters
     *
     * @param name                       The name of the command
     * @param messageAnswerProtectionEnabled If the message answer protection is enabled
     * @param global                     If the command is global
     * @param guilds                     The guilds where the command is available
     * @param options                    The options of the slash command
     * @param description                The description of the slash command
     * @param guildOnly                  If the command is only available in guilds
     * @param requiredPermissions        The required permissions to execute the command
     */
    public SlashCommandSetup(@NotNull String name, boolean messageAnswerProtectionEnabled, boolean global, @Nullable List<Guild> guilds, @Nullable List<OptionData> options, @NotNull String description, boolean guildOnly, @Nullable List<Permission> requiredPermissions) {
        super(name, messageAnswerProtectionEnabled, global, guilds, description, guildOnly, requiredPermissions);
        this.options = options;
    }

    /**
     * @return The options of the slash command
     */
    public List<OptionData> getOptions() {
        return options;
    }

}
