package de.thedesigncraft.discord.core.commands.discord.types.user;

import de.thedesigncraft.discord.core.commands.discord.types.DiscordCommandSetupBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserCommandSetupBuilder extends DiscordCommandSetupBuilder {

    public UserCommandSetupBuilder() {
    }

    /**
     * Creates a new {@link UserCommandSetupBuilder} from a {@link UserCommandSetup} and copies all values.
     * @param builder The {@link UserCommandSetup} to copy
     */
    public UserCommandSetupBuilder(@NotNull UserCommandSetupBuilder builder) {
        super(builder);
    }

    /**
     * Creates a new {@link UserCommandSetupBuilder} from a {@link UserCommandSetup}
     * @param setup The {@link UserCommandSetup}
     */
    public UserCommandSetupBuilder(@NotNull UserCommandSetup setup) {
        super(setup);
    }

    /**
     * Sets the name of the command
     * @param name The name of the command
     */
    public UserCommandSetupBuilder setName(@NotNull String name) {
        super.setName(name);
        return this;
    }

    /**
     * Activates or deactivates the MessageAnswerProtection
     * @param messageAnswerProtectionEnabled true to activate, false to deactivate
     */
    public UserCommandSetupBuilder setMessageAnswerProtectionEnabled(boolean messageAnswerProtectionEnabled) {
        super.setMessageAnswerProtectionEnabled(messageAnswerProtectionEnabled);
        return this;
    }

    /**
     * Sets the command as global or guild only
     * @param global true to set the command as global, false to set the command as guild only
     */
    public UserCommandSetupBuilder setGlobal(boolean global) {
        super.setGlobal(global);
        return this;
    }

    /**
     * Sets the guilds where the command is available.
     * Note: This will only work if the command is not global
     * @param guilds The guilds where the command is available
     */
    public UserCommandSetupBuilder setGuilds(@NotNull List<Guild> guilds) {
        super.setGuilds(guilds);
        return this;
    }

    /**
     * Sets the command as guild only
     * @param guildOnly true to set the command as guild only, false to set the command as global
     */
    public UserCommandSetupBuilder setGuildOnly(boolean guildOnly) {
        super.setGuildOnly(guildOnly);
        return this;
    }

    /**
     * Sets the required permissions for the command
     * @param requiredPermissions The required permissions for the command
     */
    public UserCommandSetupBuilder setRequiredPermissions(@NotNull List<Permission> requiredPermissions) {
        super.setRequiredPermissions(requiredPermissions);
        return this;
    }

    /**
     * Builds the {@link UserCommandSetup}
     * @return The {@link UserCommandSetup}
     */
    public UserCommandSetup build() {
        return (UserCommandSetup) super.build();
    }

}
