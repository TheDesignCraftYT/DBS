package de.thedesigncraft.discord.core.commands.discord.events;

import de.thedesigncraft.discord.core.commands.discord.types.message.IMessageContextMenu;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class DBSMessageCommandInteractionEvent extends DBSCommandInteractionEvent {

    /**
     * The JDA event
     */
    private final MessageContextInteractionEvent event;

    /**
     * Creates a new {@link DBSMessageCommandInteractionEvent}.
     *
     * @param command The command that was executed
     * @param event   The JDA event
     */
    public DBSMessageCommandInteractionEvent(@NotNull IMessageContextMenu command, @NotNull MessageContextInteractionEvent event) {
        super(command, event);
        this.event = event;
    }

    /**
     * @return The JDA event
     */
    @NotNull
    public MessageContextInteractionEvent getJDAEvent() {
        return event;
    }

}
