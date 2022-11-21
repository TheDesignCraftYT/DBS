package de.thedesigncraft.discord.botstuff.essential;

import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.oneSelection.OneSelectionMenuCommandTemplate;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ButtonInteractionListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        OneSelectionMenuCommandTemplate.onButtonInteraction(event);

        if (ActionRows.proof(event, "cancel", ActionRows.Check.ALL)) {
            if (event.getMessage().getMessageReference() != null)
                event.getMessage().getMessageReference().resolve().queue(message -> message.delete().queue());

            event.getMessage().delete().queue();
        }

    }
}
