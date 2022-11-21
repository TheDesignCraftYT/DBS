package de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.oneSelection;

import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class OneSelectionMenuSlashCommandListener extends ListenerAdapter {

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {

        OneSelectionMenuCommandTemplate.onStringSelectInteraction(event);

    }
}
