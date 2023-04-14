package de.thedesigncraft.discord.core.commands.discord.events;

import de.thedesigncraft.discord.core.commands.discord.types.user.IUserContextMenu;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class DBSUserCommandInteractionEvent extends DBSCommandInteractionEvent {

    /**
     * The JDA event.
     */
    private final UserContextInteractionEvent event;

    /**
     * Creates a new {@link DBSUserCommandInteractionEvent}.
     *
     * @param command The command that was executed.
     * @param event   The JDA event.
     */
    public DBSUserCommandInteractionEvent(@NotNull IUserContextMenu command, @NotNull UserContextInteractionEvent event) {
        super(command, event);
        this.event = event;
    }

    /**
     * @return The JDA event.
     */
    @NotNull
    public UserContextInteractionEvent getJDAEvent() {
        return event;
    }

}
