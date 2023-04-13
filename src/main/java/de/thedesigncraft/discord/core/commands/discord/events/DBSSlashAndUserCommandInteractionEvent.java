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

    private final SlashCommandInteractionEvent slashEvent;
    private final UserContextInteractionEvent userEvent;

    public DBSSlashAndUserCommandInteractionEvent(@NotNull ISlashAndUserCommand command, @NotNull SlashCommandInteractionEvent event) {
        super(command, event);
        this.slashEvent = event;
        this.userEvent = null;
    }

    public DBSSlashAndUserCommandInteractionEvent(@NotNull ISlashAndUserCommand command, @NotNull UserContextInteractionEvent event) {
        super(command, event);
        this.slashEvent = null;
        this.userEvent = event;
    }

    @Nullable
    public SlashCommandInteractionEvent getSlashEvent() {
        return slashEvent;
    }

    @Nullable
    public UserContextInteractionEvent getUserEvent() {
        return userEvent;
    }

    @NotNull
    public GenericCommandInteractionEvent getJDAEvent() {
        return slashEvent != null ? slashEvent : userEvent;
    }

    @Nullable
    public Member getTargetMember() {
        return slashEvent != null ? slashEvent.getOption("user").getAsMember() : userEvent.getTargetMember();
    }

    @NotNull
    public User getTarget() {
        return slashEvent != null ? slashEvent.getOption("user").getAsUser() : userEvent.getTarget();
    }

    @NotNull
    public User getUser() {
        return slashEvent != null ? slashEvent.getUser() : userEvent.getUser();
    }

}
