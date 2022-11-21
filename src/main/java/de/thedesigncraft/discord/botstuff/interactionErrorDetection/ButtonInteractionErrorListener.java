package de.thedesigncraft.discord.botstuff.interactionErrorDetection;

import de.thedesigncraft.discord.botstuff.essential.EmbedTemplates;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupMethods;
import de.thedesigncraft.discord.botstuff.interactionErrorDetection.setup.InteractionErrorDetectionValues;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ButtonInteractionErrorListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        if (!StartupMethods.getActivatedStuffPackageClasses().contains(InteractionErrorDetectionPackage.class))
            return;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }

        if (!event.isAcknowledged()) {

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.copyFrom(EmbedTemplates.issueEmbed("A button with an unknown id was pressed.", false));
            embedBuilder.addField("ButtonId", "```" + event.getButton().getId().split("&id=")[0] + "```", true);
            embedBuilder.addField("User", "```" + event.getUser().getAsTag() + "```", true);

            if (event.isFromGuild()) {

                embedBuilder.addField("Server", "```" + event.getGuild().getName() + "```", true);

            } else {

                embedBuilder.addField("DM User", "```" + event.getUser().getName() + "```", true);

            }

            Logger logger = LoggerFactory.getLogger(SelectInteractionErrorListener.class);
            logger.error("Unknown button");

            InteractionErrorDetectionValues.users.forEach(user1 -> user1.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessageEmbeds(embedBuilder.build()).queue()));
            event.replyEmbeds(EmbedTemplates.issueEmbed("An unexpected error occurred.\n\nThe bug has been sent to the developer.", false)).setEphemeral(true).queue();

        }

    }

}
