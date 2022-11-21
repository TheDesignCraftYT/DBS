package de.thedesigncraft.discord.botstuff.someMoreCommands.commands.slash;

import de.thedesigncraft.discord.botstuff.essential.EmbedTemplates;
import de.thedesigncraft.discord.botstuff.essential.manage.Main;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.ISlashCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.stuffPackages.IStuffPackage;
import de.thedesigncraft.discord.botstuff.someMoreCommands.SomeMoreCommandsPackage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PingSlashCommand implements ISlashCommand {

    @Override
    public @NotNull String version() {
        return "v1.0.0-alpha.1";
    }

    @Override
    public @NotNull String description() {
        return "Zeigt dir den Ping des Bots an.";
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
        return Emoji.fromUnicode("U+1F3D3");
    }

    @Override
    public List<Permission> requiredPermissions() {
        return null;
    }

    @Override
    public void performSlashCommand(@NotNull SlashCommandInteractionEvent event) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.copyFrom(EmbedTemplates.standardEmbed(commandEmoji().getName() + " Ping", ""));

        embedBuilder.addField("Discord API Gateway:", "```" + Main.jda.getGatewayPing() + "ms```", true);
        embedBuilder.addField("Discord API REST:", "```" + Main.jda.getRestPing().complete() + "ms```", true);

        event.replyEmbeds(embedBuilder.build()).queue();

    }

}
