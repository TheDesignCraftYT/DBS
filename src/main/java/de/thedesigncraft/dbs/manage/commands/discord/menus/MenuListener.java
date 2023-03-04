package de.thedesigncraft.dbs.manage.commands.discord.menus;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MenuListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        IMenu menu = IMenu.getMenuByPath(event.getComponentId());

        if (menu == null)
            return;

        event.editMessageEmbeds(menu.getEmbed()).setComponents(menu.getComponents()).queue();

    }
}
