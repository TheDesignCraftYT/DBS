package de.thedesigncraft.dbs.manage.commands.discord.events;

import de.thedesigncraft.dbs.manage.commands.discord.types.user.IUserContextMenu;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class DBSUserCommandInteractionEvent extends DBSCommandInteractionEvent {

    private final UserContextInteractionEvent event;

    public DBSUserCommandInteractionEvent(@NotNull IUserContextMenu command, @NotNull UserContextInteractionEvent event) {
        super(command, event);
        this.event = event;
    }

    @NotNull
    public UserContextInteractionEvent getJDAEvent() {
        return event;
    }

}
