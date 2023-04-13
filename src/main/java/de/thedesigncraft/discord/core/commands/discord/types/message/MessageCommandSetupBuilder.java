package de.thedesigncraft.discord.core.commands.discord.types.message;

import de.thedesigncraft.discord.manage.commands.discord.types.DiscordCommandSetupBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessageCommandSetupBuilder extends DiscordCommandSetupBuilder {

    public MessageCommandSetupBuilder() {
    }

    public MessageCommandSetupBuilder(@NotNull MessageCommandSetupBuilder builder) {
        super(builder);
    }

    public MessageCommandSetupBuilder(@NotNull MessageCommandSetup setup) {
        super(setup);
    }

    public MessageCommandSetupBuilder setName(@NotNull String name) {
        super.setName(name);
        return this;
    }

    public MessageCommandSetupBuilder setMessageAnswerProtectionEnabled(boolean messageAnswerProtectionEnabled) {
        super.setMessageAnswerProtectionEnabled(messageAnswerProtectionEnabled);
        return this;
    }

    public MessageCommandSetupBuilder setGlobal(boolean global) {
        super.setGlobal(global);
        return this;
    }

    public MessageCommandSetupBuilder setGuilds(@NotNull List<Guild> guilds) {
        super.setGuilds(guilds);
        return this;
    }

    public MessageCommandSetupBuilder setGuildOnly(boolean guildOnly) {
        super.setGuildOnly(guildOnly);
        return this;
    }

    public MessageCommandSetupBuilder setRequiredPermissions(@NotNull List<Permission> requiredPermissions) {
        super.setRequiredPermissions(requiredPermissions);
        return this;
    }

    public MessageCommandSetup build() {
        return (MessageCommandSetup) super.build();
    }

}
