package de.thedesigncraft.dbs.listeners;

import de.thedesigncraft.dbs.tools.Checks;
import de.thedesigncraft.dbs.tools.EmbedTemplates;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InteractionErrorListener extends ListenerAdapter {

    private static final List<User> users = new ArrayList<>();

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (checkEvent(event) != null)
            event.replyEmbeds(checkEvent(event)).setEphemeral(true).queue();
    }

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if (checkEvent(event) != null)
            event.replyEmbeds(checkEvent(event)).setEphemeral(true).queue();
    }

    @Override
    public void onEntitySelectInteraction(@NotNull EntitySelectInteractionEvent event) {
        if (checkEvent(event) != null)
            event.replyEmbeds(checkEvent(event)).setEphemeral(true).queue();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (checkEvent(event) != null)
            event.replyEmbeds(checkEvent(event)).setEphemeral(true).queue();
    }

    @Override
    public void onUserContextInteraction(@NotNull UserContextInteractionEvent event) {
        if (checkEvent(event) != null)
            event.replyEmbeds(checkEvent(event)).setEphemeral(true).queue();
    }

    @Override
    public void onMessageContextInteraction(@NotNull MessageContextInteractionEvent event) {
        if (checkEvent(event) != null)
            event.replyEmbeds(checkEvent(event)).setEphemeral(true).queue();
    }

    @Nullable
    private static MessageEmbed checkEvent(@NotNull GenericInteractionCreateEvent event) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }
        if (event.isAcknowledged())
            return null;

        if (Checks.isNullOrEmpty(users))
            return EmbedTemplates.issueEmbed("The bot didn't acknowledged this event. This is a bug. It was automatically sent to the developer.");

        EmbedBuilder embedBuilder = new EmbedBuilder(EmbedTemplates.issueEmbed("The bot didn't acknowledged an interaction event."));
        embedBuilder.addField("Interaction type", "```" + event.getType().name() + "```", true);
        embedBuilder.addField("User", "```" + event.getUser().getAsTag() + "```", true);
        embedBuilder.addField("Server", event.getGuild() != null ? "```" + event.getGuild().getName() + " (" + event.getGuild().getId() + ")```" : "```--- (DM)```", true);

        users.forEach(user -> user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessageEmbeds(embedBuilder.build()).queue()));
        LoggerFactory.getLogger(InteractionErrorListener.class).error("Didn't acknowledged an interaction event.");

        return EmbedTemplates.issueEmbed("The bot didn't acknowledged this event. This is a bug. It was automatically sent to the developer.");

    }

}