package de.thedesigncraft.dbs.manage.commands.discord.events;

import de.thedesigncraft.dbs.manage.commands.discord.types.message.IMessageContextMenu;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class DBSMessageCommandInteractionEvent extends DBSCommandInteractionEvent {

    private final MessageContextInteractionEvent event;

    public DBSMessageCommandInteractionEvent(@NotNull IMessageContextMenu command, @NotNull MessageContextInteractionEvent event) {
        super(command, event);
        this.event = event;
    }

    @NotNull
    public MessageContextInteractionEvent getJDAEvent() {
        return event;
    }

}
