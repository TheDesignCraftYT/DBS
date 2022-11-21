package de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash;

import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IDiscordCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ISlashCommand extends IDiscordCommand {

    @NotNull
    default String name() {

        return this.getClass().getSimpleName().replace("SlashCommand", "").replace("Command", "").toLowerCase();

    }

    @Nullable
    List<OptionData> options();

    void performSlashCommand(@NotNull SlashCommandInteractionEvent event);

}
