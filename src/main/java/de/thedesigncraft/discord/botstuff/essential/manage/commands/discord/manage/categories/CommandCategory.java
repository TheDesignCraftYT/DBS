package de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage.categories;

import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IDiscordCommand;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class CommandCategory {

    private final String name;
    private final Emoji emoji;
    private final List<IDiscordCommand> commands = IDiscordCommand.getByCategory(this);

    public CommandCategory(String name, Emoji emoji) {
        this.name = name;
        this.emoji = emoji;
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    @Nullable
    public Emoji getEmoji() {
        return this.emoji;
    }

    @Nullable
    public List<IDiscordCommand> getCommands() {
        return this.commands;
    }

    public boolean isEmpty() {
        return (this.name == null || this.name.isEmpty()) && this.emoji == null && (this.commands == null || this.commands.isEmpty());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CommandCategory)) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            CommandCategory other = (CommandCategory) obj;
            return Objects.equals(this.name, other.name) && Objects.equals(this.emoji, other.emoji) && Objects.equals(this.commands, other.commands);
        }
    }

}
