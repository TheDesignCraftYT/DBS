package de.thedesigncraft.discord.bot.stufftest;

import de.thedesigncraft.discord.core.commands.discord.events.DBSSlashAndUserCommandInteractionEvent;
import de.thedesigncraft.discord.core.commands.discord.types.slashanduser.ISlashAndUserCommand;
import de.thedesigncraft.discord.core.commands.discord.types.slashanduser.SlashAndUserCommandSetup;
import de.thedesigncraft.discord.core.commands.discord.types.slashanduser.SlashAndUserCommandSetupBuilder;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements ISlashAndUserCommand {
    @Override
    public @NotNull SlashAndUserCommandSetup getSetup() {
        return new SlashAndUserCommandSetupBuilder().setName("test").setDescription("Test Command").setSlashCommandOptionDescription("Beschreibung").setGlobal(true).build();
    }

    @Override
    public void execute(@NotNull DBSSlashAndUserCommandInteractionEvent event) {
        event.reply("Test: " + event.getTarget().getAsMention()).queue();
    }
}
