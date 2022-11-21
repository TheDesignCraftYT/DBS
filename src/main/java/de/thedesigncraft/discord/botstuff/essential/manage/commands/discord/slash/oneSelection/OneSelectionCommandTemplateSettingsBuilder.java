package de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.slash.oneSelection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OneSelectionCommandTemplateSettingsBuilder {

    private String commandName;
    private String optionName;
    private String optionDescription;

    public OneSelectionCommandTemplateSettingsBuilder() {
    }

    public OneSelectionCommandTemplateSettingsBuilder(@NotNull OneSelectionCommandTemplateSettingsBuilder builder) {
        this.copyFrom(builder);
    }

    public OneSelectionCommandTemplateSettingsBuilder(@NotNull OneSelectionMenuCommandTemplate.Settings settings) {
        this.copyFrom(settings);
    }

    @NotNull
    public OneSelectionMenuCommandTemplate.Settings build() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot build empty settings!");
        } else {
            return new OneSelectionMenuCommandTemplate.Settings(this.commandName, this.optionName, this.optionDescription);
        }
    }

    @NotNull
    public OneSelectionCommandTemplateSettingsBuilder clear() {
        this.commandName = null;
        this.optionName = null;
        this.optionDescription = null;
        return this;
    }

    public void copyFrom(@NotNull OneSelectionCommandTemplateSettingsBuilder builder) {
        this.commandName = builder.commandName;
        this.optionName = builder.optionName;
        this.optionDescription = builder.optionDescription;
    }

    public void copyFrom(@NotNull OneSelectionMenuCommandTemplate.Settings settings) {
        this.commandName = settings.commandName;
        this.optionName = settings.optionName;
        this.optionDescription = settings.optionDescription;
    }

    public boolean isEmpty() {
        return (this.commandName == null || this.commandName.isEmpty()) && (this.optionName == null || this.optionName.isEmpty()) && (this.optionDescription == null || this.optionDescription.isEmpty());
    }

    @NotNull
    public OneSelectionCommandTemplateSettingsBuilder setCommandName(@Nullable String commandName) {
        if (commandName == null) {
            this.commandName = null;
        } else if (commandName.isEmpty()) {
            throw new IllegalArgumentException("commandName may not be empty.");
        } else {
            this.commandName = commandName;
        }

        return this;
    }

    @NotNull
    public OneSelectionCommandTemplateSettingsBuilder setOptionName(@Nullable String optionName) {
        if (optionName == null) {
            this.optionName = null;
        } else if (optionName.isEmpty()) {
            throw new IllegalArgumentException("optionName may not be empty.");
        } else {
            this.optionName = optionName;
        }

        return this;
    }

    @NotNull
    public OneSelectionCommandTemplateSettingsBuilder setOptionDescription(@Nullable String optionDescription) {
        if (optionDescription == null) {
            this.optionDescription = null;
        } else if (optionDescription.isEmpty()) {
            throw new IllegalArgumentException("optionDescription may not be empty.");
        } else {
            this.optionDescription = optionDescription;
        }

        return this;
    }

}
