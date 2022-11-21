package de.thedesigncraft.discord.botstuff.essential.manage.versions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FunctionBuilder {

    private String name;
    private String description;

    public FunctionBuilder() {
    }

    public FunctionBuilder(@NotNull FunctionBuilder builder) {
        this.copyFrom(builder);
    }

    public FunctionBuilder(@NotNull Version.Function function) {
        this.copyFrom(function);
    }

    @NotNull
    public Version.Function build() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot build an empty function!");
        } else {
            return new Version.Function(this.name, this.description);
        }
    }

    @NotNull
    public FunctionBuilder clear() {
        this.name = null;
        this.description = null;
        return this;
    }

    public void copyFrom(@NotNull FunctionBuilder builder) {
        this.name = builder.name;
        this.description = builder.description;
    }

    public void copyFrom(@NotNull Version.Function function) {
        this.name = function.getName();
        this.description = function.getDescription();
    }

    public boolean isEmpty() {
        return (this.name == null || this.name.isEmpty()) && (this.description == null || this.description.isEmpty());
    }

    @NotNull
    public FunctionBuilder setName(@Nullable String name) {
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
    public FunctionBuilder setDescription(@Nullable String description) {
        if (description == null) {
            this.description = null;
        } else if (description.isEmpty()) {
            throw new IllegalArgumentException("description may not be empty.");
        } else {
            this.description = description;
        }

        return this;
    }

}
