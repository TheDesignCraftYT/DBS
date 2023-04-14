package de.thedesigncraft.discord.core.commands.discord.events;

import de.thedesigncraft.discord.core.commands.discord.menus.IMenu;
import de.thedesigncraft.discord.core.commands.discord.types.DiscordCommandSetup;
import de.thedesigncraft.discord.core.commands.discord.types.IDiscordCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.requests.restaction.interactions.ModalCallbackAction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class DBSCommandInteractionEvent {

    /**
     * The command that was executed.
     */
    private final IDiscordCommand command;

    /**
     * The JDA event.
     */
    private final GenericCommandInteractionEvent event;

    /**
     * Creates a new {@link DBSCommandInteractionEvent}.
     *
     * @param command The command that was executed.
     * @param event   The JDA event.
     */
    public DBSCommandInteractionEvent(@NotNull IDiscordCommand command, @NotNull GenericCommandInteractionEvent event) {
        this.command = command;
        this.event = event;

        if (command.getSetup().isMessageAnswerProtectionEnabled()) {
            event.getHook().sendMessageEmbeds(new EmbedBuilder().setDescription("Loading...").build()).queue();
        }
    }

    /**
     * @return The {@link Command.Type} of the command.
     */
    @NotNull
    public Command.Type getType() {
        return event.getCommandType();
    }

    /**
     * @return The JDA event.
     */
    @NotNull
    public GenericCommandInteractionEvent getJDAEvent() {
        return event;
    }

    /**
     * Replies a {@link String} to the interaction.
     * Note: If the command hasn't the MessageAnswerProtection enabled, you only have 3 seconds to answer.
     * If you want to answer later, you have to activate the MessageAnswerProtection in the {@link DiscordCommandSetup}.
     * @param message The content to reply.
     * @return The {@link ReplyCallbackAction} or null if the MessageAnswerProtection is enabled.
     */
    public ReplyCallbackAction reply(@NotNull String message) {
        if (command.getSetup().isMessageAnswerProtectionEnabled()) {
            event.getHook().editOriginal(message).queue();
            return null;
        } else {
            return event.reply(message);
        }
    }

    /**
     * Replies one or more {@link MessageEmbed}s to the interaction.
     * Note: If the command hasn't the MessageAnswerProtection enabled, you only have 3 seconds to answer.
     * If you want to answer later, you have to activate the MessageAnswerProtection in the {@link DiscordCommandSetup}.
     * @param embeds The embeds to reply.
     * @return The {@link ReplyCallbackAction} or null if the MessageAnswerProtection is enabled.
     */
    public ReplyCallbackAction replyEmbeds(@NotNull MessageEmbed... embeds) {
        if (command.getSetup().isMessageAnswerProtectionEnabled()) {
            event.getHook().editOriginalEmbeds(embeds).queue();
            return null;
        } else {
            return event.replyEmbeds(Arrays.asList(embeds));
        }
    }

    /**
     * Replies a {@link IMenu} to the interaction.
     * Note: If the command hasn't the MessageAnswerProtection enabled, you only have 3 seconds to answer.
     * If you want to answer later, you have to activate the MessageAnswerProtection in the {@link DiscordCommandSetup}.
     * @param path The path of the menu to send.
     * @return The {@link ReplyCallbackAction} or null if the MessageAnswerProtection is enabled.
     */
    public ReplyCallbackAction replyMenu(@NotNull String path) {
        IMenu menu = IMenu.getMenuByPath(path);
        if (menu == null)
            throw new NullPointerException("Menu not found!");
        if (command.getSetup().isMessageAnswerProtectionEnabled()) {
            event.getHook().editOriginalEmbeds(menu.getEmbed()).setComponents(menu.getComponents()).queue();
            return null;
        } else {
            return event.replyEmbeds(menu.getEmbed()).setComponents(menu.getComponents());
        }
    }

    /**
     * Replies a {@link Modal} to the interaction.
     * Note: If the command hasn't the MessageAnswerProtection enabled, you only have 3 seconds to answer.
     * If you want to answer later, you have to activate the MessageAnswerProtection in the {@link DiscordCommandSetup}.
     * @param modal The modal to reply.
     * @return The {@link ModalCallbackAction} or null if the MessageAnswerProtection is enabled.
     */
    public ModalCallbackAction replyModal(Modal modal) {
        return event.replyModal(modal);
    }

}
