package de.thedesigncraft.discord.botstuff.essential.manage.commands.discord;

import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import org.jetbrains.annotations.NotNull;

public interface IUserContextMenu extends IDiscordCommand {

    @NotNull
    default String name() {

        return this.getClass().getSimpleName().replace("UserContextMenu", "").replace("ContextMenu", "").toLowerCase();

    }

    void performUserContextMenu(@NotNull UserContextInteractionEvent event);

}
