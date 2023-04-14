package de.thedesigncraft.discord.core.commands.discord.types;

import de.thedesigncraft.discord.tools.Checks;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DiscordCommandSetupBuilder {

    protected String name;
    protected boolean messageAnswerProtectionEnabled;
    protected boolean global;
    protected List<Guild> guilds;
    protected String description;
    protected boolean guildOnly;
    protected List<Permission> requiredPermissions;

    public DiscordCommandSetupBuilder() {
    }

    /**
     * Creates a new {@link DiscordCommandSetupBuilder} from a {@link DiscordCommandSetupBuilder} and copies all values.
     * @param builder The {@link DiscordCommandSetupBuilder} to copy
     */
    public DiscordCommandSetupBuilder(@NotNull DiscordCommandSetupBuilder builder) {
        this.name = builder.name;
        this.messageAnswerProtectionEnabled = builder.messageAnswerProtectionEnabled;
        this.global = builder.global;
        this.guilds = builder.guilds;
        this.description = builder.description;
        this.guildOnly = builder.guildOnly;
        this.requiredPermissions = builder.requiredPermissions;
    }

    /**
     * Creates a new {@link DiscordCommandSetupBuilder} from a {@link DiscordCommandSetup}
     * @param setup The {@link DiscordCommandSetup}
     */
    public DiscordCommandSetupBuilder(@NotNull DiscordCommandSetup setup) {
        this.name = setup.getName();
        this.messageAnswerProtectionEnabled = setup.isMessageAnswerProtectionEnabled();
        this.global = setup.isGlobal();
        this.guilds = setup.getGuilds();
        this.description = setup.getDescription();
        this.guildOnly = setup.isGuildOnly();
        this.requiredPermissions = setup.getRequiredPermissions();
    }

    /**
     * Sets the name of the command
     * @param name The name of the command
     */
    public DiscordCommandSetupBuilder setName(@NotNull String name) {
        Checks.checkNullOrEmpty(name, "Name");
        this.name = name;
        return this;
    }

    /**
     * Activates or deactivates the MessageAnswerProtection
     * @param messageAnswerProtectionEnabled true to activate, false to deactivate
     */
    public DiscordCommandSetupBuilder setMessageAnswerProtectionEnabled(boolean messageAnswerProtectionEnabled) {
        this.messageAnswerProtectionEnabled = messageAnswerProtectionEnabled;
        return this;
    }

    /**
     * Sets the command as global or guild only
     * @param global true to set the command as global, false to set the command as guild only
     */
    public DiscordCommandSetupBuilder setGlobal(boolean global) {
        this.global = global;
        return this;
    }

    /**
     * Sets the guilds where the command is available.
     * Note: This will only work if the command is not global
     * @param guilds The guilds where the command is available
     */
    public DiscordCommandSetupBuilder setGuilds(@NotNull List<Guild> guilds) {
        Checks.checkNullOrEmpty(guilds, "Guilds", "(Use setGlobal(true) instead)");
        this.guilds = guilds;
        return this;
    }

    /**
     * Sets the description of the command
     * @param description The description of the command
     */
    public DiscordCommandSetupBuilder setDescription(@NotNull String description) {
        Checks.checkNullOrEmpty(description, "Description");
        this.description = description;
        return this;
    }

    /**
     * Sets if the command is only available in guilds or also in private messages
     * @param guildOnly true to set the command as guild only, false to set the command as available in guilds and private messages
     */
    public DiscordCommandSetupBuilder setGuildOnly(boolean guildOnly) {
        this.guildOnly = guildOnly;
        return this;
    }

    /**
     * Sets the required permissions to execute the command
     * @param requiredPermissions The required permissions to execute the command
     */
    public DiscordCommandSetupBuilder setRequiredPermissions(@NotNull List<Permission> requiredPermissions) {
        Checks.checkNullOrEmpty(requiredPermissions, "RequiredPermissions");
        this.requiredPermissions = requiredPermissions;
        return this;
    }

    /**
     * Builds the {@link DiscordCommandSetup}
     * @return The {@link DiscordCommandSetup}
     */
    public DiscordCommandSetup build() {
        Checks.checkNullOrEmpty(this.name, "Name");
        if (Checks.isNullOrEmpty(this.guilds) && !this.global)
            LoggerFactory.getLogger(name + "-Command").warn("Command won't be loaded because it's not global and no guilds are set.");
        return new DiscordCommandSetup(this.name, this.messageAnswerProtectionEnabled, this.global, this.guilds, this.description, this.guildOnly, this.requiredPermissions);
    }

}
