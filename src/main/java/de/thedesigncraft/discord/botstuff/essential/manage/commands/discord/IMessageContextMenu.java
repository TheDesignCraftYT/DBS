package de.thedesigncraft.discord.botstuff.essential.manage.commands.discord;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import org.jetbrains.annotations.NotNull;

public interface IMessageContextMenu extends IDiscordCommand {

    @NotNull
    default String name() {

        return this.getClass().getSimpleName().replace("MessageContextMenu", "").replace("ContextMenu", "").toLowerCase();

    }

    void performMessageContextMenu(@NotNull MessageContextInteractionEvent event);

}
