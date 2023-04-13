package de.thedesigncraft.discord.core.commands.discord.types.user;

import de.thedesigncraft.discord.manage.commands.discord.types.DiscordCommandSetupBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserCommandSetupBuilder extends DiscordCommandSetupBuilder {

    public UserCommandSetupBuilder() {
    }

    public UserCommandSetupBuilder(@NotNull UserCommandSetupBuilder builder) {
        super(builder);
    }

    public UserCommandSetupBuilder(@NotNull UserCommandSetup setup) {
        super(setup);
    }

    public UserCommandSetupBuilder setName(@NotNull String name) {
        super.setName(name);
        return this;
    }

    public UserCommandSetupBuilder setMessageAnswerProtectionEnabled(boolean messageAnswerProtectionEnabled) {
        super.setMessageAnswerProtectionEnabled(messageAnswerProtectionEnabled);
        return this;
    }

    public UserCommandSetupBuilder setGlobal(boolean global) {
        super.setGlobal(global);
        return this;
    }

    public UserCommandSetupBuilder setGuilds(@NotNull List<Guild> guilds) {
        super.setGuilds(guilds);
        return this;
    }

    public UserCommandSetupBuilder setGuildOnly(boolean guildOnly) {
        super.setGuildOnly(guildOnly);
        return this;
    }

    public UserCommandSetupBuilder setRequiredPermissions(@NotNull List<Permission> requiredPermissions) {
        super.setRequiredPermissions(requiredPermissions);
        return this;
    }

    public UserCommandSetup build() {
        return (UserCommandSetup) super.build();
    }

}
