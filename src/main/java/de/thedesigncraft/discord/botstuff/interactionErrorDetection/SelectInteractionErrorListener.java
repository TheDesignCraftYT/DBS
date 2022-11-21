package de.thedesigncraft.discord.botstuff.interactionErrorDetection;

import de.thedesigncraft.discord.botstuff.essential.EmbedTemplates;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupMethods;
import de.thedesigncraft.discord.botstuff.interactionErrorDetection.setup.InteractionErrorDetectionValues;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SelectInteractionErrorListener extends ListenerAdapter {

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {

        if (!StartupMethods.getActivatedStuffPackageClasses().contains(InteractionErrorDetectionPackage.class))
            return;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }

        if (!event.isAcknowledged()) {

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.copyFrom(EmbedTemplates.issueEmbed("A SelectMenu with an unknown id was used.", false));
            embedBuilder.addField("SelectMenuId", "```" + Objects.requireNonNull(event.getSelectMenu().getId()).split("&id=")[0] + "```", true);
            embedBuilder.addField("User", "```" + event.getUser().getAsTag() + "```", true);
            embedBuilder.addField("Server", "```" + Objects.requireNonNull(event.getGuild()).getName() + "```", true);

            Logger logger = LoggerFactory.getLogger(SelectInteractionErrorListener.class);
            logger.error("Unknown SelectMenu");

            InteractionErrorDetectionValues.users.forEach(user -> user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessageEmbeds(embedBuilder.build()).queue()));
            event.replyEmbeds(EmbedTemplates.issueEmbed("An unexpected error occurred.\n\nThe bug has been sent to the developer.", false)).setEphemeral(true).queue();

        }

    }

    @Override
    public void onEntitySelectInteraction(@NotNull EntitySelectInteractionEvent event) {

        if (!StartupMethods.getActivatedStuffPackageClasses().contains(InteractionErrorDetectionPackage.class))
            return;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }

        if (!event.isAcknowledged()) {

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.copyFrom(EmbedTemplates.issueEmbed("A SelectMenu with an unknown id was used.", false));
            embedBuilder.addField("SelectMenuId", "```" + Objects.requireNonNull(event.getSelectMenu().getId()).split("&id=")[0] + "```", true);
            embedBuilder.addField("User", "```" + event.getUser().getAsTag() + "```", true);
            embedBuilder.addField("Server", "```" + Objects.requireNonNull(event.getGuild()).getName() + "```", true);

            Logger logger = LoggerFactory.getLogger(SelectInteractionErrorListener.class);
            logger.error("Unknown SelectMenu");

            InteractionErrorDetectionValues.users.forEach(user -> user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessageEmbeds(embedBuilder.build()).queue()));
            event.replyEmbeds(EmbedTemplates.issueEmbed("An unexpected error occurred.\n\nThe bug has been sent to the developer.", false)).setEphemeral(true).queue();

        }

    }

}
