package de.thedesigncraft.discord.botstuff.essential.manage.versions;

import de.thedesigncraft.discord.botstuff.essential.Checks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VersionBuilder {

    private String name;
    private Version.Type type;
    private String information;
    private List<Version.Function> functions = new ArrayList<>();

    public VersionBuilder() {
    }

    public VersionBuilder(@NotNull VersionBuilder builder) {
        this.copyFrom(builder);
    }

    public VersionBuilder(@NotNull Version version) {
        this.copyFrom(version);
    }

    @NotNull
    public Version build() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot build an empty version!");
        } else {
            return new Version(this.name, this.type, this.information, this.functions);
        }
    }

    @NotNull
    public VersionBuilder clear() {
        this.name = null;
        this.type = null;
        this.information = null;
        this.functions.clear();
        return this;
    }

    public void copyFrom(@NotNull VersionBuilder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.information = builder.information;
        this.functions = builder.functions;
    }

    public void copyFrom(@NotNull Version version) {
        this.name = version.getName();
        this.type = version.getType();
        this.information = version.getInformation();
        this.functions = version.getFunctions();
    }

    public boolean isEmpty() {
        return (this.name == null || this.name.isEmpty()) && this.type == null && (this.information == null || this.information.isEmpty()) && (this.functions == null || this.functions.isEmpty());
    }

    @NotNull
    public VersionBuilder setName(@Nullable String name) {
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
    public VersionBuilder setType(@Nullable Version.Type type) {
        this.type = type;

        return this;
    }

    @NotNull
    public VersionBuilder setInformation(@Nullable String information) {
        if (information == null) {
            this.information = null;
        } else if (information.isEmpty()) {
            throw new IllegalArgumentException("information may not be empty.");
        } else {
            this.information = information;
        }

        return this;
    }

    @NotNull
    public VersionBuilder addFunction(@NotNull Version.Function function) {
        return this.addFunction(function.getName(), function.getDescription());
    }

    @NotNull
    public VersionBuilder addFunction(@Nullable String name, @Nullable String description) {
        Checks.notEmpty(name, "name");
        Checks.notEmpty(description, "description");
        this.functions.add(new Version.Function(name, description));
        return this;
    }

    @NotNull
    public VersionBuilder clearFunctions() {
        this.functions.clear();
        return this;
    }

    @NotNull
    public List<Version.Function> getFunctions() {
        return this.functions;
    }

}
