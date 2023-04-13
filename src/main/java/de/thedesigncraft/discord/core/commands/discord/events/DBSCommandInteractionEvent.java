package de.thedesigncraft.discord.core.commands.discord.events;

import de.thedesigncraft.discord.core.commands.discord.menus.IMenu;
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

    private final IDiscordCommand command;
    private final GenericCommandInteractionEvent event;

    public DBSCommandInteractionEvent(@NotNull IDiscordCommand command, @NotNull GenericCommandInteractionEvent event) {
        this.command = command;
        this.event = event;

        if (command.getSetup().isMessageAnswerProtectionEnabled()) {
            event.getHook().sendMessageEmbeds(new EmbedBuilder().setDescription("Loading...").build()).queue();
        }
    }

    @NotNull
    public Command.Type getType() {
        return event.getCommandType();
    }

    @NotNull
    public GenericCommandInteractionEvent getJDAEvent() {
        return event;
    }

    public ReplyCallbackAction reply(@NotNull String message) {
        if (command.getSetup().isMessageAnswerProtectionEnabled()) {
            event.getHook().editOriginal(message).queue();
            return null;
        } else {
            return event.reply(message);
        }
    }

    public ReplyCallbackAction replyEmbeds(@NotNull MessageEmbed... embeds) {
        if (command.getSetup().isMessageAnswerProtectionEnabled()) {
            event.getHook().editOriginalEmbeds(embeds).queue();
            return null;
        } else {
            return event.replyEmbeds(Arrays.asList(embeds));
        }
    }

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

    public ModalCallbackAction replyModal(Modal modal) {
        return event.replyModal(modal);
    }

}
