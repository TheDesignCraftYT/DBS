package de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.manage.categories;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommandCategoryBuilder {

    private String name;
    private Emoji emoji;

    public CommandCategoryBuilder() {
    }

    public CommandCategoryBuilder(@NotNull CommandCategoryBuilder builder) {
        this.copyFrom(builder);
    }

    public CommandCategoryBuilder(@NotNull CommandCategory category) {
        this.copyFrom(category);
    }

    @NotNull
    public CommandCategory build() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot build an empty command category!");
        } else {
            return new CommandCategory(this.name, this.emoji);
        }
    }

    @NotNull
    public CommandCategoryBuilder clear() {
        this.name = null;
        this.emoji = null;
        return this;
    }

    public void copyFrom(@NotNull CommandCategoryBuilder builder) {
        this.name = builder.name;
        this.emoji = builder.emoji;
    }

    public void copyFrom(@NotNull CommandCategory category) {
        this.name = category.getName();
        this.emoji = category.getEmoji();
    }

    public boolean isEmpty() {
        return (this.name == null || this.name.isEmpty()) && this.emoji == null;
    }

    @NotNull
    public CommandCategoryBuilder setName(@Nullable String name) {
        if (name == null) {
            this.name = null;
        } else if (name.isEmpty()) {
            throw new IllegalArgumentException("name may not be empty.");
        } else {
            this.name = name;
        }

        return this;
    }

    @NotNull
    public CommandCategoryBuilder setEmoji(@Nullable Emoji emoji) {
        this.emoji = emoji;

        return this;
    }

}
