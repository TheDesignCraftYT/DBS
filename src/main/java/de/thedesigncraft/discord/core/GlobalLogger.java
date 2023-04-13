package de.thedesigncraft.discord.core;

import de.thedesigncraft.discord.core.commands.console.DiscordConsoleCommandListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static org.slf4j.LoggerFactory.getLogger;

public class GlobalLogger {

    public static void info(@NotNull Class<?> clazz, String message) {
        sendConsole(Type.INFO, clazz.getSimpleName(), message);
    }

    public static void error(@NotNull Class<?> clazz, String message) {
        sendConsole(Type.ERROR, clazz.getSimpleName(), message);
    }

    public static void exceptionError(@NotNull Exception exception) {
        sendConsole(Type.EXCEPTION, exception);
    }

    public static void warn(@NotNull Class<?> clazz, String message) {
        sendConsole(Type.WARN, clazz.getSimpleName(), message);
    }

    private static void sendConsole(@NotNull Type type, String source, String message) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(type.name() + ": " + source);
        builder.setDescription("```" + message + "```");
        switch (type) {
            case INFO -> {
                builder.setColor(0x3e8aee);
                getLogger(source).info(message);
            }
            case ERROR, EXCEPTION -> {
                builder.setColor(0xdf4032);
                getLogger(source).error(message);
            }
            case WARN -> {
                builder.setColor(0xc1c100);
                getLogger(source).warn(message);
            }
        }
        if (DiscordConsoleCommandListener.getMessageChannel() != null) {
            MessageCreateAction action = DiscordConsoleCommandListener.getMessageChannel().sendMessageEmbeds(builder.build());
            if (type != Type.INFO)
                action = action.addContent("@everyone");
            action.queue();
        }
    }

    private static void sendConsole(@NotNull Type type, @NotNull Exception exception) {
        String source = Arrays.stream(exception.getStackTrace()).findFirst().get().getClassName();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(type.name() + ": " + source);
        builder.setDescription(exception.getMessage() + "\n\n```" + Arrays.toString(exception.getStackTrace()) + "```");
        switch (type) {
            case INFO -> {
                return;
            }
            case ERROR, EXCEPTION -> {
                builder.setColor(0xdf4032);
                getLogger(source).error(exception.getMessage(), exception);
            }
            case WARN -> {
                builder.setColor(0xc1c100);
                getLogger(source).warn(exception.getMessage(), exception);
            }
        }
        if (DiscordConsoleCommandListener.getMessageChannel() != null)
            DiscordConsoleCommandListener.getMessageChannel().sendMessageEmbeds(builder.build()).addContent("@everyone").queue();
    }

    public enum Type {
        INFO,
        ERROR,
        EXCEPTION,
        WARN
    }

}
