package de.thedesigncraft.discord.manage.commands.discord.events;

import de.thedesigncraft.discord.manage.commands.discord.types.slash.ISlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class DBSSlashCommandInteractionEvent extends DBSCommandInteractionEvent {

    private final SlashCommandInteractionEvent event;

    public DBSSlashCommandInteractionEvent(@NotNull ISlashCommand command, @NotNull SlashCommandInteractionEvent event) {
        super(command, event);
        this.event = event;
    }

    @NotNull
    public SlashCommandInteractionEvent getJDAEvent() {
        return event;
    }

}
