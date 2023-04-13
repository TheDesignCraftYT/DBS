package de.thedesigncraft.discord.listeners;

import de.thedesigncraft.discord.manage.DBS;
import de.thedesigncraft.discord.manage.GlobalLogger;
import de.thedesigncraft.discord.tools.Checks;
import de.thedesigncraft.discord.tools.EmbedTemplates;
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

    public static void addUserById(long userId) {
        try {
            User user = DBS.getJDA().retrieveUserById(userId).complete();
            users.add(user);
            GlobalLogger.info(InteractionErrorListener.class, "Registered user '" + user.getAsTag() + "' as bot developer.");
        } catch (Exception e) {
            GlobalLogger.exceptionError(e);
        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }
        MessageEmbed embed = checkEvent(event);
        if (embed != null)
            event.replyEmbeds(embed).setEphemeral(true).queue();
    }

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }
        MessageEmbed embed = checkEvent(event);
        if (embed != null)
            event.replyEmbeds(embed).setEphemeral(true).queue();
    }

    @Override
    public void onEntitySelectInteraction(@NotNull EntitySelectInteractionEvent event) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }
        MessageEmbed embed = checkEvent(event);
        if (embed != null)
            event.replyEmbeds(embed).setEphemeral(true).queue();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }
        MessageEmbed embed = checkEvent(event);
        if (embed != null)
            event.replyEmbeds(embed).setEphemeral(true).queue();
    }

    @Override
    public void onUserContextInteraction(@NotNull UserContextInteractionEvent event) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }
        MessageEmbed embed = checkEvent(event);
        if (embed != null)
            event.replyEmbeds(embed).setEphemeral(true).queue();
    }

    @Override
    public void onMessageContextInteraction(@NotNull MessageContextInteractionEvent event) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {
        }
        MessageEmbed embed = checkEvent(event);
        if (embed != null)
            event.replyEmbeds(embed).setEphemeral(true).queue();
    }

    @Nullable
    private static MessageEmbed checkEvent(@NotNull GenericInteractionCreateEvent event) {
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