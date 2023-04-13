package de.thedesigncraft.discord.core.commands.discord.types.slashanduser;

import de.thedesigncraft.discord.manage.commands.discord.types.DiscordCommandSetupBuilder;
import de.thedesigncraft.discord.tools.Checks;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SlashAndUserCommandSetupBuilder extends DiscordCommandSetupBuilder {

    private String slashCommandOptionDescription;

    public SlashAndUserCommandSetupBuilder() {
    }

    public SlashAndUserCommandSetupBuilder(@NotNull SlashAndUserCommandSetupBuilder builder) {
        super(builder);
        this.slashCommandOptionDescription = builder.slashCommandOptionDescription;
    }

    public SlashAndUserCommandSetupBuilder(@NotNull SlashAndUserCommandSetup setup) {
        super(setup);
        this.slashCommandOptionDescription = setup.getSlashCommandOptionDescription();
    }

    public SlashAndUserCommandSetupBuilder setName(@NotNull String name) {
        super.setName(name);
        return this;
    }

    public SlashAndUserCommandSetupBuilder setMessageAnswerProtectionEnabled(boolean messageAnswerProtectionEnabled) {
        super.setMessageAnswerProtectionEnabled(messageAnswerProtectionEnabled);
        return this;
    }

    public SlashAndUserCommandSetupBuilder setGlobal(boolean global) {
        super.setGlobal(global);
        return this;
    }

    public SlashAndUserCommandSetupBuilder setGuilds(@NotNull List<Guild> guilds) {
        super.setGuilds(guilds);
        return this;
    }

    public SlashAndUserCommandSetupBuilder setDescription(@NotNull String description) {
        super.setDescription(description);
        return this;
    }

    public SlashAndUserCommandSetupBuilder setGuildOnly(boolean guildOnly) {
        super.setGuildOnly(guildOnly);
        return this;
    }

    public SlashAndUserCommandSetupBuilder setRequiredPermissions(@NotNull List<Permission> requiredPermissions) {
        super.setRequiredPermissions(requiredPermissions);
        return this;
    }

    public SlashAndUserCommandSetupBuilder setSlashCommandOptionDescription(String slashCommandOptionDescription) {
        Checks.checkNullOrEmpty(slashCommandOptionDescription, "Slash-command-option description");
        this.slashCommandOptionDescription = slashCommandOptionDescription;
        return this;
    }

    public SlashAndUserCommandSetup build() {
        Checks.checkNullOrEmpty(this.name, "Name");
        Checks.checkNullOrEmpty(this.description, "Description");
        Checks.checkNullOrEmpty(this.slashCommandOptionDescription, "Slash-command-option description");
        if (Checks.isNullOrEmpty(this.guilds) && !this.global)
            throw new IllegalStateException("Guilds may not be null or empty if the command isn't global!");
        return new SlashAndUserCommandSetup(this.name, this.messageAnswerProtectionEnabled, this.global, this.guilds, this.description, this.guildOnly, this.requiredPermissions, this.slashCommandOptionDescription);
    }

}
