package de.thedesigncraft.discord.botstuff.someMoreCommands.commands.slash;

import de.thedesigncraft.discord.botstuff.essential.EmbedTemplates;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.ISlashCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupValues;
import de.thedesigncraft.discord.botstuff.someMoreCommands.SomeMoreCommandsPackage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VersionSlashCommand implements ISlashCommand {

    @Override
    public @NotNull String version() {
        return "v1.0.0-alpha.1";
    }

    @Override
    public @NotNull String description() {
        return "Gibt die aktuelle Version des Bots zurück.";
    }

    @Override
    public @NotNull String category() {
        return "Nützliches";
    }

    @Override
    public boolean globalCommand() {
        return IStuffPackage.activatedStuffPackageClasses().contains(SomeMoreCommandsPackage.class);
    }

    @Override
    public List<Guild> guilds() {
        return null;
    }

    @Override
    public boolean guildOnly() {
        return false;
    }

    @Override
    public List<OptionData> options() {
        return null;
    }

    @Override
    public @NotNull Emoji commandEmoji() {
        return Emoji.fromUnicode("U+1F916");
    }

    @Override
    public List<Permission> requiredPermissions() {
        return null;
    }

    @Override
    public void performSlashCommand(@NotNull SlashCommandInteractionEvent event) {

        EmbedBuilder embedBuilder = new EmbedBuilder(EmbedTemplates.standardEmbed(commandEmoji().getName() + " Bot Version", "Unten siehst du die aktuelle Version des Bots."));
        embedBuilder.addField("Version", StartupValues.currentVersion, true);
        embedBuilder.addField("VersionType", String.valueOf(StartupValues.currentVersionType), true);

        event.replyEmbeds(embedBuilder.build()).queue();

    }

}
