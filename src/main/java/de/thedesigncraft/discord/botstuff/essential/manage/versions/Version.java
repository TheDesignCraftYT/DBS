package de.thedesigncraft.discord.botstuff.essential.manage.versions;

import de.thedesigncraft.discord.botstuff.essential.manage.commands.discord.IDiscordCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class Version {

    private final String name;
    private final Type type;
    private final String information;
    private final List<IDiscordCommand> commands = IDiscordCommand.getByVersion(this);
    private final List<Function> functions;

    public Version(String name, Type type, String information, List<Function> functions) {
        this.name = name;
        this.type = type;
        this.information = information;
        this.functions = functions;
    }

    @Nullable
    public String getName() {
        return this.name;
    }

    @Nullable
    public Type getType() {
        return this.type;
    }

    @Nullable
    public String getInformation() {
        return this.information;
    }

    @Nullable
    public List<IDiscordCommand> getCommands() {
        return this.commands;
    }

    @NotNull
    public List<Function> getFunctions() {
        return this.functions;
    }

    public boolean isEmpty() {
        return (this.name == null || this.name.isEmpty()) && this.type == null && (this.information == null || this.information.isEmpty()) && (this.commands == null || this.commands.isEmpty()) && (this.functions == null || this.functions.isEmpty());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Version)) {
            return false;
        } else if (obj == this) {
            return true;
        } else {
            Version other = (Version) obj;
            return Objects.equals(this.name, other.name) && Objects.equals(this.type, other.type) && Objects.equals(this.information, other.information) && Objects.equals(this.commands, other.commands) && Objects.equals(this.functions, other.functions);
        }
    }

    public static class Function {

        private final String name;
        private final String description;

        public Function(String name, String description) {
            this.name = name;
            this.description = description;
        }

        @Nullable
        public String getName() {
            return this.name;
        }

        @Nullable
        public String getDescription() {
            return this.description;
        }

        public boolean isEmpty() {
            return (this.name == null || this.name.isEmpty()) && (this.description == null || this.description.isEmpty());
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Function)) {
                return false;
            } else if (obj == this) {
                return true;
            } else {
                Function other = (Function) obj;
                return Objects.equals(this.name, other.name) && Objects.equals(this.description, other.description);
            }
        }

    }

    public enum Type {
        ALPHA,
        BETA,
        RELEASE
    }

}
