package de.thedesigncraft.discord.core.commands.discord.types.slash;

import de.thedesigncraft.discord.core.commands.discord.types.DiscordCommandSetupBuilder;
import de.thedesigncraft.discord.core.commands.discord.types.message.MessageCommandSetup;
import de.thedesigncraft.discord.tools.Checks;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SlashCommandSetupBuilder extends DiscordCommandSetupBuilder {

    /**
     * The options of the slash command
     */
    private List<OptionData> options;

    public SlashCommandSetupBuilder() {
    }

    /**
     * Creates a new {@link SlashCommandSetupBuilder} from a {@link SlashCommandSetupBuilder} and copies all values.
     * @param builder The {@link SlashCommandSetupBuilder} to copy
     */
    public SlashCommandSetupBuilder(@NotNull SlashCommandSetupBuilder builder) {
        super(builder);
        this.options = builder.options;
    }

    /**
     * Creates a new {@link SlashCommandSetupBuilder} from a {@link SlashCommandSetup}
     * @param setup The {@link SlashCommandSetup}
     */
    public SlashCommandSetupBuilder(@NotNull SlashCommandSetup setup) {
        super(setup);
        this.options = setup.getOptions();
    }

    /**
     * Sets the name of the command
     * @param name The name of the command
     */
    public SlashCommandSetupBuilder setName(@NotNull String name) {
        super.setName(name);
        return this;
    }

    /**
     * Activates or deactivates the MessageAnswerProtection
     * @param messageAnswerProtectionEnabled true to activate, false to deactivate
     */
    public SlashCommandSetupBuilder setMessageAnswerProtectionEnabled(boolean messageAnswerProtectionEnabled) {
        super.setMessageAnswerProtectionEnabled(messageAnswerProtectionEnabled);
        return this;
    }

    /**
     * Sets the command as global or guild only
     * @param global true to set the command as global, false to set the command as guild only
     */
    public SlashCommandSetupBuilder setGlobal(boolean global) {
        super.setGlobal(global);
        return this;
    }

    /**
     * Sets the guilds where the command is available.
     * Note: This will only work if the command is not global
     * @param guilds The guilds where the command is available
     */
    public SlashCommandSetupBuilder setGuilds(@NotNull List<Guild> guilds) {
        super.setGuilds(guilds);
        return this;
    }

    /**
     * Sets the description of the command
     * @param description The description of the command
     */
    public SlashCommandSetupBuilder setDescription(@NotNull String description) {
        super.setDescription(description);
        return this;
    }

    /**
     * Sets if the command is only available in guilds or also in private messages
     * @param guildOnly true to set the command as guild only, false to set the command as available in guilds and private messages
     */
    public SlashCommandSetupBuilder setGuildOnly(boolean guildOnly) {
        super.setGuildOnly(guildOnly);
        return this;
    }

    /**
     * Sets the required permissions to execute the command
     * @param requiredPermissions The required permissions to execute the command
     */
    public SlashCommandSetupBuilder setRequiredPermissions(@NotNull List<Permission> requiredPermissions) {
        super.setRequiredPermissions(requiredPermissions);
        return this;
    }

    /**
     * Sets the options of the slash command
     * @param options The options of the slash command
     */
    public SlashCommandSetupBuilder setOptions(List<OptionData> options) {
        Checks.checkNullOrEmpty(options, "Options");
        this.options = options;
        return this;
    }

    /**
     * Builds the {@link SlashCommandSetup}
     * @return The {@link SlashCommandSetup}
     */
    public SlashCommandSetup build() {
        Checks.checkNullOrEmpty(this.name, "Name");
        Checks.checkNullOrEmpty(this.description, "Description");
        if (Checks.isNullOrEmpty(this.guilds) && !this.global)
            throw new IllegalStateException("Guilds may not be null or empty if the command isn't global!");
        return new SlashCommandSetup(this.name, this.messageAnswerProtectionEnabled, this.global, this.guilds, this.options, this.description, this.guildOnly, this.requiredPermissions);
    }

}
