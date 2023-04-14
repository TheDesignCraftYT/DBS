package de.thedesigncraft.discord.core.commands.discord.types.message;

import de.thedesigncraft.discord.core.commands.discord.types.DiscordCommandSetupBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessageCommandSetupBuilder extends DiscordCommandSetupBuilder {

    public MessageCommandSetupBuilder() {
    }

    /**
     * Creates a new {@link MessageCommandSetupBuilder} from a {@link MessageCommandSetupBuilder} and copies all values.
     * @param builder The {@link MessageCommandSetupBuilder} to copy
     */
    public MessageCommandSetupBuilder(@NotNull MessageCommandSetupBuilder builder) {
        super(builder);
    }

    /**
     * Creates a new {@link MessageCommandSetupBuilder} from a {@link MessageCommandSetup}
     * @param setup The {@link MessageCommandSetup}
     */
    public MessageCommandSetupBuilder(@NotNull MessageCommandSetup setup) {
        super(setup);
    }

    /**
     * Sets the name of the command
     * @param name The name of the command
     */
    public MessageCommandSetupBuilder setName(@NotNull String name) {
        super.setName(name);
        return this;
    }

    /**
     * Activates or deactivates the MessageAnswerProtection
     * @param messageAnswerProtectionEnabled true to activate, false to deactivate
     */
    public MessageCommandSetupBuilder setMessageAnswerProtectionEnabled(boolean messageAnswerProtectionEnabled) {
        super.setMessageAnswerProtectionEnabled(messageAnswerProtectionEnabled);
        return this;
    }

    /**
     * Sets the command as global or guild only
     * @param global true to set the command as global, false to set the command as guild only
     */
    public MessageCommandSetupBuilder setGlobal(boolean global) {
        super.setGlobal(global);
        return this;
    }

    /**
     * Sets the guilds where the command is available.
     * Note: This will only work if the command is not global
     * @param guilds The guilds where the command is available
     */
    public MessageCommandSetupBuilder setGuilds(@NotNull List<Guild> guilds) {
        super.setGuilds(guilds);
        return this;
    }

    /**
     * Sets if the command is only available in guilds or also in private messages
     * @param guildOnly true to set the command as guild only, false to set the command as available in guilds and private messages
     */
    public MessageCommandSetupBuilder setGuildOnly(boolean guildOnly) {
        super.setGuildOnly(guildOnly);
        return this;
    }

    /**
     * Sets the required permissions to execute the command
     * @param requiredPermissions The required permissions to execute the command
     */
    public MessageCommandSetupBuilder setRequiredPermissions(@NotNull List<Permission> requiredPermissions) {
        super.setRequiredPermissions(requiredPermissions);
        return this;
    }

    /**
     * Builds the {@link MessageCommandSetup}
     * @return The {@link MessageCommandSetup}
     */
    public MessageCommandSetup build() {
        return (MessageCommandSetup) super.build();
    }

}
