package de.thedesigncraft.discord.core.commands.discord.events;

import de.thedesigncraft.discord.core.commands.discord.types.slash.ISlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class DBSSlashCommandInteractionEvent extends DBSCommandInteractionEvent {

    /**
     * The JDA event.
     */
    private final SlashCommandInteractionEvent event;

    /**
     * Creates a new {@link DBSSlashCommandInteractionEvent}.
     *
     * @param command The command that was executed.
     * @param event   The JDA event.
     */
    public DBSSlashCommandInteractionEvent(@NotNull ISlashCommand command, @NotNull SlashCommandInteractionEvent event) {
        super(command, event);
        this.event = event;
    }

    /**
     * @return The JDA event.
     */
    @NotNull
    public SlashCommandInteractionEvent getJDAEvent() {
        return event;
    }

}
