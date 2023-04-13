package de.thedesigncraft.discord.core.commands.discord.types.slash;

import de.thedesigncraft.discord.core.commands.discord.types.DiscordCommandSetupBuilder;
import de.thedesigncraft.discord.tools.Checks;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SlashCommandSetupBuilder extends DiscordCommandSetupBuilder {

    private List<OptionData> options;

    public SlashCommandSetupBuilder() {
    }

    public SlashCommandSetupBuilder(@NotNull SlashCommandSetupBuilder builder) {
        super(builder);
        this.options = builder.options;
    }

    public SlashCommandSetupBuilder(@NotNull SlashCommandSetup setup) {
        super(setup);
        this.options = setup.getOptions();
    }

    public SlashCommandSetupBuilder setName(@NotNull String name) {
        super.setName(name);
        return this;
    }

    public SlashCommandSetupBuilder setMessageAnswerProtectionEnabled(boolean messageAnswerProtectionEnabled) {
        super.setMessageAnswerProtectionEnabled(messageAnswerProtectionEnabled);
        return this;
    }

    public SlashCommandSetupBuilder setGlobal(boolean global) {
        super.setGlobal(global);
        return this;
    }

    public SlashCommandSetupBuilder setGuilds(@NotNull List<Guild> guilds) {
        super.setGuilds(guilds);
        return this;
    }

    public SlashCommandSetupBuilder setDescription(@NotNull String description) {
        super.setDescription(description);
        return this;
    }

    public SlashCommandSetupBuilder setGuildOnly(boolean guildOnly) {
        super.setGuildOnly(guildOnly);
        return this;
    }

    public SlashCommandSetupBuilder setRequiredPermissions(@NotNull List<Permission> requiredPermissions) {
        super.setRequiredPermissions(requiredPermissions);
        return this;
    }

    public SlashCommandSetupBuilder setOptions(List<OptionData> options) {
        Checks.checkNullOrEmpty(options, "Options");
        this.options = options;
        return this;
    }

    public SlashCommandSetup build() {
        Checks.checkNullOrEmpty(this.name, "Name");
        Checks.checkNullOrEmpty(this.description, "Description");
        if (Checks.isNullOrEmpty(this.guilds) && !this.global)
            throw new IllegalStateException("Guilds may not be null or empty if the command isn't global!");
        return new SlashCommandSetup(this.name, this.messageAnswerProtectionEnabled, this.global, this.guilds, this.options, this.description, this.guildOnly, this.requiredPermissions);
    }

}
