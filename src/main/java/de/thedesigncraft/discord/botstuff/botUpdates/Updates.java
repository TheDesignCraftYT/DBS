package de.thedesigncraft.discord.botstuff.botUpdates;

import de.thedesigncraft.discord.botstuff.botUpdates.setup.BotUpdateValues;
import de.thedesigncraft.discord.botstuff.essential.EmbedTemplates;
import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IDiscordCommand;
import de.thedesigncraft.discord.botstuff.essential.manage.versions.Version;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class Updates {

    public static void sendUpdateMessageToUsers(@NotNull Version version, @NotNull User... users) {

        MessageEmbed updateMessageEmbed;

        if ((updateMessageEmbed = getUpdateMessageEmbed(version)) != null) {

            Arrays.stream(users).toList().forEach(user ->
                    user.openPrivateChannel().queue(privateChannel ->
                            privateChannel.sendMessageEmbeds(
                                    new EmbedBuilder(EmbedTemplates.standardEmbed("UpdateMessageEmbed", "The following embed was sent only to you."))
                                            .build(),
                                    updateMessageEmbed
                            ).queue()
                    ));

        }

    }

    public static void sendUpdateMessageToAll(@NotNull Version version) {

        MessageEmbed updateMessageEmbed;

        if ((updateMessageEmbed = getUpdateMessageEmbed(version)) != null) {

            BotUpdateValues.updateChannels.forEach(messageChannel -> messageChannel.sendMessageEmbeds(updateMessageEmbed).queue());

            StringBuilder channelsBuilder = new StringBuilder();

            BotUpdateValues.updateChannels.forEach(messageChannel -> channelsBuilder
                    .append("➤ ")
                    .append(messageChannel.getName())
                    .append(" (")
                    .append(messageChannel.getType())
                    .append(")\n")
            );

            BotUpdateValues.getOwners().forEach(user ->
                    user.openPrivateChannel().queue(privateChannel ->
                            privateChannel.sendMessageEmbeds(
                                    new EmbedBuilder(EmbedTemplates.standardEmbed("New Update", "The following update message was sent to all set channels:"))
                                            .addField("UpdateChannels", channelsBuilder.toString(), true)
                                            .build(),
                                    updateMessageEmbed
                            ).queue()
                    ));

        }

    }

    @Nullable
    private static MessageEmbed getUpdateMessageEmbed(@NotNull Version version) {

        EmbedBuilder embedBuilder = new EmbedBuilder(EmbedTemplates.standardEmbed("New Version: " + version.getName(), version.getInformation() + "\n\n" + BotUpdateValues.updateInformationFooter));

        if (IDiscordCommand.getByVersion(version) != null) {

            StringBuilder newCommandsBuilder = new StringBuilder();

            IDiscordCommand.getByVersion(version).forEach(iDiscordCommand -> newCommandsBuilder
                    .append("➤ `")
                    .append(IDiscordCommand.getType(iDiscordCommand).toString().replace("SLASH", ""))
                    .append("/` - ")
                    .append(iDiscordCommand.description())
                    .append("\n")
            );

            embedBuilder.addField("New Commands", newCommandsBuilder.toString(), true);

        }

        if (!version.getFunctions().isEmpty()) {

            StringBuilder newFunctionsBuilder = new StringBuilder();

            version.getFunctions().forEach(function -> newFunctionsBuilder
                    .append("➤ `")
                    .append(function.getName())
                    .append("` - ")
                    .append(function.getDescription())
                    .append("\n")
            );

            embedBuilder.addField("New Functions", newFunctionsBuilder.toString(), true);

        }

        if (embedBuilder.getFields().isEmpty())
            return null;

        return embedBuilder.build();

    }

}
