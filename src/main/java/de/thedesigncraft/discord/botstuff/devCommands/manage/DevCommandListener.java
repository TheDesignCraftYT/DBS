package de.thedesigncraft.discord.botstuff.devCommands.manage;

import de.thedesigncraft.discord.botstuff.devCommands.DevCommandsPackage;
import de.thedesigncraft.discord.botstuff.devCommands.setup.DevCommandsValues;
import de.thedesigncraft.discord.botstuff.essential.EmbedTemplates;
import de.thedesigncraft.discord.botstuff.essential.manage.Manager;
import de.thedesigncraft.discord.botstuff.essential.setup.StartupMethods;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class DevCommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (!event.isFromType(ChannelType.PRIVATE) || !DevCommandsValues.developerIds.contains(event.getAuthor().getIdLong()) || !event.getMessage().getContentRaw().startsWith("dev.") || !StartupMethods.getActivatedStuffPackageClasses().contains(DevCommandsPackage.class))
            return;

        String contentRaw = event.getMessage().getContentRaw().replaceFirst("dev.", "");
        String[] args = contentRaw.split(" ");

        Map<String, IDevCommand> devCommands = new HashMap<>();

        Manager.getDevCommands().forEach(iDevCommand -> devCommands.put(iDevCommand.name(), iDevCommand));

        IDevCommand devCommand;

        if ((devCommand = devCommands.get(args[0])) != null) {

            devCommand.performDevCommand(event, args);

        } else {

            event.getMessage().replyEmbeds(EmbedTemplates.issueEmbed("This isn't a registered DevCommand.", false)).mentionRepliedUser(false).queue();

        }

    }
}
