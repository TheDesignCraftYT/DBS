package de.thedesigncraft.listeners;

import de.thedesigncraft.tools.ComponentInteractions;
import de.thedesigncraft.tools.EmbedTemplates;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CancelButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        if (!event.getMessage().getAuthor().equals(event.getJDA().getSelfUser()))
            return;

        if (!event.getComponentId().equals("cancel"))
            return;

        if (!ComponentInteractions.getUserThatInitialized(event).equals(event.getUser()))
            return;

        event.getMessage().delete().queue();

        if (event.isFromGuild() && !event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            event.replyEmbeds(EmbedTemplates.issueEmbed("I don't have the permission to delete messages.")).setEphemeral(true).queue();
        } else if (event.getMessage().getReferencedMessage() != null) {
            event.getMessage().getReferencedMessage().delete().queue();
        }

    }
}
