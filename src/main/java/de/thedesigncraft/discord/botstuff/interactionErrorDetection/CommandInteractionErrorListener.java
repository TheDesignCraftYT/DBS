package de.thedesigncraft.discord.botstuff.interactionErrorDetection;

import de.thedesigncraft.discord.botstuff.essential.EmbedTemplates;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupMethods;
import de.thedesigncraft.discord.botstuff.interactionErrorDetection.setup.InteractionErrorDetectionValues;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CommandInteractionErrorListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (!StartupMethods.getActivatedStuffPackageClasses().contains(InteractionErrorDetectionPackage.class))
            return;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }

        if (!event.isAcknowledged()) {

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.copyFrom(EmbedTemplates.issueEmbed("There was no response to a SlashCommand.", false));
            embedBuilder.addField("CommandName", "```/" + Objects.requireNonNull(event.getName()).split("&id=")[0] + "```", true);
            embedBuilder.addField("User", "```" + event.getUser().getAsTag() + "```", true);
            embedBuilder.addField("Server", "```" + Objects.requireNonNull(event.getGuild()).getName() + "```", true);

            Logger logger = LoggerFactory.getLogger(SelectInteractionErrorListener.class);
            logger.error("No response to a SlashCommand");

            InteractionErrorDetectionValues.users.forEach(user -> user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessageEmbeds(embedBuilder.build()).queue()));
            event.replyEmbeds(EmbedTemplates.issueEmbed("An unexpected error occurred.\n\nThe bug has been sent to the developer.", false)).setEphemeral(true).queue();

        }

    }

    @Override
    public void onUserContextInteraction(@NotNull UserContextInteractionEvent event) {

        if (!StartupMethods.getActivatedStuffPackageClasses().contains(InteractionErrorDetectionPackage.class))
            return;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }

        if (!event.isAcknowledged()) {

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.copyFrom(EmbedTemplates.issueEmbed("There was no response to a UserContextMenu.", false));
            embedBuilder.addField("CommandName", "```/" + Objects.requireNonNull(event.getName()).split("&id=")[0] + "```", true);
            embedBuilder.addField("User", "```" + event.getUser().getAsTag() + "```", true);
            embedBuilder.addField("Server", "```" + Objects.requireNonNull(event.getGuild()).getName() + "```", true);

            Logger logger = LoggerFactory.getLogger(SelectInteractionErrorListener.class);
            logger.error("No response to a UserContextMenu");

            InteractionErrorDetectionValues.users.forEach(user -> user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessageEmbeds(embedBuilder.build()).queue()));
            event.replyEmbeds(EmbedTemplates.issueEmbed("An unexpected error occurred.\n\nThe bug has been sent to the developer.", false)).setEphemeral(true).queue();

        }

    }

    @Override
    public void onMessageContextInteraction(@NotNull MessageContextInteractionEvent event) {

        if (!StartupMethods.getActivatedStuffPackageClasses().contains(InteractionErrorDetectionPackage.class))
            return;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }

        if (!event.isAcknowledged()) {

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.copyFrom(EmbedTemplates.issueEmbed("There was no response to a MessageContextMenu.", false));
            embedBuilder.addField("CommandName", "```/" + Objects.requireNonNull(event.getName()).split("&id=")[0] + "```", true);
            embedBuilder.addField("User", "```" + event.getUser().getAsTag() + "```", true);
            embedBuilder.addField("Server", "```" + Objects.requireNonNull(event.getGuild()).getName() + "```", true);

            Logger logger = LoggerFactory.getLogger(SelectInteractionErrorListener.class);
            logger.error("No response to a MessageContextMenu");

            InteractionErrorDetectionValues.users.forEach(user -> user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessageEmbeds(embedBuilder.build()).queue()));
            event.replyEmbeds(EmbedTemplates.issueEmbed("An unexpected error occurred.\n\nThe bug has been sent to the developers.", false)).setEphemeral(true).queue();

        }

    }

}
