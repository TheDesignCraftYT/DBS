package de.thedesigncraft.discord.core.commands.console;

import de.thedesigncraft.discord.core.DBS;
import de.thedesigncraft.discord.core.GlobalLogger;
import de.thedesigncraft.discord.listeners.InteractionErrorListener;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class DiscordConsoleCommandListener extends ListenerAdapter {

    /**
     * The message channel where the console is mirrored to.
     */
    private static MessageChannel messageChannel;

    /**
     * Sets the message channel where the console is mirrored to.
     * @param channelId The id of the channel.
     */
    public static void setMessageChannelById(long channelId) {
        MessageChannel channel = DBS.getJDA().getChannelById(MessageChannel.class, channelId);
        DiscordConsoleCommandListener.messageChannel = channel;
        GlobalLogger.info(DBS.class, "Started initiation of discord bot.");
        GlobalLogger.info(InteractionErrorListener.class, "Registered channel '" + channel.getName() + "' as log channel.");
    }

    /**
     * Gets the message channel where the console is mirrored to.
     * @return The message channel.
     */
    public static MessageChannel getMessageChannel() {
        return messageChannel;
    }

    /**
     * Checks for console commands and executes them.
     */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;
        if (event.getChannel() != messageChannel) return;

        IConsoleCommand consoleCommand;
        if ((consoleCommand = IConsoleCommand.getActivatedConsoleCommandsMap().get(event.getMessage().getContentRaw().toLowerCase())) != null) {
            event.getChannel().sendMessage(consoleCommand.execute()).queue();
        } else {
            event.getChannel().sendMessage("This isn't a registered console command or this command isn't activated." + "\n" +
                    "For a list of available commands, run 'commands'.").queue();
        }

    }
}
