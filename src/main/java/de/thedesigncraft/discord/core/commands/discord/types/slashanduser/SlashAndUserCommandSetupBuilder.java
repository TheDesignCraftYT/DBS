package de.thedesigncraft.discord.core.commands.discord.types.slashanduser;

import de.thedesigncraft.discord.core.commands.discord.types.DiscordCommandSetupBuilder;
import de.thedesigncraft.discord.core.commands.discord.types.message.MessageCommandSetupBuilder;
import de.thedesigncraft.discord.tools.Checks;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SlashAndUserCommandSetupBuilder extends DiscordCommandSetupBuilder {

    /**
     * The option description of the slash command
     */
    private String slashCommandOptionDescription;

    public SlashAndUserCommandSetupBuilder() {
    }

    /**
     * Creates a new {@link SlashAndUserCommandSetupBuilder} from a {@link SlashAndUserCommandSetupBuilder} and copies all values.
     * @param builder The {@link SlashAndUserCommandSetupBuilder} to copy
     */
    public SlashAndUserCommandSetupBuilder(@NotNull SlashAndUserCommandSetupBuilder builder) {
        super(builder);
        this.slashCommandOptionDescription = builder.slashCommandOptionDescription;
    }

    /**
     * Creates a new {@link SlashAndUserCommandSetupBuilder} from a {@link SlashAndUserCommandSetup}
     * @param setup The {@link SlashAndUserCommandSetup}
     */
    public SlashAndUserCommandSetupBuilder(@NotNull SlashAndUserCommandSetup setup) {
        super(setup);
        this.slashCommandOptionDescription = setup.getSlashCommandOptionDescription();
    }

    /**
     * Sets the name of the command
     * @param name The name of the command
     */
    public SlashAndUserCommandSetupBuilder setName(@NotNull String name) {
        super.setName(name);
        return this;
    }

    /**
     * Activates or deactivates the MessageAnswerProtection
     * @param messageAnswerProtectionEnabled true to activate, false to deactivate
     */
    public SlashAndUserCommandSetupBuilder setMessageAnswerProtectionEnabled(boolean messageAnswerProtectionEnabled) {
        super.setMessageAnswerProtectionEnabled(messageAnswerProtectionEnabled);
        return this;
    }

    /**
     * Sets the command as global or guild only
     * @param global true to set the command as global, false to set the command as guild only
     */
    public SlashAndUserCommandSetupBuilder setGlobal(boolean global) {
        super.setGlobal(global);
        return this;
    }

    /**
     * Sets the guilds where the command is available.
     * Note: This will only work if the command is not global
     * @param guilds The guilds where the command is available
     */
    public SlashAndUserCommandSetupBuilder setGuilds(@NotNull List<Guild> guilds) {
        super.setGuilds(guilds);
        return this;
    }

    /**
     * Sets the description of the command
     * @param description The description of the command
     */
    public SlashAndUserCommandSetupBuilder setDescription(@NotNull String description) {
        super.setDescription(description);
        return this;
    }

    /**
     * Sets if the command is only available in guilds or also in private messages
     * @param guildOnly true to set the command as guild only, false to set the command as available in guilds and private messages
     */
    public SlashAndUserCommandSetupBuilder setGuildOnly(boolean guildOnly) {
        super.setGuildOnly(guildOnly);
        return this;
    }

    /**
     * Sets the required permissions to execute the command
     * @param requiredPermissions The required permissions to execute the command
     */
    public SlashAndUserCommandSetupBuilder setRequiredPermissions(@NotNull List<Permission> requiredPermissions) {
        super.setRequiredPermissions(requiredPermissions);
        return this;
    }

    /**
     * Sets the option description of the slash command
     * @param slashCommandOptionDescription The option description of the slash command
     */
    public SlashAndUserCommandSetupBuilder setSlashCommandOptionDescription(String slashCommandOptionDescription) {
        Checks.checkNullOrEmpty(slashCommandOptionDescription, "Slash-command-option description");
        this.slashCommandOptionDescription = slashCommandOptionDescription;
        return this;
    }

    /**
     * Builds the {@link SlashAndUserCommandSetup}
     * @return The {@link SlashAndUserCommandSetup}
     */
    public SlashAndUserCommandSetup build() {
        Checks.checkNullOrEmpty(this.name, "Name");
        Checks.checkNullOrEmpty(this.description, "Description");
        Checks.checkNullOrEmpty(this.slashCommandOptionDescription, "Slash-command-option description");
        if (Checks.isNullOrEmpty(this.guilds) && !this.global)
            throw new IllegalStateException("Guilds may not be null or empty if the command isn't global!");
        return new SlashAndUserCommandSetup(this.name, this.messageAnswerProtectionEnabled, this.global, this.guilds, this.description, this.guildOnly, this.requiredPermissions, this.slashCommandOptionDescription);
    }

}
