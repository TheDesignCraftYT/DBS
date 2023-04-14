package de.thedesigncraft.discord.core.commands.discord.events;

import de.thedesigncraft.discord.core.commands.discord.types.slashanduser.ISlashAndUserCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DBSSlashAndUserCommandInteractionEvent extends DBSCommandInteractionEvent {

    /**
     * The JDA event.
     */
    private final SlashCommandInteractionEvent slashEvent;

    /**
     * The JDA event.
     */
    private final UserContextInteractionEvent userEvent;

    /**
     * Creates a new {@link DBSSlashAndUserCommandInteractionEvent} from a {@link SlashCommandInteractionEvent}.
     *
     * @param command The command that was executed.
     * @param event   The JDA event.
     */
    public DBSSlashAndUserCommandInteractionEvent(@NotNull ISlashAndUserCommand command, @NotNull SlashCommandInteractionEvent event) {
        super(command, event);
        this.slashEvent = event;
        this.userEvent = null;
    }

    /**
     * Creates a new {@link DBSSlashAndUserCommandInteractionEvent} from a {@link UserContextInteractionEvent}.
     *
     * @param command The command that was executed.
     * @param event   The JDA event.
     */
    public DBSSlashAndUserCommandInteractionEvent(@NotNull ISlashAndUserCommand command, @NotNull UserContextInteractionEvent event) {
        super(command, event);
        this.slashEvent = null;
        this.userEvent = event;
    }

    /**
     * @return The {@link SlashCommandInteractionEvent} or null if the event was a {@link UserContextInteractionEvent}.
     */
    @Nullable
    public SlashCommandInteractionEvent getSlashEvent() {
        return slashEvent;
    }

    /**
     * @return The {@link UserContextInteractionEvent} or null if the event was a {@link SlashCommandInteractionEvent}.
     */
    @Nullable
    public UserContextInteractionEvent getUserEvent() {
        return userEvent;
    }

    /**
     * @return The JDA event.
     */
    @NotNull
    public GenericCommandInteractionEvent getJDAEvent() {
        return slashEvent != null ? slashEvent : userEvent;
    }

    /**
     * @return The {@link Member} of the target.
     */
    @Nullable
    public Member getTargetMember() {
        return slashEvent != null ? slashEvent.getOption("user").getAsMember() : userEvent.getTargetMember();
    }

    /**
     * @return The {@link User} of the target.
     */
    @NotNull
    public User getTarget() {
        return slashEvent != null ? slashEvent.getOption("user").getAsUser() : userEvent.getTarget();
    }

    /**
     * @return The {@link Member} of the user.
     */
    @NotNull
    public User getUser() {
        return slashEvent != null ? slashEvent.getUser() : userEvent.getUser();
    }

}
