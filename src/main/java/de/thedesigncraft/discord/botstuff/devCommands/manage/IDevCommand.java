package de.thedesigncraft.discord.botstuff.devCommands.manage;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public interface IDevCommand {

    @NotNull
    default String name() {

        return this.getClass().getSimpleName().replace("DevCommand", "").replace("Command", "").toLowerCase();

    }

    void performDevCommand(@NotNull MessageReceivedEvent event, @NotNull String[] commandContent);

}
